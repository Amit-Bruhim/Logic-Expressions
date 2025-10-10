import java.util.Map;

/**
 * class Not is the operand that return the opposite boolean value.
 */
public class Not extends UnaryExpression {
    // constructor
    protected Not(Expression expression) {
        super(expression);
    }

    @Override
    public Expression myConstructor(Expression expression1) {
        return new Not(expression1);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(this.getExpression().evaluate(assignment));
    }

    @Override
    public boolean calculate() {
        return !(this.getExpression().calculate());
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !(this.getExpression().evaluate());
    }

    @Override
    public String toString() {
        return "~(" + this.getExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(this.getExpression().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression a = this.getExpression();
        a = a.nandify();
        return new Nand(a, a);
    }

    @Override
    public Expression norify() {
        Expression a = this.getExpression();
        a = a.norify();
        return new Nor(a, a);
    }
}
