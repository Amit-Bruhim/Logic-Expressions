import java.util.List;
import java.util.Map;

/**
 * interface that contain all the methods that all the expressions have.
 */
public interface Expression {


    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment the map that matches vars to boolean values.
     * @return the boolean value of the expression
     * @throws Exception if the key is not exist
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;


    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return the boolean value of the expression.
     * @throws Exception if the key is not exist.
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return the list.
     */
    List<String> getVariables();

    /**
     * method that counts the amount of vars in the expression.
     *
     * @return the amount of vars in the expression.
     */
    int countVars();

    /**
     * method that evaluate the value of the expression.
     *
     * @return the value of the expression.
     */
    boolean calculate();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return the expression as a string.
     */
    String toString();


    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        the var that should be replaced.
     * @param expression the expression that replace the var.
     * @return the new expression after the replacement.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nand operation.
     *
     * @return the expression
     */
    Expression nandify();


    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nor operation.
     *
     * @return the expression.
     */
    Expression norify();

    /**
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}