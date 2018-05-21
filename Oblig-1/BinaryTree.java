import java.util.Scanner;
import java.io.*;
import java.io.IOException;


class BinaryTree{

	Node root; //Horte paa videoer paa youtube som hjulpet meg med aa komme paa en loesning

	char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	FileWriter writer = null;
	
	public BinaryTree(){
		root=null;
	}

	public void addNode(String word){//insert en ny node
		if (root == null) {
			root = new Node(word);
		}else{
			root.addNode(word);
		}
	}

	public String searchNode(String word){
		if (root != null) {
			return root.searchNode(word);
		}
		return null;
	}


	public int right(Node node, int depth){//lagde den metoden for å finne tree dybde siden den forrige ikke fungerte
		node.depth1 = depth;

		if (node.rightChild != null) {
			node = node.rightChild;
			depth++;
			int depthToRight = right(node, depth);
			int depthToLeft = left(node, depth);

			depth = Math.max(depth, Math.max(depthToLeft, depthToRight));
		}
		return depth;
	}

	public int left(Node node, int depth){//lagde den metoden for å finne tree dybde siden den forrige ikke fungerte
		node.depth1 = depth;

		if (node.leftchild != null) {
			node = node.leftchild;
			depth++;
			int depthToRight = right(node, depth);
			int depthToLeft = left(node, depth);

			depth = Math.max(depth, Math.max(depthToLeft, depthToRight));
		}
		return depth;
	}

	public int maxDepth(){//finner tre dybden
		int depth = 0;
		if (root != null) {
			Node node = root;
		 depth = Math.max(left(node, depth), right(node, depth));
		}
		//System.out.println("maxDepth: " + depth);
		return depth;
	}

	public int[] nodesDepth(){
		int[] nodeCount = new int[maxDepth()+1];
		Node curr = root;
		root.depth = nodeCount;
		addToNodeDepth(curr, nodeCount);

		//System.out.println("NodesDepth: " + nodeCount);
		return nodeCount;
	}

	public void addToNodeDepth(Node node, int[] depth){
		if (node != null) {
			depth[node.depth1]++;

			addToNodeDepth(node.leftchild, depth);
			addToNodeDepth(node.rightChild, depth);
		}
	}

	public double avgDepth(){
		int[] nodesDepth = nodesDepth();
		double avg = 0;
		int total = 0;
		int avgdepNodeTotal = 0;
		for (int i = 0;i<nodesDepth.length;i++ ) {
			total += nodesDepth[i];
			avgdepNodeTotal += nodesDepth[i]*i;
		}
		avg = (avgdepNodeTotal * 1.0) / (total * 1.0);
		//System.out.println("Total nodes are: " + total);
		return avg;
	}

/*prøvde med den her ogsaa men jeg fikk litt problemer
	public int nodeCount(){
		return countNodes(root);
	}

	public int countNodes(Node nodes){
		if (nodes == null) {
			return 0;
		}
		return 1 + countNodes(nodes.leftchild) + countNodes(nodes.rightChild);
	}

	public int totalTreeDepth(){
		return treeDepth(root, 0);
	}

	public int treeDepth(Node nodes, int depth){
		if (nodes == null) {
			return 0;
		}
		return depth + treeDepth(nodes.leftchild, depth +1) + treeDepth(nodes.rightChild, depth +1); 
	}

	public int[] nodesDepth(int[] count, int currDepth){
		return depthNodes(root, count, currDepth);
	}

	public int[] depthNodes(Node nodes, int[] count, int currDepth){
		count[currDepth]++;//oeker counteren med 1
		if (nodes.leftchild != null) {
			depthNodes(nodes.leftchild, count, currDepth +1);
		}

		if (nodes.rightChild != null) {
			depthNodes(nodes.rightChild, count, currDepth +1);
		}
	return count;
	}

	public void innOrderTraverseTree(Node focusNode){
		if (focusNode!=null) {
			innOrderTraverseTree(focusNode.leftchild);
			System.out.println(focusNode);

			innOrderTraverseTree(focusNode.rightChild);
		}
	}

	public void preOrderTraverseTree(Node focusNode){
		if (focusNode!=null) {
			System.out.println(focusNode);

			preOrderTraverseTree(focusNode.leftchild);
			
			preOrderTraverseTree(focusNode.rightChild);
		}
	}

	public void postOrderTraverseTree(Node focusNode){
		if (focusNode!=null) {

			postOrderTraverseTree(focusNode.leftchild);
			
			postOrderTraverseTree(focusNode.rightChild);
			System.out.println(focusNode);
		}
	}*/


