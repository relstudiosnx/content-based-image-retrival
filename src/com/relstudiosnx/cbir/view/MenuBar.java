package com.relstudiosnx.cbir.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Implementing Menu Bar of GUI<br>
 * <br>
 * Date: 1/20/2013 <br>
 * Time: 1:36 AM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
public class MenuBar implements ActionListener {

	private String[] menuNames = { "Files .Opens .Exit",
			"Views .Start Pages .Options .Result",
			"Helps .Abouts .Developer Wikis .Donates .Documentation" };
	private JMenu menu;
	private JMenuItem menuItem;

	/**
	 * 
	 * @return
	 */
	public JMenuBar createMenu() {
		// create a menu bar
		JMenuBar menuBar = new JMenuBar();

		for (String menuElement : menuNames) {
			String[] splitMenuItems = menuElement.split("s ");
			menu = new JMenu(splitMenuItems[0]);
			menuBar.add(menu);

			for (String menuItemElement : splitMenuItems) {
				// System.out.println(menuItemElement);
				if (menuItemElement.startsWith(".")) {
					menuItem = new JMenuItem(menuItemElement.replace('.', ' '));
					menuItem.addActionListener(this);
					menuItem.setActionCommand(menuItemElement);
					menu.add(menuItem);
				}
			}
		}
		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(".Exit"))
			System.exit(0);
	}
}
