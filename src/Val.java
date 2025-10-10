import java.util.List;
import java.util.Map;

/**
 * class Val contains a boolean value - true or false.
 */
public class Val implements Expression {
    // members
    private Boolean bool;

    /**
     * constructor.
     *
     * @param bool the boolean value.
     */
    public Val(boolean bool) {
        this.bool = bool;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.bool;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.bool;
    }

    @Override
    public boolean calculate() {
        return this.bool;
    }

    @Override
    public String toString() {
        if (bool) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
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
    public Expression simplify() {
        return this;
    }

    @Override
    public List<String> getVariables() {
        return null;
    }

    @Override
    public int countVars() {
        return 0;
    }
}