	public String alfabetFirst(Node root){
		if (root == null) {
			System.out.println("No words found in the list");
		}
		String minste = "-1";
		if (root.leftchild == null) {
			return root.name;
		}else{
			minste = alfabetFirst(root.leftchild);//traverserer ti venstre for aa finne det minste alfabetiske ordet.
		}

		return minste;
	}

	public String alfabetLast(Node root){
		if (root == null) {
			System.out.println("No words found in the list");
		}
		String stoerste = "-1";
		if (root.rightChild == null) {
			return root.name;
		}else{
			stoerste = alfabetLast(root.rightChild);//traverserer til hoyre for aa finne det stoerste alfabetise ordet.
		}

		return stoerste;
	}

	public String swapChar(int a, int b, char[] words){//metode brukt for å finne lignende ord
		char curr = words[a];
		words[a] = words[b];
		words[b] = curr;

		return new String(words);
	}

	public String[] firstSimilar(String word){// 1. rule
		char[] words = word.toCharArray();
		String[] arrayWords = new String[words.length-1];
		char[] curr;
		for (int i = 0;i<words.length-1;i++ ) {
			curr = words.clone();
			arrayWords[i] = swapChar(i, i+1, curr);
		}
		return arrayWords;
	}

	public String[] secondSimilar(String word){//2. rule
		String[] subs = new String[word.length()*alphabet.length];
		char[] words = word.toCharArray();

		for (int i = 0;i<words.length;i++ ) {
			for (int x = 0;x<alphabet.length;x++ ) {
				char[] curr = words.clone();
				curr[i] = alphabet[x];
				subs[alphabet.length*i+x]="";

				for (int y = 0;y<curr.length;y++) {
					subs[alphabet.length*i+x] += curr[y];
				}
			}
		}
		for (int i = 0;i<subs.length;i++ ) {
			if (word.equalsIgnoreCase(subs[i])) {
				subs[i] = "";
			}
		}
		return subs;
	}

	public String[] thirdSimilar(String word){// 3. rule
		String[] subs = new String[(word.length()+1)*alphabet.length];
		char[] words = word.toCharArray();

		for (int i = 0;i<=word.length();i++ ) {
			String start = word.substring(0, i);
			String end = word.substring(i, word.length());

			for (int x = 0;x<alphabet.length;x++ ) {
				subs[word.length()*i+x] = start + alphabet[x] + end;
				subs[word.length()*i+x] = subs[word.length()*i+x].trim();

			}
		}
		for (int i = 0;i<subs.length;i++ ) {
			if (subs[i] == null) {
				subs[i] = "";
			}
		}
		return subs;
	}

	public String[] fourSimilar(String word){
		char[] words = word.toCharArray();
		String[] rm = new String[word.length()];
		char[] cp;

		for (int i = 0;i<words.length;i++ ) {
			cp = words.clone();
			cp[i] = ' ';
			String tmp = "";

			for (int x = 0;x<words.length;x++) {
				if (cp[x] != ' ') {
					tmp +=cp[x];
				}
			}
			rm[i] = tmp.trim();
		}
		return rm;
	}

	public int similarTotal(String[] sim){
		int cnt = 0;

		if (sim.length>0) {
			for (int i= 0;i<sim.length;i++ ) {
				String words = searchNode(sim[i]);
				if (words != null) {
					cnt++;
				}
			}
		}
		return cnt;//antall ord som er samme/lignende
	}

