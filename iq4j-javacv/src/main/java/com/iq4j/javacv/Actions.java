package com.iq4j.javacv;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.iq4j.resources.icons.Icons;

public class Actions {

	public static final ActionImpl EXIT = new ActionImpl("Exit", Icons.EXIT) {

		private static final long serialVersionUID = -5288165387000393690L;

		public void actionPerformed(ActionEvent e) {
			Application.exit();
		}
	};
	
		
	public abstract static class ActionImpl extends AbstractAction {

		private static final long serialVersionUID = 5041707033525696486L;

		public ActionImpl() {
			super();
		}

		public ActionImpl(String name, Icon icon) {
			super(name, icon);
		}

		public ActionImpl(String name) {
			super(name);
		}

		public ActionImpl(String name, Icon icon, String desc, Integer mnemonic) {
			super(name, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

	}

}
