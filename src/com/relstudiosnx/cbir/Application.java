package com.relstudiosnx.cbir;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.relstudiosnx.cbir.view.View;
import com.relstudiosnx.cbir.view.ViewFactory;

/**
 * Factory for view class<br>
 * <br>
 * Date: 2/13/2013 <br>
 * Time: 2:55 AM
 * 
 * @author relstudiosnx, relstudiosdev@gmail.com
 **/
public class Application {
	/**
	 * Main method, fire up !! :)
	 * 
	 * @param args
	 */
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				View view = ViewFactory.get();
				//Model model = new Model();
				//Presenter presenter = new Presenter(view, model);
				//view.setPresenter(presenter);

				view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				view.setResizable(false);
				view.setVisible(true);
			}
		});
	}
}