	public void printSimilar(String[] sim){
		boolean found = false;
		if (sim.length>0) {
			for (int i = 0;i<sim.length ;i++ ) {
				String words = searchNode(sim[i]);
				if (words != null) {
					System.out.println(words+ "\n");
					found = true;
				}
			}
			if (found == true) {
				System.out.println("");
			}
		}
	}

	public void menuResults(String res, Scanner sc){
		if (res.equals("1")) {
			System.out.println("Skriv inn ordet du letter etter: ");
			String word = sc.nextLine().trim().toLowerCase();//hvis brukeren gir et ord med store bokstaver 
			System.out.println("");

			String re = searchNode(word);

			if (re == null) {
				System.out.println("Desverre, det ordet du lettet etter finnes ikke i ordboken");
			}else{
				System.out.println("Ordet ble funnet");
			}

			if (re == null) {
				long tim = System.currentTimeMillis();
				System.out.println("Lignende ord ble funnet: ");
				String[] first = firstSimilar(word);
				String[] second = secondSimilar(word);
				String[] third = thirdSimilar(word);
				String[] fourth = fourSimilar(word);

				int simCount = 0;
				simCount += similarTotal(first);
				simCount += similarTotal(second);
				simCount += similarTotal(third);
				simCount += similarTotal(fourth);

				long totTime = System.currentTimeMillis()-tim;

				printSimilar(first);
				printSimilar(second);
				printSimilar(third);
				printSimilar(fourth);
				//printer ut alle ordene ^
				
				System.out.println("Antall lignende ord: " + simCount);
				System.out.println("Det tok " + totTime + "ms for aa utfore sokingen");
				System.out.println("");
			}
		}
			else if (res.equals("2")) {
				System.out.println("Skriv inn ordet du onsker aa legge til ordboka: ");
				String word = sc.nextLine().trim().toLowerCase();//hvis brukeren gir et ord med store bokstaver 
				addNode(word);
			}
			else if (res.equals("q")) {
				System.out.println("----------Tre statistics----------");
				System.out.println("Tre dypde er: " + maxDepth());//tre dybde
				
				int[] nodesDepth = nodesDepth();
				System.out.println(" Noder i dybde: ");
				
				for (int i = 0;i<nodesDepth.length;i++ ) {//hvor mange noder per hver dybde
					System.out.println(nodesDepth[i] + " Noder i dybde: " + i);
				}

				System.out.println("Gjennomsnitt dybde: " + avgDepth());
				
				if (root != null) {
					System.out.println("Forste ord i ordboka: " + alfabetFirst(root));
					System.out.println("Siste ord i ordboka: " + alfabetLast(root));
				}else{}

				/*
				//Skriver alt til fil
				writeToFile("----------Tre statistics----------");
				writeToFile("Tre dypde er: " + maxTreeDepth());//tre dybde
				int[] nodesPerDepth1 = new int[maxTreeDepth()];

				for (int i = 0;i<nodesPerDepth1.length;i++ ) {//hvor mange noder per hver dybde
					writeToFile(nodesPerDepth1[i] + " Noder i dybde: " + i);
				}

				writeToFile("Gjennomsnitt dybde: " + totalTreeDepth()/nodeCount());
				
				if (root != null) {
					writeToFile("Forste ord i ordboka: " + alfabetFirst(root));
					writeToFile("Siste ord i ordboka: " + alfabetLast(root));
				}*/

			}else{
				System.out.println("Feil valg, prov igjen\n");
		}
	}

	public void readFile(String file){
		Scanner sc = null;

		try{
			sc = new Scanner(new File(file));
		}catch(IOException e){
			e.printStackTrace();
		}

		while(sc.hasNext()){
			addNode(sc.next().trim());
		}
	sc.close();
	}

	public void writeToFile(String text){
		try{
			writer.write(text);
		}catch(Exception e){
			System.out.println("Kunne ikke skrive til fil!!");
		}
	}

	public void menu(){
		System.out.println("Velg en operasjon du osnker aa utfore: ");
		System.out.println("Trykk 1 for aa lette etter et ord");
		System.out.println("Trykk 2 for aa sette inn ett nytt ord");
		System.out.println("Trykk q for aa avslutte og skrive til utskrift filen");
	}
}
