package com.relstudiosnx.cbir.model.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class reads all the images files form directory/within directory<br>
 * <br>
 * Date: 1/30/2013 <br>
 * Time: 10:48 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
public class FileReader {
		
	/**
	 * 
	 * @param file
	 * @return path of the image file
	 * @throws IOException
	 */
	public static  ArrayList<String> readImages(File file, boolean isTraverse) throws IOException {
		// declearing the arrylist to store the file path
		ArrayList<String> fileName = new ArrayList<String>(200);
		File[] allFiles = file.listFiles();

		for (File singleFile : allFiles) {
			if (singleFile != null
					&& (singleFile.getName().toLowerCase().endsWith("jpg")
							|| singleFile.getName().toLowerCase()
									.endsWith("png") || singleFile.getName()
							.toLowerCase().endsWith("jpeg")))
				fileName.add(singleFile.getCanonicalPath());

			if (isTraverse && singleFile.isDirectory()) {
				// temporary arraylist to store the file path of sub folder if
				// exists
				ArrayList<String> tempSubFiles = readImages(singleFile, true);
				assert (tempSubFiles != null);
				fileName.addAll(tempSubFiles);
			}
		}
		return fileName;
	}
}
