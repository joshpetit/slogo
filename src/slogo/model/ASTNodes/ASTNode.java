package slogo.model.ASTNodes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import slogo.model.InfoBundle;

/**
 * This class's purpose is to create a template for which all future ASTNodes will have to follow
 * <p>
 * This class sets up the common instance variables that each ASTNode will use.
 *
 * @author Jiyang Tang, Oliver Rodas
 */
public abstract class ASTNode implements Serializable {

  private List<ASTNode> children;

  /**
   * The original string we parsed from to create this node
   */
  private String token = "";

  /**
   * Constructor
   */
  public ASTNode() {
    children = new ArrayList<>();
  }

  /**
   * Get the number of expected parameters
   */
  public int getNumParams() {
    return 0;
  }

  /**
   * Set the original string we parsed from to create this node
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * Get the original string we parsed from to create this node
   */
  public String getToken() {
    return token;
  }

  /**
   * An ASTNode can have multiple children, add an ASTNode as this node's child
   *
   * @return The new number of children
   */
  public int addChild(ASTNode newChild) {
    children.add(newChild);
    return children.size();
  }

  /**
   * Get child node at index `idx`
   */
  public ASTNode getChildAt(int idx) {
    return children.get(idx);
  }

  /**
   * Get current number of children of this node
   */
  public int getNumChildren() {
    return children.size();
  }

  /**
   * This method evaluates a command and returns in value most ASTNodes will call this method on
   * their children as well.
   *
   * @return The return value of evaluated method
   */
  public double evaluate(InfoBundle info) {
    preEvaluate(info);
    return doEvaluate(info, children);
  }

  /**
   * Subclass can override this method to do things before actual evaluation. For example, commands
   * can check if the number of parameters is correct here.
   */
  protected void preEvaluate(InfoBundle info) {
  }

  /**
   * Subclasses implement this method to do the actual evaluation
   */
  protected abstract double doEvaluate(InfoBundle info, List<ASTNode> params);

  /**
   * Check whether the number of children is enough for the command to execute
   */
  public abstract boolean isDone();

  /**
   * Get an immutable list of all child nodes
   */
  public List<ASTNode> getChildren() {
    return Collections.unmodifiableList(children);
  }
}