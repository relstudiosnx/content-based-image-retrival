package com.relstudiosnx.cbir.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.relstudiosnx.cbir.presenter.Presenter;
import com.relstudiosnx.cbir.view.containerlayout.CardContainer;
import com.relstudiosnx.cbir.view.containerlayout.IndexContainer;
import com.relstudiosnx.cbir.view.containerlayout.OptionContainer;
import com.relstudiosnx.cbir.view.containerlayout.SearchContainer;

public class View extends JFrame {

	private static final long serialVersionUID = -799909666246220892L;
	private final NavagationMenu navagationMenu;
	private final MenuBar menuBar;
	private final CardContainer cardContainer;
	private final IndexContainer indexContainer;
	private final SearchContainer searchContainer;
	private final OptionContainer optionContainer;

	// private Presenter presenter;

	/**
	 * Default Constructor
	 * 
	 * @param indexContainer
	 * @param searchContainer
	 * @param optionContainer
	 */
	public View(IndexContainer indexContainer, SearchContainer searchContainer,
			OptionContainer optionContainer) {
		// title setup
		super("Content Based Image Retriveal v.1.0");

		this.indexContainer = indexContainer;
		this.searchContainer = searchContainer;
		this.optionContainer = optionContainer;

		cardContainer = new CardContainer(searchContainer, optionContainer,
				indexContainer);
		navagationMenu = new NavagationMenu(cardContainer);
		menuBar = new MenuBar();

		setAttributes();
		add(navagationMenu, BorderLayout.WEST);
		add(cardContainer, BorderLayout.CENTER);
		pack();
	}

	/**
	 * 
	 */
	private void setAttributes() {
		// set up the menubar
		setJMenuBar(menuBar.createMenu());
		// set up the icons
		setIconImage(java.awt.Toolkit
				.getDefaultToolkit()
				.getImage(
						View.class
								.getResource("/com/relstudiosnx/cbir/ui/lib/images/ico_logo.png")));

		// set the layout manager
		setLayout(new BorderLayout());

		// setting up the size of the window
		setSize(700, 400);
		setMinimumSize(new Dimension(600, 445));

	}

	/**
	 * 
	 */
	public void setPresenter(Presenter presenter) {
		indexContainer.setPresenter(presenter);
		searchContainer.setPresenter(presenter);
		optionContainer.setPresenter(presenter);
	}
}
