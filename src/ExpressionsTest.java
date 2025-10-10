import java.util.Map;
import java.util.TreeMap;

/**
 * ID: 211548045.
 * class ExpressionsTest is a test for ass4.
 */
public class ExpressionsTest {
    /**
     * main method.
     *
     * @param args ignored.
     */
    public static void main(String[] args) throws Exception {
        // create new expression and print it
        Expression test = new And(new Or(new Var("x"), new Var("y")),
                new Var("z"));
        System.out.println(test.toString());
        // assign values to the vars and print it
        Map<String, Boolean> assignment = new TreeMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        assignment.put("z", false);
        Boolean value = test.evaluate(assignment);
        System.out.println(value);
        // print some more versions
        System.out.println(test.nandify().toString());
        System.out.println(test.norify().toString());
        System.out.println(test.simplify().toString());
    }
}
