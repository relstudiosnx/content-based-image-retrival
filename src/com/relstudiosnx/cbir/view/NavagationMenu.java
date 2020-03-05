package com.relstudiosnx.cbir.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import com.relstudiosnx.cbir.view.containerlayout.CardContainer;

/**
 * Implementing Left Navagation Menu of GUI<br>
 * <br>
 * Date: 1/19/2013 <br>
 * Time: 5:54 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
@SuppressWarnings("serial")
public class NavagationMenu extends JPanel {

	private JButton[] btnIndex = new JButton[4];
	private String[] btnNames = { "Index", "Search", "Option", "About" };
	private String[] imageIcon = { "ico_index", "ico_search", "ico_options",
			"ico_about" };
	private CardContainer cardContainer;

	/**
	 * Default Constructor
	 */
	public NavagationMenu(CardContainer cardContainer) {
		// Setting up the dimension
		Dimension relDim = getPreferredSize();
		relDim.width = 95;
		setBackground(Color.WHITE);

		setPreferredSize(relDim);
		this.cardContainer = cardContainer;

		// setting up the border
		setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), new MatteBorder(0, 0, 0, 0, (Color) new Color(255,
				255, 255))));

		// setting up the layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// calling the menuButtons
		menuButtons();
		actionListener();
	}

	/**
	 * This method creates the JButton and add to the JPanel
	 */
	private void menuButtons() {
		// creates the buttons and define the sizes
		for (int i = 0; i < btnNames.length; i++) {
			btnIndex[i] = new JButton(btnNames[i]);
			// btnIndex[i].addActionListener(this);
			setAttributes(i);

			add(btnIndex[i]);
		}
	}

	/**
	 * This method set's the various attributes regarding the JButton for
	 * example setting size, icons and so on..
	 * 
	 * @param i
	 */
	private void setAttributes(int i) {
		// sets the maximum size
		btnIndex[i].setMaximumSize(new Dimension(89, 110));

		// set the font size
		btnIndex[i].setFont(new Font("Tahoma", Font.PLAIN, 11));

		// sets the respective icons also
		btnIndex[i].setIcon(new ImageIcon(NavagationMenu.class
				.getResource("/com/relstudiosnx/cbir/ui/lib/images/"
						+ imageIcon[i] + ".png")));

		// sets up the cursor
		btnIndex[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// alignment top and bottom
		btnIndex[i].setVerticalTextPosition(SwingConstants.BOTTOM);
		btnIndex[i].setHorizontalTextPosition(SwingConstants.CENTER);
	}

	/**
	 * This method performs event handler when button is been clicked.
	 */
	private void actionListener() {
		for (int i = 0; i < btnNames.length; i++) {
			final int indicator = i;
			btnIndex[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardContainer.getCards().show(cardContainer,
							cardContainer.getPannelName()[indicator]);
				}
			});
		}
	}

}
