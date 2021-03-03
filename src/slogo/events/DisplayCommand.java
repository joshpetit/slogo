package slogo.events;

/**
 * The state of a command within the environment at a specific instance.
 */
public record DisplayCommand(String name,String signature){
  public static final String NAME = "name";
  public static final String SIGNATURE = "value";

  public String getName() {
    return name;
  }

  public String getSignature() {
    return signature;
  }
}
