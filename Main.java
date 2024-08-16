import java.util.Scanner;
/**
 * This class serves as the entry point for the blockchain simulation. It allows the user to create
 * transactions, insert them into the blockchain, and print the entire blockchain to the console.
 * The user interacts with the program via a simple text menu.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Main {
    public static void main(String[] args) {
        Chain chain = new Chain();

        int choice = -1;
        while(choice != 3){
            choice = get_Valid_Choice();
            if(choice == 1){
                Transaction temp = get_Valid_Transaction();
                System.out.println("Transaction Created. Mining Block...");
                chain.insert(new Block(temp));
            }
            if(choice == 2){
                chain.printChain();
                System.out.println();
            }
        }
    }
    /**
     * Displays the main menu to the user with options to enter a transaction,
     * print the blockchain, or exit the simulation.
     */
    public static void print_Menu(){
        System.out.println("Press: ");
        System.out.println("1 : Enter a Transaction");
        System.out.println("2 : Print Blockchain");
        System.out.println("3 : Exit Simulation");
    }
    /**
     * Prompts the user to input a valid menu choice and returns the user's choice.
     * It repeatedly prompts the user until a valid choice (1-3) is entered.
     * @return The valid choice selected by the user.
     */
    public static int get_Valid_Choice(){
        Scanner sc = new Scanner(System.in);
        String input = "";
        int choice = 0;
        boolean is_Valid_Choice = false;
        while(is_Valid_Choice == false){
            print_Menu();
            System.out.print("Choice: ");
            input = sc.nextLine();
            if(validate_Input(input) == false){
                System.out.println("Error: Please enter a digit from [1-3]");
                System.out.println();
            }
            else{
                choice = Integer.parseInt(input);
                is_Valid_Choice = true;
            }
        }
        return choice;
    }

    /**
     * Validates the user's input to ensure it is a valid choice (1-3).
     * @param input The input string to validate.
     * @return True if the input is a valid choice, false otherwise.
     */
    public static boolean validate_Input(String input){
        try{
            int digit = Integer.parseInt(input);
            if(1 <= digit && digit <= 3){
                return true;
            }
            return false;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Prompts the user to enter a valid transaction amount and type,
     * and returns a new Transaction object with the specified details.
     * @return A valid Transaction object created by the user.
     */
    public static Transaction get_Valid_Transaction(){
        Scanner sc = new Scanner(System.in);
        double amount = get_Valid_Transaction_Amount();
        System.out.print("Enter transaction type: ");
        String transaction_Type = sc.nextLine();
        return new Transaction(amount, transaction_Type);
    }

    /**
     * Validates the user's input to ensure it is a valid transaction amount.
     * A valid transaction amount is a non-negative decimal number.
     * @param amount amount The input string to validate.
     * @return True if the input is a valid transaction amount, false otherwise.
     */
    public static boolean validate_Transaction_Amount(String amount){
        try{
            double amt = Double.parseDouble(amount);
            if(amt >= 0.00){
                return true;
            }
            return false;
        }catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Prompts the user to input a valid transaction amount and returns it.
     * It repeatedly prompts the user until a valid amount is entered.
     * @return The valid transaction amount entered by the user.
     */
    public static double get_Valid_Transaction_Amount(){
        Scanner sc = new Scanner(System.in);
        String input = "";
        double amount = 0.00;
        boolean is_Valid_Amount = false;
        while(is_Valid_Amount == false){
            System.out.print("Enter an Amount: ");
            input = sc.nextLine();
            if(validate_Transaction_Amount(input) == false){
                System.out.println("Error: Please enter a valid transaction amount");
                System.out.println();
            }
            else{
                amount = Double.parseDouble(input);
                is_Valid_Amount = true;
            }
        }
        return amount;
    }
}