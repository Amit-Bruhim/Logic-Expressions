import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * abstract class BinaryExpression contains all the Binary Expressions.
 */
public abstract class BinaryExpression extends BaseExpression {
    // members
    private Expression leftExpression;
    private Expression rightExpression;

    // constructor.
    protected BinaryExpression(Expression leftExpression,
                               Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    /**
     * build new constructor.
     *
     * @param expression1 the "inside" left expression.
     * @param expression2 the "inside" right expression.
     * @return a new expression.
     */
    public abstract Expression myConstructor(Expression expression1,
                                             Expression expression2);

    protected Expression getLeftExpression() {
        return this.leftExpression;
    }

    protected Expression getRightExpression() {
        return this.rightExpression;
    }

    @Override
    public List<String> getVariables() {
        // get the variables recursively from the expressions.
        List<String> l1 = new LinkedList<String>();
        l1 = this.getLeftExpression().getVariables();
        List<String> l2 = new LinkedList<String>();
        l2 = this.getRightExpression().getVariables();
        // edge cases - one of the expressions doesn't have variables.
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
            // return the mixed list.
        } else {
            List<String> l3 = new LinkedList<String>();
            l3.addAll(l1);
            l3.addAll(l2);
            return new LinkedList<String>(
                    new LinkedHashSet<>(l3));
        }
    }

    /**
     * method that handle the case which the first expression is val,
     * and the second is var.
     *
     * @param val the val.
     * @param var the var.
     * @return the new expression.
     */
    public abstract Expression handleValAndVar(boolean val,
                                               Expression var);

    /**
     * method that handle the case which the first expression is var,
     * and the second is val.
     *
     * @param var the var.
     * @param val the val.
     * @return the new expression.
     */
    public abstract Expression handleVarAndVAL(Expression var, boolean val);

    /**
     * method that handle the case which both expressions are
     * the same.
     *
     * @param var the var
     * @return the new expression.
     */
    public abstract Expression handleSameVars(Expression var);

    @Override
    public Expression simplify() {
        // simplify the expressions recursively.
        Expression expression1 = this.getLeftExpression().simplify();
        Expression expression2 = this.getRightExpression().simplify();
        // check if one of the expressions can be calculated to a boolean value.
        if (expression1.countVars() == 0 || expression2.countVars() == 0) {
            // if both are boolean - calculate the whole expression.
            if (expression1.countVars() == 0 && expression2.countVars() == 0) {
                Expression answer = this.myConstructor(expression1,
                        expression2);
                return new Val(answer.calculate());
                // if only one of them is boolean - handle it on the operand.
            } else if (expression1.countVars() == 0) {
                boolean val = expression1.calculate();
                return this.handleValAndVar(val, expression2);
            } else {
                boolean val = expression2.calculate();
                return this.handleVarAndVAL(expression1, val);
            }
            // if both are not boolean.
        } else {
            // if those are 2 separate expressions - return the same expression.
            if (!(expression1.toString().equals(expression2.toString()))) {
                return this.myConstructor(expression1, expression2);
                // if it's the same expression - handle it on the operand.
            } else {
                return this.handleSameVars(expression1);
            }
        }
    }
}

