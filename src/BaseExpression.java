/**
 * class BaseExpression contain all kinds of expressions that are not value
 * or variables.
 */
public abstract class BaseExpression implements Expression {
    @Override
    public int countVars() {
        return this.getVariables().size();
    }
}
