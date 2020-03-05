package com.relstudiosnx.cbir.view.containerlayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.relstudiosnx.cbir.presenter.Presenter;

@SuppressWarnings("serial")
/**
 * Implementing Option Container of GUI<br>
 * <br>
 * Date: 1/25/2013 <br>
 * Time: 11:16 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
public class OptionContainer extends JPanel {

	private JLabel optionTitle;
	private String[] featureModuleNames = { "All Feature Modules",
			"Gabor Filter", "HSV Color Histogram", "Shape & Edge Detection" };
	private JComboBox<Object> featureComboModel;
	private JTextField noSearchResult, noFlickrImages;
	private Presenter presenter;

	public OptionContainer() {
		Dimension relDim = getPreferredSize();
		relDim.width = 610;
		setPreferredSize(relDim);

		// this method creates all the necessary compontents
		createComponents();

		// set up's the mig layout
		createMigLayout();

		// this method calls the action listener
		actionListener();
	}

	/**
	 * This method add the component to it's specific cells/grids
	 */
	public void createMigLayout() {
		setLayout(new MigLayout("", "[][]6[]", "[]6[]"));

		add(optionTitle, "wrap");
		add(new JLabel("Select feature extraction module:"), "");
		add(featureComboModel, "wrap, growx");
		add(new JLabel("Number of search results:"), "");
		add(noSearchResult, "wrap, growx");
		add(new JLabel("Number of flickr images:"), "");
		add(noFlickrImages, "wrap 3sp, growx");
		add(new JLabel("<html>"
				+ "	<b style ='color:#3888a9'>Important Notes:</b>"
				+ "		<ol>"
				+ "			<li>If you don't select feature extraction module then default will be <i>All Feature Module</i></li>"
				+ "			<li>Please don't try to enter alphabetical value in <i>Search Result</i> & <i>Fickr Retrival</i></li>"
				+ "</ol>" + "</html>"), "span 4");
	}

	/**
	 * This method creates the various JComponents for container.
	 */
	public void createComponents() {
		// create a title
		optionTitle = new JLabel("Options");
		optionTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		featureComboModel = new JComboBox<Object>();
		for (int i = 0; i < featureModuleNames.length; i++) {
			featureComboModel.addItem(featureModuleNames[i]);
		}

		noSearchResult = new JTextField(60);
		noSearchResult.setText("20");

		noFlickrImages = new JTextField(60);
		noFlickrImages.setText("10");
	}

	/**
	 * This methods activates when the components is clicked.
	 */
	private void actionListener() {
		featureComboModel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
