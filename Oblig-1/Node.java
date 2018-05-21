class Node{

	String name;

	Node leftchild;
	Node rightChild;
	static int[] depth;//bruker den i BinaryTree klassen
	int depth1;
	Node(String name){
		this.name=name;
		this.leftchild = null;
		this.rightChild = null;
	}

	Node(){
		this.name=name;
		this.leftchild = null;
		this.rightChild = null;
	}


	public void addNode(String word){//setter inn word i treet
		Node newNode = new Node(word);

		if (this.name == null) {
			this.name = word;
			return;
		}

		if (word.compareToIgnoreCase(this.name) < 0) {
			if (leftchild == null) {
				leftchild = newNode;
			}else{
				leftchild.addNode(word);
			}
		}else{
			if (rightChild == null) {
				rightChild = newNode;
			}else{
				rightChild.addNode(word);
			}
		}
	}

	public String searchNode(String word){
		Node focusNode;//naa Node

		if (word.compareToIgnoreCase(this.name) == 0) {
			return word;
		}else if (word.compareToIgnoreCase(this.name) < 0) {
			focusNode = leftchild;
		}else{
			focusNode = rightChild;
		}
		if (focusNode != null) {
			return focusNode.searchNode(word);
		}else{
			return null;
		}
	}
}