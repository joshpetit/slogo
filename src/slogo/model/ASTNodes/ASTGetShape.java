package slogo.model.ASTNodes;

import java.util.List;
import slogo.model.InfoBundle;

public class ASTGetShape extends ASTCommand {

  private static final int NUM_PARAMS = 0;
  private static final String NAME = "GetShape";

  public ASTGetShape() {
    super(NAME, NUM_PARAMS);
  }

  @Override
  protected double doEvaluate(InfoBundle info, List<ASTNode> params) {
    return info.getShapeIdx();
  }
}
