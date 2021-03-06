package slogo.model.ASTNodes;

import java.util.List;
import slogo.model.InfoBundle;

/**
 * Represents a function call, is stored in the function table
 * <p>
 * Children:
 * <ol>
 *   <li>Actual parameter 1</li>
 *   <li>Actual parameter 2</li>
 *   <li>...</li>
 * </ol>
 *
 * @author Jiyang Tang, Oliver Rodas
 */
public class ASTFunctionCall extends ASTCommand {

  private List<String> parameterNames;
  private ASTNode body;

  /**
   * Constructor
   *
   * @param identifier     Name of the function being called
   * @param parameterNames Parameter names
   */
  public ASTFunctionCall(String identifier, List<String> parameterNames) {
    super(identifier, parameterNames.size());
    this.parameterNames = parameterNames;
  }

  public ASTFunctionCall(String identifier, List<String> parameterNames, ASTNode body) {
    super(identifier, parameterNames.size());
    this.parameterNames = parameterNames;
    this.body = body;
  }

  /**
   * Set the commands in function body
   */
  public void setBody(ASTNode body) {
    this.body = body;
  }

  @Override
  protected double doEvaluate(InfoBundle info, List<ASTNode> params) {
    // insert actual parameters into the lookup table
    for (int i = 0; i < getNumParams(); ++i) {
      ASTNumberLiteral value = new ASTNumberLiteral(params.get(i).evaluate(info));
      info.setVariable(parameterNames.get(i), value);
    }

    return body.evaluate(info);
  }

  public ASTFunctionCall clone() {
    return new ASTFunctionCall(getName(), parameterNames, body);
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append(getName()).append(" [");

    // parameters
    int nParams = parameterNames.size();
    for (int i = 0; i < nParams; ++i) {
      ret.append(parameterNames.get(i));
      if (i < nParams - 1) {
        ret.append(", ");
      }
    }

    ret.append("]");
    return ret.toString();
  }

  /**
   * Create a reference of this function, so that we can implement recursion more easily
   */
  public ASTNode createReference() {
    return new ASTFunctionReference(getName(), getNumParams());
  }
}
