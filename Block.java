import java.security.MessageDigest;
/**
 * This class represents a doubly-linked list implementation of a "block". A block is a data structure
 * within a blockchain that contains a list of validated transactions, a reference to
 * the previous block, and a unique cryptographic hash, linking it to the rest of the chain.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Block {
    private Block next;
    private Block prev;
    private Transaction transaction;
    private String prevHash;
    private String hash;

    /**
     * Constructs a Block instance. A transaction is needed for initialization (every block has a transaction).
     * @param t The transaction associated with this block.
     */
    public Block(Transaction t){
        this.transaction = t;
    }

    /**
     * Generates a SHA-256 hash for the given input string.
     * @param input input The string input to be hashed.
     * @return The SHA-256 hash of the input string in hexadecimal format.
     * @see <a href="https://www.baeldung.com/sha-256-hashing-java">Baeldung: SHA-256 Hashing in Java</a>
     */
    public static String calculate_Hash(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input,
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the current hash of this block.
     * @return The current hash of this block.
     */
    public String getHash(){
        return hash;
    }

    /**
     * Sets the hash of this block.
     * @param hash hash The hash to be set for this block.
     */
    public void setHash(String hash){
        this.hash = hash;
    }

    /**
     * Returns the hash of the previous block in the chain.
     * @return The hash of the previous block.
     */
    public String getPrevHash(){
        return prevHash;
    }

    /**
     * Sets the hash of the previous block in the chain.
     * @param prevHash prevHash The hash to be set for the previous block.
     */
    public void setPrevHash(String prevHash){
        this.prevHash = prevHash;
    }

    /**
     * Returns the transaction associated with this block.
     * @return The transaction associated with this block.
     */
    public Transaction getTransaction(){
        return transaction;
    }

    /**
     * Returns the next block in the chain.
     * @return The next block in the chain.
     */
    public Block getNext(){
        return next;
    }

    /**
     * Sets the next block in the chain.
     * @param next The block to be set as the next block in the chain.
     */
    public void setNext(Block next){
        this.next = next;
    }

    /**
     * Returns the previous block in the chain.
     * @return The previous block in the chain.
     */
    public Block getPrev(){
        return prev;
    }

    /**
     * Sets the previous block in the chain.
     * @param prev The block to be set as the previous block in the chain.
     */
    public void setPrev(Block prev){
        this.prev = prev;
    }

    /**
     * Returns a string representation of the block, including its hash, transaction data,
     * and the hash of the previous block.
     * @return A string representation of the block.
     */
    public String toString(){
        return "HASH: " + this.hash +
                "\nTransaction:\n" + this.transaction.toString()
                + "PREV_HASH: " + prevHash;
    }

    /**
     * Mines the block by finding a hash that meets the difficulty criteria.
     * The difficulty determines the number of leading zeros required in the hash.
     * Please note, the difficulty is a static variable that is set in the Chain class.
     * @param difficulty difficulty The number of leading zeros required in the block's hash.
     */
    public void mine(int difficulty){
        String target = "";
        for(int i = 0; i < difficulty; i++){
            target += "0";
        }
        int nonce = 0;
        while(!(this.hash.substring(0,difficulty)).equals(target)){
            this.hash = calculate_Hash(this.prevHash + this.transaction.getDate().getTime() + (++nonce) + this.transaction.get_Transaction_Data());
        }
        System.out.println("Block Mined!");
        System.out.println();
    }
}