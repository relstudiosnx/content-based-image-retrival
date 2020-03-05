package com.relstudiosnx.cbir.presenter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

//import org.apache.log4j.Logger;

import com.relstudiosnx.cbir.model.Model;
import com.relstudiosnx.cbir.model.utils.FileReader;
import com.relstudiosnx.cbir.view.View;
import com.relstudiosnx.cbir.view.containerlayout.IndexContainer;
import com.relstudiosnx.cbir.view.containerlayout.SearchContainer;

/**
 * Implementing Index Container<br>
 * <br>
 * Date: 1/25/2013 <br>
 * Time: 2:48 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
public class Presenter {

	//private static Logger logger = Logger.getLogger(Presenter.class);
	private final View view;
	private final Model model;
	private String filePath;

	/**
	 * Default Constructor
	 * 
	 * @param view
	 * @param model
	 */
	public Presenter(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	
	/**
	 * 
	 * @param fileChooser
	 * @param indexContainer
	 * @param dirUrl
	 * @throws IOException
	 */
	public void openDirBtn(JFileChooser fileChooser,
			IndexContainer indexContainer, JTextField dirUrl)
			throws IOException {
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (fileChooser.showOpenDialog(indexContainer) == JFileChooser.APPROVE_OPTION) {
			filePath = fileChooser.getSelectedFile().getAbsolutePath();
			dirUrl.setText(filePath); // contains only directory path
		}
	}

	/**
	 * 
	 * @param fileChooser
	 * @param searchContainer
	 * @param dirUrl
	 */
	public void openImageBtn(JFileChooser fileChooser,
			SearchContainer searchContainer, JTextField dirUrl) {
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (fileChooser.showOpenDialog(searchContainer) == JFileChooser.APPROVE_OPTION) {
			filePath = fileChooser.getSelectedFile().getAbsolutePath();
			dirUrl.setText(filePath); // contains only file path
		}
	}
	
	public void startIndexingBtn() throws FileNotFoundException, IOException {
		model.extract(filePath);
	}
}
