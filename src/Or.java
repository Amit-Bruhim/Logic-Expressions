import java.util.Map;

/**
 * class Or is the operand that return true if at least one expression
 * is true.
 */
public class Or extends BinaryExpression {

    // constructor
    protected Or(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        boolean leftValue = this.getLeftExpression().evaluate(assignment);
        boolean rightValue = this.getRightExpression().evaluate(assignment);
        return leftValue || rightValue;
    }

    @Override
    public Boolean evaluate() throws Exception {
        boolean leftValue = this.getLeftExpression().evaluate();
        boolean rightValue = this.getRightExpression().evaluate();
        return leftValue || rightValue;
    }

    @Override
    public boolean calculate() {
        return this.getLeftExpression().calculate()
                || this.getRightExpression().calculate();
    }

    @Override
    public Expression myConstructor(Expression expression1,
                                    Expression expression2) {
        return new Or(expression1, expression2);
    }

    @Override
    public Expression handleValAndVar(boolean val, Expression var) {
        if (val) {
            return new Val(true);
        } else {
            return var;
        }
    }

    @Override
    public Expression handleVarAndVAL(Expression var, boolean val) {
        if (val) {
            return new Val(true);
        } else {
            return var;
        }
    }

    @Override
    public Expression handleSameVars(Expression var) {
        return var;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression a = this.getLeftExpression();
        a = a.nandify();
        Expression b = this.getRightExpression();
        b = b.nandify();
        return new Nand(new Nand(a, a), new Nand(b, b));
    }

    @Override
    public Expression norify() {
        Expression a = this.getLeftExpression();
        a = a.norify();
        Expression b = this.getRightExpression();
        b = b.norify();
        return new Nor(new Nor(a, b), new Nor(a, b));
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression().toString() + " | " + this.getRightExpression() + ")";
    }
}
