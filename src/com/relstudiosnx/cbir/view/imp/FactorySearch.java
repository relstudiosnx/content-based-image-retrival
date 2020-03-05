package com.relstudiosnx.cbir.view.imp;

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
public class FactorySearch {
	private static SearchContainer searchContainer;

	/**
	 * This method creates the object of SearchContainer Class
	 * 
	 * @return object
	 */
	public static SearchContainer get() {
		if (searchContainer != null) {
			return searchContainer;
		}
		return new SearchContainer();
	}
}
