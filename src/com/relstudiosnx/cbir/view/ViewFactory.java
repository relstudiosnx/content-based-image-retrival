package com.relstudiosnx.cbir.view;

import com.relstudiosnx.cbir.view.imp.FactoryIndex;
import com.relstudiosnx.cbir.view.imp.FactoryOption;
import com.relstudiosnx.cbir.view.imp.FactorySearch;

/**
 * Factory for view class<br>
 * <br>
 * Date: 2/13/2013 <br>
 * Time: 2:55 AM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
public class ViewFactory {
	private static View view;

	/**
	 * This static method creates object of View Class
	 * 
	 * @return object of view class
	 */
	public static View get() {
		if (view != null) {
			return view;
		}
		return new View(FactoryIndex.get(),
				FactorySearch.get(), FactoryOption.get());
	}
}
