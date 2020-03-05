package com.relstudiosnx.cbir.view.containerlayout;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Implementing CardLayout Container<br>
 * <br>
 * Date: 1/19/2013 <br>
 * Time: 5:48 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
@SuppressWarnings("serial")
public class CardContainer extends JPanel {

	private String[] panelName = { "index", "search", "option", "about" };
	private CardLayout cards;
	private SearchContainer searchContainer;
	private OptionContainer optionContainer;
	private IndexContainer indexContainer;
	
	/**
	 * Default Constructor
	 */
	public CardContainer(SearchContainer searchContainer, OptionContainer optionContainer, IndexContainer indexContainer) {
		// default constructor for setting the size of the panel, instantating
		// object etc
		Dimension relDim = getPreferredSize();
		relDim.width = 610;
		setPreferredSize(relDim);

		cards = new CardLayout();
		
		this.searchContainer = searchContainer;
		this.optionContainer = optionContainer;
		this.indexContainer = indexContainer;

		// setting up the border
		Border innerBorder = BorderFactory.createEmptyBorder(5, 7, 7, 7);
		Border outerBorder = BorderFactory.createEmptyBorder();
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

		setLayout(cards);
		addToPanel();
	}

	/**
	 * 
	 * @return pannelName This method returns the name of the each panel
	 */
	public String[] getPannelName() {
		return panelName;
	}

	/**
	 * 
	 * @param controlsString
	 *            This method set's pannelName
	 */
	public void setPannelName(String[] pannelName) {
		this.panelName = pannelName;
	}

	/**
	 * 
	 * @return This method get's the cardlayout object
	 */
	public CardLayout getCards() {
		return cards;
	}

	/**
	 * 
	 * @param cards
	 *            This method set's the cardlayout object
	 */
	public void setCards(CardLayout cards) {
		this.cards = cards;
	}

	/**
	 * This method generates each object the panel class and add's to the
	 * cardlayout
	 */
	private void addToPanel() {
		add(indexContainer, panelName[0]);
		add(searchContainer, panelName[1]);
		add(optionContainer, panelName[2]);
	}
}
