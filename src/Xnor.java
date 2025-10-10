import java.util.Map;

/**
 * class Xnor is the operand that return false if only one expression
 * is true.
 */
public class Xnor extends BinaryExpression {

    // constructor
    protected Xnor(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        boolean leftValue = this.getLeftExpression().evaluate(assignment);
        boolean rightValue = this.getRightExpression().evaluate(assignment);
        return leftValue == rightValue;
    }

    @Override
    public boolean calculate() {
        return this.getLeftExpression().calculate()
                == this.getRightExpression().calculate();
    }

    @Override
    public Expression myConstructor(Expression expression1,
                                    Expression expression2) {
        return new Xnor(expression1, expression2);
    }

    @Override
    public Expression handleValAndVar(boolean val, Expression var) {
        return new Xnor(new Val(val), var);
    }

    @Override
    public Expression handleVarAndVAL(Expression var, boolean val) {
        return new Xnor(var, new Val(val));
    }

    @Override
    public Expression handleSameVars(Expression var) {
        return new Val(true);
    }

    @Override
    public Boolean evaluate() throws Exception {
        boolean leftValue = this.getLeftExpression().evaluate();
        boolean rightValue = this.getRightExpression().evaluate();
        return leftValue == rightValue;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression a = this.getLeftExpression();
        a = a.nandify();
        Expression b = this.getRightExpression();
        b = b.nandify();
        return new Nand(new Nand(new Nand(a, a), new Nand(b, b)), new Nand(a,
                b));

    }

    @Override
    public Expression norify() {
        Expression a = this.getLeftExpression();
        a = a.norify();
        Expression b = this.getRightExpression();
        b = b.norify();
        return new Nor(new Nor(a, new Nor(a, b)), new Nor(b, new Nor(a, b)));
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression().toString() + " # " + this.getRightExpression() + ")";
    }
}
