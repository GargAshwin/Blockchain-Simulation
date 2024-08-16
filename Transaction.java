import java.util.Date;

/**
 * This class represents a transaction in the blockchain. Each transaction has an ID, a type,
 * a date when it was created, and an associated amount. The total number of transactions is
 * tracked using a static counter.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Transaction {
    private int transaction_ID;
    private String transaction_type;
    private Date date;
    private double amount;
    public static int numTransactions = 0;

    /**
     * Constructs a new Transaction with the specified amount and type. The transaction is assigned
     * a unique ID based on the number of transactions created so far, and the date is set to the
     * current date and time.
     * @param amount amount The amount involved in the transaction.
     * @param transaction_type transaction_type The type of transaction (e.g., "credit", "debit").
     */
    public Transaction(double amount, String transaction_type){
        this.transaction_ID = numTransactions;
        this.date = new Date();
        this.amount = amount;
        this.transaction_type = transaction_type;
        Transaction.numTransactions++;
    }

    /**
     * Returns a string representing the core data of the transaction, including the transaction ID,
     * type, and amount. This is typically used for generating a hash of the transaction.
     * @return A string representation of the transaction data.
     */
    public String get_Transaction_Data(){
        return "" + this.transaction_ID + this.transaction_type + this.amount;
    }

    /**
     * Returns a string representation of the transaction, including its ID, type, amount, and the date
     * when it was made.
     * @return A string representation of the transaction.
     */
    public String toString(){
        return "\"Transaction_ID\": " + this.transaction_ID + "\n\"Transaction Type\": " + this.transaction_type +
                "\n\"Transaction Amount\": " + this.amount + "\n\"Date Made\": " + this.date.toString() + "\n";
    }

    /**
     * Returns the date when the transaction was created.
     * @return The date of the transaction.
     */
    public Date getDate(){
        return date;
    }
}
