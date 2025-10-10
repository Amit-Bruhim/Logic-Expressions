public class Main {
    // global variable for the currently selected expression
    static Expression currentExpression;
    // define colors for printing
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    /**
     * main method.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        // run until the user choose to stop the program
        while (true) {
            
            // let the user choose an expression
            chooseExpression();
        }
    }

    /**
     * let the user choose an expression
     * 
     * @return void
     */
    static void chooseExpression() {
        // create all the different options
        Expression[] expressions = createExpressions();
        // print the menu
        printMenu(expressions);
    }

    /**
     * prints the expressions menu
     * 
     * @param expressions - an array of options for different expressions
     * @return void
     */
    static void printMenu(Expression[] expressions) {
        System.out.println(GREEN + "Choose an expression:" + RESET);
    }


    /**
     * creates the expressions to choose from
     * 
     * @return array of the expressions
     */
    static Expression[] createExpressions() {
        Expression[] expressions = {
                new And(new Var("x"), new Var("x")),
                new And(
                        new Or(new Var("x"), new Not(new Var("y"))),
                        new Xnor(new Var("x"), new Var("y"))),
                new Or(
                        new And(new Var("x"), new Nand(new Var("y"), new Var("z"))),
                        new Xor(new Not(new Var("x")), new Or(new Var("y"), new Var("z"))))
        };
        return expressions;

    }
}
