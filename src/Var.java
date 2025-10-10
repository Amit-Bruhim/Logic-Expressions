import java.util.List;
import java.util.Map;
import java.util.LinkedList;


/**
 * class Var contains a string.
 */
public class Var implements Expression {

    // members
    private String str;

    /**
     * constructor.
     *
     * @param str string.
     */
    public Var(String str) {
        this.str = str;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (!assignment.containsKey(this.str)) {
            throw new Exception("Var is not found");
        } else {
            return assignment.get(this.str);
        }
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Var is not found");
    }

    @Override
    public boolean calculate() {
        return true;
    }

    @Override
    public String toString() {
        return this.str;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.str.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public List<String> getVariables() {
        List<String> l = new LinkedList<String>();
        l.add(this.str);
        return l;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public int countVars() {
        return 1;
    }
}
