package org.iq4j.webcam;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_ProfileRGB;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 *
 * @author Samuel Audet
 * 
 * 
 * Make sure OpenGL or XRender is enabled to get low latency, something like
 *      export _JAVA_OPTIONS=-Dsun.java2d.opengl=True
 *      export _JAVA_OPTIONS=-Dsun.java2d.xrender=True
 *      
 * @author Sertac ANADOLLU ( anatolian )
 * 
 * JPanel version of javacv CanvasFrame      
 *      
 */
public class CanvasPanel extends JPanel  {

    private static final long serialVersionUID = 1L;

	public static class Exception extends java.lang.Exception {
        private static final long serialVersionUID = 1L;
		public Exception(String message) { super(message); }
        public Exception(String message, Throwable cause) { super(message, cause); }
    }
    
	public static GraphicsDevice getDefaultScreenDevice() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }
	
	public static double getGamma(GraphicsDevice screen) {
        ColorSpace cs = screen.getDefaultConfiguration().getColorModel().getColorSpace();
        if (cs.isCS_sRGB()) {
            return 2.2;
        } else {
            try {
                return ((ICC_ProfileRGB)((ICC_ColorSpace)cs).getProfile()).getGamma(0);
            } catch (RuntimeException e) { }
        }
        return 0.0;
    }

    public CanvasPanel() {
    	this(0.0);
    }
    
    public CanvasPanel(double gamma) {
        this(gamma, Dimensions.DEFAULT_SIZE);
    }

    public CanvasPanel(double gamma, Dimension size) {
    	this.gamma = gamma;
    	setPreferredSize(size);
    	setSize(size);
    	setBackground(Color.LIGHT_GRAY);
    }

    private void startCanvas() {
        Runnable r = new Runnable() { public void run() {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatch);
            
            GraphicsDevice gd = getGraphicsConfiguration().getDevice();
            double g = gamma == 0.0 ? getGamma(gd) : gamma;
            inverseGamma = g == 0.0 ? 1.0 : 1.0/g;
            setVisible(true);
            setupCanvas(gamma);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }};

        if (EventQueue.isDispatchThread()) {
            r.run();
            setCanvasSize(getSize().width, getSize().height);
        } else {
            try {
                EventQueue.invokeAndWait(r);
            } catch (java.lang.Exception ex) { }
        }
    }

    public void resetCanvas() {
    	canvas = null;
    }
    
    protected void setupCanvas(double gamma) {
        canvas = new Canvas() {
            private static final long serialVersionUID = 1L;
			@Override public void update(Graphics g) {
                paint(g);
            }
			
			
            @Override public void paint(Graphics g) {
                // Calling BufferStrategy.show() here sometimes throws
                // NullPointerException or IllegalStateException,
                // but otherwise seems to work fine.
                try {
                    BufferStrategy strategy = canvas.getBufferStrategy();
                    do {
                        do {
                            g = strategy.getDrawGraphics();
                            if (color != null) {
                                g.setColor(color);
                                g.fillRect(0, 0, getWidth(), getHeight());
                            }
                            if (image != null) {
                                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                            }
                            if (buffer != null) {
                                g.drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
                            }
                            g.dispose();
                        } while (strategy.contentsRestored());
                        strategy.show();
                    } while (strategy.contentsLost());
                } catch (NullPointerException e) {
                } catch (IllegalStateException e) { }
            }
        };
        
        needInitialResize = true;
        add(canvas);
        canvas.setVisible(true);
        canvas.createBufferStrategy(2);
    
    }

    // used for example as debugging console...
    public static CanvasPanel global = null;

    // Latency is about 60 ms on Metacity and Windows XP, and 90 ms on Compiz Fusion,
    // but we set the default to twice as much to take into account the roundtrip
    // camera latency as well, just to be sure
    public static final long DEFAULT_LATENCY = 200;
    private long latency = DEFAULT_LATENCY;

    private KeyEvent keyEvent = null;
    private KeyEventDispatcher keyEventDispatch = new KeyEventDispatcher() {
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                synchronized (CanvasPanel.this) {
                    keyEvent = e;
                    CanvasPanel.this.notify();
                }
            }
            return false;
        }
    };

    protected Canvas canvas = null;
    protected boolean needInitialResize = false;
    protected double initialScale = 1.0;
    protected double inverseGamma = 1.0;
    private Color color = null;
    private Image image = null;
    private BufferedImage buffer = null;
    private final double gamma;

    public long getLatency() {
        // if there exists some way to estimate the latency in real time,
        // add it here
        return latency;
    }
    public void setLatency(long latency) {
        this.latency = latency;
    }
    public void waitLatency() throws InterruptedException {
        Thread.sleep(getLatency());
    }

    public KeyEvent waitKey() throws InterruptedException {
        return waitKey(0);
    }
    public synchronized KeyEvent waitKey(int delay) throws InterruptedException {
        if (delay >= 0) {
            keyEvent = null;
            wait(delay);
        }
        KeyEvent e = keyEvent;
        keyEvent = null;
        return e;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Dimension getCanvasSize() {
        return canvas.getSize();
    }
    
    public void setCanvasSize(final int width, final int height) {
        Dimension d = getCanvasSize();
        if (d.width == width && d.height == height) {
            return;
        }

        Runnable r = new Runnable() { public void run() {
            // There is apparently a bug in Java code for Linux, and what happens goes like this:
            // 1. Canvas gets resized, checks the visible area (has not changed) and updates
            // BufferStrategy with the same size. 2. pack() resizes the frame and changes
            // the visible area 3. We call Canvas.setSize() with different dimensions, to make
            // it check the visible area and reallocate the BufferStrategy almost correctly
            // 4. Finally, we resize the Canvas to the desired size... phew!
            
            canvas.setSize(width, height);
            setSize(width, height);
            canvas.setSize(width+1, height+1);
            canvas.setSize(width, height);
            needInitialResize = false;
        }};

        if (EventQueue.isDispatchThread()) {
            r.run();
        } else {
            try {
                EventQueue.invokeAndWait(r);
            } catch (java.lang.Exception ex) { }
        }
    }

    public double getCanvasScale() {
        return initialScale;
    }
    
    public void setCanvasScale(double initialScale) {
        this.initialScale = initialScale;
        this.needInitialResize = true;
    }

    public Graphics2D createGraphics() {
        if (buffer == null || buffer.getWidth() != canvas.getWidth() || buffer.getHeight() != canvas.getHeight()) {
            BufferedImage newbuffer = canvas.getGraphicsConfiguration().createCompatibleImage(
                    canvas.getWidth(), canvas.getHeight(), Transparency.TRANSLUCENT);
            if (buffer != null) {
                Graphics g = newbuffer.getGraphics();
                g.drawImage(buffer, 0, 0, null);
                g.dispose();
            }
            buffer = newbuffer;
        }
        return buffer.createGraphics();
    }
    public void releaseGraphics(Graphics2D g) {
        g.dispose();
        canvas.paint(null);
    }

    public void showColor(CvScalar color) {
        showColor(new Color((int)color.red(), (int)color.green(), (int)color.blue()));
    }
    public void showColor(Color color) {
        this.color = color;
        this.image = null;
        canvas.paint(null);
    }

    // Java2D will do gamma correction for TYPE_CUSTOM BufferedImage, but
    // not for the standard types, so we need to do it manually.
    public void showImage(IplImage image) {
        showImage(image, false);
    }
    
    public void showImage(IplImage image, boolean flipChannels) {
        showImage(image.getBufferedImage(image.getBufferedImageType() ==
                BufferedImage.TYPE_CUSTOM ? 1.0 : inverseGamma, flipChannels));
    }
    public void showImage(Image image) {
    	if(canvas == null) {
    		startCanvas();
    	}
        if (image == null) {
            return;
        } else if (needInitialResize) {
            int w = (int)Math.round(image.getWidth (null)*initialScale);
            int h = (int)Math.round(image.getHeight(null)*initialScale);
            setCanvasSize(w, h);
        }
        this.color = null;
        this.image = image;
        canvas.paint(null);
    }
    
}
