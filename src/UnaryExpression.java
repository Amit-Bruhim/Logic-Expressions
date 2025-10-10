import java.util.List;

/**
 * abstract class UnaryExpression contains all the Unary Expressions.
 */
public abstract class UnaryExpression extends BaseExpression {
    // members
    private Expression expression;

    protected UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    protected Expression getExpression() {
        return this.expression;
    }

    @Override
    public List<String> getVariables() {
        return this.getExpression().getVariables();
    }

    /**
     * build new constructor.
     *
     * @param expression1 the "inside" expression.
     * @return a new expression.
     */
    public abstract Expression myConstructor(Expression expression1);

    @Override
    public Expression simplify() {
        Expression expression1 = this.getExpression().simplify();
        if (expression1.countVars() == 0) {
            return new Val(expression1.calculate());
        } else {
            return myConstructor(expression1);
        }
    }
}
