package com.relstudiosnx.cbir.view.imp;

import com.relstudiosnx.cbir.view.containerlayout.IndexContainer;
/**
 * Implementing Index Container<br>
 * <br>
 * Date: 1/25/2013 <br>
 * Time: 2:48 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
public class FactoryIndex {
	private static IndexContainer indexContainer;
	
	
	public static IndexContainer get() {
		if (indexContainer != null) {
			return indexContainer;
		}
		return new IndexContainer();
	}
}
