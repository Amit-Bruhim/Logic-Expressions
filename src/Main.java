import java.util.Scanner;

public class Main {
    // global variable for the currently selected expression, and all the
    // expressions
    static Expression currentExpression;
    static Expression[] expressions = {
            new And(new Var("x"), new Var("x")),
            new And(
                    new Or(new Var("x"), new Not(new Var("y"))),
                    new Xnor(new Var("x"), new Var("y"))),
            new Or(
                    new And(new Var("x"), new Nand(new Var("y"), new Var("z"))),
                    new Xor(new Not(new Var("x")), new Or(new Var("y"), new Var("z"))))
    };

    // define colors for printing
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    // create scanner
    static Scanner scanner = new Scanner(System.in);

    // define menu items
    interface MenuHandler {
        void run();
    }

    static class MenuItem {
        String name; // name of the menu option
        MenuHandler handler; // function to execute

        MenuItem(String name, MenuHandler handler) {
            this.name = name;
            this.handler = handler;
        }
    }

    /**
     * main method.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        // create a new menu
        MenuItem[] menu = createMenu();
        
        // run until the user choose to stop the program
        while (true) {
            // let the user choose an expression
            chooseExpression();

            // let the user choose an option from the menu, and run it
            selectOption(menu);
        }
    }

    /**
     * function that let the user choose an option from the menu, and run it
     * 
     * @param menu - a menu of all the legal options
     * @return void
     */
    static void selectOption(MenuItem[] menu) {
        // print the menu
        printMenu(menu);
        // takes the option from the user
        takeOption(menu);
    }

    /**
     * function that creates the menu
     * 
     * @return MenuItem[] - an array of menu item's
    */
    static MenuItem[] createMenu() {
        return new MenuItem[] {
                // Nandify
                new MenuItem("Nandify", Main::nandifyFunc),
                // Norify
                new MenuItem("Norify", Main::norifyFunc),
                // Simplify
                new MenuItem("Simplify", Main::simplifyFunc),
                // Evaluate
                new MenuItem("Evaluate", Main::evaluateFunc)
        };
    }

    static void nandifyFunc() {
        System.out.println("im func NANDIFY");
    }

    static void norifyFunc() {
        System.out.println("im func NORIFY");
    }

    static void simplifyFunc() {
        System.out.println("im func SIMPLIFY");
    }

    static void evaluateFunc() {
        System.out.println("im func EVALUATE");
    }

    /**
     * function that prints the menu
     * 
     * @param menu - the menu
     * @return void
     */
    static void printMenu(MenuItem[] menu) {
        System.out.println(GREEN + "Choose an option:" + RESET);
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i].name);
        }
    }

    /**
     * takes the option from the user
     * 
     * @param menu - an array of options
     * @return void
    */
    static void takeOption(MenuItem[] menu) {
        // variable to store the user's choice
        int number = 0;

        // flag to indicate if the input is valid
        boolean isValidInput = false;

        // loop until the user provides valid input
        while (!isValidInput) {
            try {
                // try to read an integer from the user
                number = scanner.nextInt();

                // check if the number is within the valid range (0 to expressions.length)
                if (number < 0 || number >= menu.length) {
                    System.out.println(RED + "Invalid input, try again" + RESET);
                } else {
                    // input is valid, exit the loop
                    isValidInput = true;
                }

            } catch (Exception e) {
                // if the input is not an integer, notify the user
                System.out.println(RED + "Invalid input, try again" + RESET);

                // clear the invalid input from the scanner buffer
                scanner.next();
            }
        }

        // activate the chosen function
        menu[number].handler.run();
    }

    /**
     * let the user choose an expression
     * 
     * @return void
     */
    static void chooseExpression() {
        // print the menu
        printExpressionsMenu(expressions);
        // takes the expression from the user
        takeExpression(expressions);
    }

    /**
     * takes the expression from the user
     * 
     * @param expressions - an array of options for different expressions
     * @return void
     */
    static void takeExpression(Expression[] expressions) {

        // variable to store the user's choice
        int number = 0;

        // flag to indicate if the input is valid
        boolean isValidInput = false;

        // loop until the user provides valid input
        while (!isValidInput) {
            try {
                // try to read an integer from the user
                number = scanner.nextInt();

                // check if the number is within the valid range (0 to expressions.length)
                if (number < 0 || number > expressions.length) {
                    System.out.println(RED + "Invalid input, try again" + RESET);
                } else {
                    // input is valid, exit the loop
                    isValidInput = true;
                }

            } catch (Exception e) {
                // if the input is not an integer, notify the user
                System.out.println(RED + "Invalid input, try again" + RESET);

                // clear the invalid input from the scanner buffer
                scanner.next();
            }
        }

        // if the user chose 0, exit the program
        if (number == 0) {
            scanner.close(); // close the scanner
            System.exit(0);
        } else {
            // store the selected expression in the global variable
            currentExpression = expressions[number - 1];
        }
    }

    /**
     * prints the expressions menu
     * 
     * @param expressions - an array of options for different expressions
     * @return void
     */
    static void printExpressionsMenu(Expression[] expressions) {
        System.out.println(GREEN + "Choose an expression:" + RESET);
        System.out.println("0. Exit");
        for (int i = 0; i < expressions.length; i++) {
            System.out.println((i + 1) + ". " + expressions[i].toString());
        }
    }
}
