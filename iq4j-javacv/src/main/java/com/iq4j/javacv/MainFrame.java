package com.iq4j.javacv;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.iq4j.webcam.Actions;
import org.iq4j.webcam.SelectedWebcam;
import org.iq4j.webcam.SelectedWebcamController;

import com.iq4j.resources.icons.Icons;


public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -5185959921350165587L;

	private JPanel contentPane; 
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mnitmExit = new JMenuItem(Actions.EXIT);
	private final JMenu mnHelp = new JMenu("Help");
	private final JToolBar toolBarMain = new JToolBar();
	private final JMenu mnSettings = new JMenu("Settings");
	private final SelectedWebcamController selectedWebcamController = new SelectedWebcamController();
	private final JPanel panel = new JPanel();
	

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		initialize();
	}
	
	private void initialize() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SelectedWebcam.stop();
			}
		});
		setTitle("K12Hub - Webcam Scanner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 700);
		{
			setJMenuBar(menuBar);
		}
		{
			menuBar.add(mnFile);
		}
		{
			mnFile.add(mnitmExit);
		}
		{
			menuBar.add(mnSettings);
		}
		{
			menuBar.add(mnHelp);
		}
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		setIconImage(Icons.get("camera").getImage());

		this.contentPane.setLayout(new BorderLayout(0, 0));
		{
			toolBarMain.setAlignmentY(Component.CENTER_ALIGNMENT);
			toolBarMain.setPreferredSize(new Dimension(13, 35));
			toolBarMain.setFloatable(false);
			this.contentPane.add(toolBarMain, BorderLayout.NORTH);
		}
		
		this.toolBarMain.add(this.selectedWebcamController);
		this.panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new CardLayout(0, 0));
		
		this.panel.add(new TestPanel());
		pack();
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}
