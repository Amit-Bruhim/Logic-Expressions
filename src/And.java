import java.util.Map;

/**
 * class And is the operand that return true only if both expressions
 * are true.
 */
public class And extends BinaryExpression {

    // constructor
    protected And(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public Expression myConstructor(Expression expression1,
                                    Expression expression2) {
        return new And(expression1, expression2);
    }

    @Override
    public Expression handleValAndVar(boolean val, Expression var) {
        if (val) {
            return var;
        } else {
            return new Val(false);
        }
    }

    @Override
    public Expression handleVarAndVAL(Expression var, boolean val) {
        if (val) {
            return var;
        } else {
            return new Val(false);
        }
    }

    @Override
    public Expression handleSameVars(Expression var) {
        return var;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        boolean leftValue = this.getLeftExpression().evaluate(assignment);
        boolean rightValue = this.getRightExpression().evaluate(assignment);
        return leftValue && rightValue;
    }

    @Override
    public Boolean evaluate() throws Exception {
        boolean leftValue = this.getLeftExpression().evaluate();
        boolean rightValue = this.getRightExpression().evaluate();
        return leftValue && rightValue;
    }

    @Override
    public boolean calculate() {
        return this.getLeftExpression().calculate()
                & this.getRightExpression().calculate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new And(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression a = this.getLeftExpression();
        a = a.nandify();
        Expression b = this.getRightExpression();
        b = b.nandify();
        return new Nand(new Nand(a, b), new Nand(a, b));
    }

    @Override
    public Expression norify() {
        Expression a = this.getLeftExpression();
        a = a.norify();
        Expression b = this.getRightExpression();
        b = b.norify();
        return new Nor(new Nor(a, a), new Nor(b, b));
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression().toString() + " & "
                + this.getRightExpression() + ")";
    }
}
