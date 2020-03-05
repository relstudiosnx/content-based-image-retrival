package com.relstudiosnx.cbir.view.containerlayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import net.miginfocom.swing.MigLayout;

import com.relstudiosnx.cbir.Constants;
import com.relstudiosnx.cbir.model.clustering.Cluster;
import com.relstudiosnx.cbir.model.clustering.KMeans;
import com.relstudiosnx.cbir.model.imageanalysis.utils.GraphicsUtilities;
import com.relstudiosnx.cbir.model.index.ClusterWriter;
import com.relstudiosnx.cbir.model.index.Index;
import com.relstudiosnx.cbir.model.index.IndexWriter;
import com.relstudiosnx.cbir.model.utils.ImageFileFilter;
import com.relstudiosnx.cbir.presenter.Presenter;
//import org.apache.lucene.index.IndexWriter;

/**
 * Implementing Index Container<br>
 * <br>
 * Date: 1/25/2013 <br>
 * Time: 2:48 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
public class IndexContainer extends JPanel {

    private static final long serialVersionUID = -2894411498083753253L;
    private JLabel indexTitle;

    private JTextField dirUrl;

    private JButton openDir;
    private JButton startIndexing;
    private JProgressBar indexProgress;
    private JCheckBox addExistingCheck;
    private JFileChooser fileChooser;
    
    private SwingWorker<String, Void> indexWorker;
    
    private long time;
    private int numIndex;

    public IndexContainer() {
            Dimension relDim = getPreferredSize();
            relDim.width = 610;
            setPreferredSize(relDim);

            createComponents();
            createMigLayout();
            actionHandler();
    }

    /**
     * This method add the component to it's specific cells/grids
     */
    private void createMigLayout() {
            setLayout(new MigLayout("", "[][]6[]", "[]6[]"));

            add(indexTitle, "wrap");
            add(dirUrl, "span 3");
            add(openDir, "wrap");
            add(indexProgress, "wrap, span 4, growx");
            add(addExistingCheck, "span 3, split 2, right");
            add(new JLabel("Add Existing Index"));
            add(startIndexing, "wrap 3sp");
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
    private void createComponents() {
            // create a title
            indexTitle = new JLabel("Indexing");
            indexTitle.setFont(new Font("Tahoma", Font.BOLD, 15));

            dirUrl = new JTextField();
            dirUrl.setColumns(70);
            dirUrl.setEditable(false);

            openDir = new JButton("Open Dir");
            fileChooser = new JFileChooser();

            startIndexing = new JButton("Start");
            startIndexing.setPreferredSize(new Dimension(95, 22));

            indexProgress = new JProgressBar();
            //progressBar.setStringPainted(true);
           // progressBar.setString("Indexing State..");

            addExistingCheck = new JCheckBox();
    }

    /**
     * 
     * @param presenter
     */
    public void setPresenter(Presenter presenter) {
    }

    private void actionHandler() {
        openDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                browseFolderActionPerformed(evt);
            }
        });

        // StartIndex Event Generator
        startIndexing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	startIndexButtonActionPerformed(evt);
            }
        });
    }
    
    private void browseFolderActionPerformed(java.awt.event.ActionEvent evt) {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Open Index Folder..");
        fileChooser.showDialog(this, "Open");
        
        // get the selected file now
        File f = fileChooser.getSelectedFile();
        if(f == null) {
            //startIndexing.setEnabled(false);
            return;
        }
        
        dirUrl.setText(f.getAbsolutePath());
        
        File[] files = f.listFiles(new ImageFileFilter(false));
        if(files.length == 0) {
        	return;
        }
        
        // enable combo box
    } // end of function
    
    private void startIndexButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	indexWorker = new SwingWorker<String, Void>() {

            String notes;

            @Override
            protected String doInBackground() throws Exception {
                File folder = fileChooser.getSelectedFile();

                File[] files = folder.listFiles(new ImageFileFilter(false));
                //System.out.println(files.length);
                if (!folder.isDirectory() || folder == null || files.length == 0) {
                    notes = "FAIL";
                    return notes;
                }

                startIndexing.setEnabled(false);
                //viewDataButton.setEnabled(false);
                indexProgress.setStringPainted(true);
                time = System.currentTimeMillis();
                IndexWriter indexWriter = new IndexWriter();
                List<Index> indexes = new ArrayList<Index>();
                // feature extraction
                numIndex = 1;
                for (File f : files) {
                    try {
                        BufferedImage bufferedImage = ImageIO.read(f);
                        if (bufferedImage.getHeight(null) != Constants.IMAGE_HEIGHT
                                || bufferedImage.getWidth(null) != Constants.IMAGE_WIDTH) {
                            bufferedImage = GraphicsUtilities.resizeImage(bufferedImage,
                                    Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT);
                        }
                        //indexingStatusLabel.setText("Extracting image : " + f.getAbsolutePath());
                        Index index = indexWriter.getIndex(bufferedImage);
                        indexProgress.setValue(numIndex * 100 / files.length);
                        index.setFilePath(f.getAbsolutePath());
                        //indexWriter.addIndex(index);
                        indexes.add(index);
                        numIndex++;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                // k-means clustering
                KMeans kMeans = new KMeans();
                //int maxIter = Integer.parseInt(maxIterText.getText());
                kMeans.setNumCluster(3);
                kMeans.setMaxIteration(200);
                //indexingStatusLabel.setText("Clustering Indexs ....");
                indexProgress.setIndeterminate(true);
                kMeans.computeCluster(indexWriter, indexes);
                indexProgress.setIndeterminate(false);

                //indexingStatusLabel.setText("Saving Indexs ....");
                // indexing ...
                Cluster[] clusters = kMeans.getClusters();
                // create root index
                indexWriter.open();
                for (Cluster c : clusters) {
                    indexWriter.addCluster(c);
                }
                indexWriter.close();


                //create clusters index
                numIndex = 1;
                ClusterWriter clusterWriter = new ClusterWriter();
                for (int idx = 0; idx < kMeans.getNumCluster(); idx++) {
                    clusterWriter.open(idx);
                    for (Index ix : clusters[idx].getMembers()) {
                        //indexingStatusLabel.setText("Add image " + ix.getFilePath() + " to cluster-" + idx);
                    	System.out.println("Add image " + ix.getFilePath() + " to cluster-" + idx);
                        clusterWriter.addIndex(ix);
                        indexProgress.setValue(numIndex * 100 / files.length);
                        numIndex++;
                    }
                    clusterWriter.close();
                }

                notes = "SUCCESS";
                return notes;
            }

            @Override
            protected void done() {
                indexProgress.setValue(0);
                if ("FAIL".equals(notes)) {
                    //indexingStatusLabel.setText("Can't Index images because path has no images");
                   // browsePanel.setVisible(false);
                    return;
                }
                //indexingStatusLabel.setText("Finished indexing " + (numIndex - 1) + " images in " + ((System.currentTimeMillis() - time) / 1000) + " s.");
                //enabled clustId combobox
                //Integer clustSize = (Integer) numClusterCombo.getSelectedItem();
//                Integer[] nums = new Integer[clustSize];
//                for (int i = 0; i < clustSize; i++) {
//                    nums[i] = i + 1;
//                }

                //thumbIndexPanel.getListModel().clear();
               // clustIdComboBox.setModel(new DefaultComboBoxModel(nums));
                //browsePanel.setVisible(true);
                //thumbIndexPanel.setVisible(false);
                startIndexing.setEnabled(true);
                //viewDataButton.setEnabled(true);
            }
        };

        indexWorker.execute();

    	
    } // end of start
    
    
}
