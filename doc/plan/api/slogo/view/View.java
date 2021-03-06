package slogo.view;

import javafx.stage.Stage;
import slogo.model.EnvironmentFactory;
import slogo.model.TrackableEnvironment;

/**
 * @author Joshua Pettima
 * @author marthaaboagye
 * This class coordinates between all other classes
 * from the view package. It needs to be initialized with a stage and a model
 * controller object.  It also contains a private View environmentInfo class
 * that implements the view controller and allows the user to change the
 *  color for the pen and background.
 */
public class View {
	ModelController modelCon;
	TrackableEnvironment environment;
	ViewController viewCon;

	/**
	 * This is teh constructor for the View class.
	 * It needs to be initIalized with a stage which
	 * contains all the nodels to display and a model controller
	 * to allow the view to pass commands to the mode.
	 * @param stage
	 * @param modelCon
	 */
	public View(Stage stage, ModelController modelCon) {
		this.modelCon = modelCon;
		this.environment = EnvironmentFactory.createEnvironment();
		viewCon = new ViewBundle();
		modelCon.setController(viewCon);
	}

	/**
	 * This class implements the ViewController class. It
	 * implements setBackground and setLanguages methods for when a user changes
	 * the background color and the language for the display.
	 */

	private class ViewBundle implements ViewController {

		/**
		 * This method sets the background color to what the
		 * user picked from the display window.
		 * @param color - The color to set the background to.
		 */
		public void setBackground(String color) {

		}

		/**
		 * This method sets the display language to the user's
		 * input.
		 * @param lang - The language locale to use.
		 */
		public void setLanguage(String lang) {

		}


		/**
		 * This method sets the color of turtle's pen to the
		 * the color the user picked from the display window
		 * @param color - the color to set the background to.
		 */
		public void setPenColor(String color) {

		}

		/**
		 * This method sets the turtle logo to be displayed.
		 * @param turtleLogo - the turtle logo the user picked.
		 */
		public void setTurtleLogo(String turtleLogo) {

		}

		public void sendAlert(String title, String message) {

		}
	}
}
