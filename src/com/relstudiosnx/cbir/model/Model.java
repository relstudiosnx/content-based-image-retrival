package com.relstudiosnx.cbir.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//import org.apache.log4j.Logger;

//import com.relstudiosnx.cbir.model.imageanalysis.GaborFilter;
import com.relstudiosnx.cbir.model.utils.FileReader;

/**
 * Model Class<br>
 * <br>
 * Date: 2/16/2013 <br>
 * Time: 6:15 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 **/
public class Model {

	//private static Logger logger = Logger.getLogger(Model.class);

	/**
	 * Extract all the features of each images
	 * 
	 * @param filePath
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void extract(String filePath) throws FileNotFoundException,
			IOException {
		// read the images from folder
		// filter the image
		// extract the feature

		File file = new File(filePath);
		ArrayList<String> allFiles;
		//GaborFilter gaborFilter = new GaborFilter();

		allFiles = FileReader.readImages(file, true);
		for (String eachFile : allFiles) {
			
			BufferedImage image = ImageIO.read(new FileInputStream(eachFile));
			//gaborFilter.extract(image);
			//logger.info("sch = " + gaborFilter.getStringRepresentation());
		}
	}

}
