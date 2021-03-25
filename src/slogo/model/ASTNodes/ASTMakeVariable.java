package slogo.model.ASTNodes;

import java.util.List;
import slogo.model.InfoBundle;

/**
 * Children:
 * <ol>
 *   <li>Variable name</li>
 *   <li>Expression</li>
 * </ol>
 */
public class ASTMakeVariable extends ASTDeclaration {

  private static final int NUM_PARAMS = 2;
  private static final String NAME = "MakeVariable";

  public ASTMakeVariable() {
    super(NAME, NUM_PARAMS);
  }

  @Override
  protected double doEvaluate(InfoBundle info, List<ASTNode> params) {
    double value = 0;
    for (int i = 0; i < getNumChildren(); i+=getNumParams()) {
        String name = ((ASTNamed) params.get(i)).getName();
        setIdentifier(name);

        value = params.get(i + 1).evaluate(info);
        ASTNumberLiteral numberLiteral = new ASTNumberLiteral(value);
        info.setVariable(name, numberLiteral);
    }

    return value;
  }
}
