package slogo.view;

import slogo.model.TrackableEnvironment;

/**
 * @author Joshua Pettima
 * @author Martha Aboagye
 * This class contains methods needed for
 * the view to talk to the  our model.
 * The class assumes/depends on  a trackable environment object.
 * Trackable environment is the backend external interface.
 * In order for the view
 * to send user request actions to the backened to execute,
 * it calls the methods available from the trackable environment
 * object.  An example of how to use this class is below:
 * ModelController model = new ModelController();
 * model.sendCommand("Forward 50");
 *
 */

public class ModelController {
  TrackableEnvironment env;

  /**
   * This is the constryctor for the model controller class
   * As of now, it doesn't meed to be initialized with any
   * specific obkects. It is an empty method
   */
  public ModelController() {}

  /**
   * This method initializes a trackable environment object which
   * allows the view to talk to the model/pass user actions
   * to the model to interpret.
   * @param env is a trackable environment object.
   */
  public void setModel(TrackableEnvironment env) {
    this.env = env;
  }

  /**
   * This method sends the text the user
   * enters through the view to the model
   * to parse and execute. If this method is called
   * before a trackable environment is initialized,
   * it throws an error.
   *
   * @param command
   * @throws Exception
   */
  public void sendCommand(String command) throws Exception {
    if (env != null) {
      env.runCommand(command);
    } else {
      throw new Exception("NO CODING ENVIRONMENT HERE DUEEHUDWQHFQWFCQWF");
    }
  }
}