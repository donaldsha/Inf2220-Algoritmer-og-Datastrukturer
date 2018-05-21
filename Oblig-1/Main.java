import java.util.Scanner;
import java.io.File;
import java.io.IOException;


class Main{
	public static void main(String[] args) {
		
		BinaryTree tree = new BinaryTree();
		Node node = new Node();

		String file = "dictionary.txt";
		tree.readFile(file);
		//tree.nodesDepth();

		Scanner inn = new Scanner(System.in);
		String sel = "";

		while(!sel.equals("q")){
			tree.menu();
			sel = inn.nextLine().trim();
			tree.menuResults(sel, inn);
			//tree.writeToFile("output.txt");
		}
		inn.close();
	}
}