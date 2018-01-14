import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 0;
	static int N = 3;// No.of blocks

	public static void main(String[] args) {
		// Genesis
		blockchain.add(new Block("Genesis Block", "0"));
		System.out.println("Mining block 1... ");
		blockchain.get(0).mineBlock(difficulty);

		for (int i = 0; i < N - 1; i++) {
			blockchain.add(new Block(i + "block", blockchain.get(blockchain
					.size() - 1).hash));
			System.out.println("Mining Block " + (i + 2));
			blockchain.get(i).mineBlock(difficulty);
		}

		System.out.println("\nBlockchain is Valid: " + checkChainValid());

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create()
				.toJson(blockchain);
		System.out.println("The block chain: ");
		System.out.println(blockchainJson);
	}

	public static Boolean checkChainValid() {
		Block currentBlock;
		Block previousBlock;

		// loop through blockchain to check hashes:
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);
			// compare registered hash and calculated hash:
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			// compare previous hash and registered previous hash
			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
}