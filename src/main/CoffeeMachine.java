package main;

import java.util.Scanner;

public class CoffeeMachine {

    enum State {
        MENU,
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT,
        FAULT;

        /**
         * Changes the state of the Coffee machine based on it's current state or the user's input
         *
         * @param choice User's input
         * @return Coffee Machine's new State
         */
        public State changeState(String choice) {
            switch (choice) {
                case "buy":
                    return BUY;
                case "fill":
                    return FILL;
                case "take":
                    return TAKE;
                case "remaining":
                    return REMAINING;
                case "exit":
                    return EXIT;
                default:
                    return MENU;

            }
        }
    }

    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int cups = 9;
    private int cash = 550;
    private State state = State.MENU;

    /**
     * Unique function to handle any and all inputs needed
     *
     * @return String user's input
     */
    private String inputHandler() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    /**
     * Outputs a message based on the Coffee Machine's state
     */
    private void printMessage() {
        switch (state) {

            case MENU:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                System.out.print("> ");
                break;
            case BUY:
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                System.out.print("> ");
                break;
            case TAKE:
                System.out.println("\nI gave you $" + cash);
                break;
            case REMAINING:
                System.out.println("\n" + water + " of water\n" + milk + " of milk\n" + coffeeBeans + " of coffee beans\n" + cups + " of disposable cups\n" + "$" + cash + " of money");
                break;
            default:
                System.out.println("Test");
        }
    }

    /**
     * Performs actions based on the user input in the buying menu
     */
    private void buy() {
        printMessage();
        String userInput = inputHandler();  //necessary to use a variable here in order for the cups check to be done after the user inputs his choice rather than before if not used.
        if (cups >= 1) {
            switch (userInput) {
                case "1":
                    if (water < 250) {
                        System.out.println("Sorry, not enough water!");
                    } else if (coffeeBeans < 16) {
                        System.out.println("Sorry, not enough coffee!");
                    } else {
                        water -= 250;
                        coffeeBeans -= 16;
                        cups--;
                        cash += 4;
                        System.out.println("I have enough resources, making you a coffee!");
                    }
                    break;
                case "2":
                    if (water < 350) {
                        System.out.println("Sorry, not enough water!");
                    } else if (milk < 75) {
                        System.out.println("Sorry, not enough milk!");
                    } else if (coffeeBeans < 20) {
                        System.out.println("Sorry, not enough coffee!");
                    } else {
                        water -= 350;
                        milk -= 75;
                        coffeeBeans -= 20;
                        cups--;
                        cash += 7;
                        System.out.println("I have enough resources, making you a coffee!");
                    }
                    break;
                case "3":
                    if (water < 200) {
                        System.out.println("Sorry, not enough water!");
                    } else if (milk < 100) {
                        System.out.println("Sorry, not enough milk!");
                    } else if (coffeeBeans < 12) {
                        System.out.println("Sorry, not enough coffee!");
                    } else {
                        water -= 200;
                        milk -= 100;
                        coffeeBeans -= 12;
                        cups--;
                        cash += 6;
                        System.out.println("I have enough resources, making you a coffee!");
                    }
                    break;
                case "back":
                    break;
                default:
                    state = State.FAULT;
            }
        } else {
            System.out.println("Sorry, not enough cups!");
        }
    }

    /**
     * Performs the filling action based on the state of the machine and the input of the user
     */
    private void fill() {

        System.out.println("\nWrite how many ml of water do you want to add:");
        System.out.print("> ");
        water += Integer.parseInt(inputHandler());

        System.out.println("Write how many ml of milk do you want to add:");
        System.out.print(">");
        milk += Integer.parseInt(inputHandler());

        System.out.println("Write how many grams of coffee beans do you want to add:");
        System.out.print(">");
        coffeeBeans += Integer.parseInt(inputHandler());

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        System.out.print(">");
        cups += Integer.parseInt(inputHandler());
    }

    /**
     * Calls upon a function based on the Coffee Machine's state after the user's input
     */
    private void performAction() {
        switch (state) {
            case BUY:
                buy();
                state = state.changeState("");
                break;
            case FILL:
                fill();
                state = state.changeState("");
                break;
            case TAKE:
                printMessage();
                cash -= cash;
                state = state.changeState("");
                break;
            case REMAINING:
                printMessage();
                state = state.changeState("");
                break;
        }
    }

    /**
     * Starts the Coffee Machine
     */
    public void init() {

        while (!state.equals(State.EXIT)) {
            printMessage();
            state = state.changeState(inputHandler());
            performAction();
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {

        CoffeeMachine machine = new CoffeeMachine();

        machine.init();
    }
}
