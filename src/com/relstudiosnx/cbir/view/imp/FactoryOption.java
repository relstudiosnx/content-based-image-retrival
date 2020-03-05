package com.relstudiosnx.cbir.view.imp;

import com.relstudiosnx.cbir.view.containerlayout.OptionContainer;

/**
 * Implementing Index Container<br>
 * <br>
 * Date: 1/25/2013 <br>
 * Time: 2:48 PM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 * 
 */
public class FactoryOption {
	private static OptionContainer optionContainer;

	/**
	 * This method creates object of OptionContainer class
	 * 
	 * @return object
	 */
	public static OptionContainer get() {
		if (optionContainer != null) {
			return optionContainer;
		}
		return new OptionContainer();
	}
}
