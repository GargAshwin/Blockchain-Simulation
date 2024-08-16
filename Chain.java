/**
 * This class represents a blockchain, which is a linked list of blocks. Each block contains
 * a reference to the previous block and a cryptographic hash that links it to the rest of the chain.
 * The chain starts with a head block, and blocks can be added to the end of the chain.
 * @author Ashwin Garg
 * @version 20.0.2
 */
public class Chain {
    private Block head;
    private int size;
    private static int difficulty = 5;
    private static final int CHAIN_SIZE = 50; //if we have more than 50 blocks, we will will remove the head of the linked list

    /**
     * Constructs an empty blockchain with no blocks.
     * The size is initialized to 0, and the head is set to null.
     */
    public Chain(){
        this.head = null;
        this.size = 0;
    }

    /**
     * Inserts a new block at the end of the blockchain. If the chain is empty,
     * the block is added as the head. The block's previous hash is set to the hash of the
     * previous block, and it is mined based on the difficulty level.
     * @param toAdd The block to be added to the blockchain.
     */
    public void insert(Block toAdd){
        if(this.head == null){
            this.head = toAdd;
            toAdd.setPrevHash("0");
            toAdd.setHash(Block.calculate_Hash(toAdd.getPrevHash() + toAdd.getTransaction().getDate().getTime() + toAdd.getTransaction().get_Transaction_Data()));
            toAdd.mine(difficulty);
        }
        else{
            if(size + 1 > CHAIN_SIZE){ //size check
                this.head = head.getNext();
                this.head.setPrev(null);
                this.head.setPrevHash("0");
                size--;
            }
            Block ptr = head;
            while(ptr.getNext() != null){
                ptr = ptr.getNext();
            }
            ptr.setNext(toAdd);
            toAdd.setPrev(ptr);
            toAdd.setPrevHash(ptr.getHash());
            toAdd.setHash(Block.calculate_Hash(toAdd.getPrevHash() + toAdd.getTransaction().getDate().getTime() + toAdd.getTransaction().get_Transaction_Data()));
            toAdd.mine(difficulty);
        }
        this.size++;
        return;
    }

    /**
     * Prints the entire blockchain, displaying each block's details in sequence.
     * Each block's string representation is printed, including its hash, transaction data,
     * and the hash of the previous block.
     */
    public void printChain(){
        for(Block ptr = head; ptr != null; ptr = ptr.getNext()){
            System.out.print(ptr.toString());
            System.out.println();
            System.out.println();
        }
    }
}
