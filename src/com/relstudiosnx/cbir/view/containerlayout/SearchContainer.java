package com.relstudiosnx.cbir.view.containerlayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.relstudiosnx.cbir.model.utils.ImageFileFilter;
import com.relstudiosnx.cbir.presenter.Presenter;
import com.relstudiosnx.cbir.view.JThumbnailPanel;

@SuppressWarnings("serial")
/**
 * Implementing Search Container of GUI<br>
 * <br>
 * Date: 1/25/2013 <br>
 * Time: 6:54 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
public class SearchContainer extends JPanel {

	private JLabel searchTitle;
	private JTextField dirUrl;
	private JButton openImage, startSearching;
	private JProgressBar progressBar;
	private Presenter presenter;
	private JFileChooser imageChooser;
	private JThumbnailPanel jThumbnailPanel;

	public SearchContainer() {
		Dimension relDim = getPreferredSize();
		relDim.width = 610;
		setPreferredSize(relDim);

		// calling the createComponent method
		createComponents();

		// calling createMigLayout method
		createMigLayout();
		actionHanlder();
	}

	/**
	 * This method add the component to it's specific cells/grids
	 */
	public void createMigLayout() {
		setLayout(new MigLayout("", "[][]6[]", "[]6[]"));

		add(searchTitle, "wrap");
		add(dirUrl, "span 3");
		add(openImage, "wrap");
		add(progressBar, "wrap, span 4, growx");
		add(startSearching, "wrap 3sp, span 4, right");
		add(new JLabel(
				"<html>"
						+ "	<b style ='color:#3888a9'>Important Notes:</b>"
						+ "		<ol>"
						+ "			<li>JPEG images in directory and <i>sub-directories</i> will be indexed.</li>"
						+ "			<li>You can <i>update the existing index</i> in the database by checking the checkbox.</li>"
						+ "</ol>" + "</html>"), "span 4");
	}

	/**
	 * This method creates the various JComponents for container.
	 */
	public void createComponents() {
		// create a title
		searchTitle = new JLabel("Search");
		searchTitle.setFont(new Font("Tahoma", Font.BOLD, 15));

		dirUrl = new JTextField();
		dirUrl.setColumns(70);
		dirUrl.setEditable(false);

		openImage = new JButton("Open Image");
		
		imageChooser = new JFileChooser();

		startSearching = new JButton("Start");
		startSearching.setPreferredSize(new Dimension(92, 22));

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setString("Search State..");
		jThumbnailPanel = new JThumbnailPanel();

	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	private void actionHanlder() {
		openImage.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
				try {
					browseButtonActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		startSearching.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
				searchButtonActionPerformed(evt);
			}
		});
	}
	
	private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		imageChooser.setFileFilter(new ImageFileFilter());
		imageChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		imageChooser.setDialogTitle("Open Query Image ...");
        imageChooser.showDialog(this, "Open");
        File f = imageChooser.getSelectedFile();
        if (f == null) {
            return;
        }
        dirUrl.setText(f.getAbsolutePath());
        //BufferedImage bufferedImage = ImageIO.read(imageChooser.getSelectedFile());
        
		
	}
	
	private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
	
	
	
}
