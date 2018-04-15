
public class Node {
	
	int[] pos = new int[2];
	Node link = null;
	
	Node(int[] pos){
		this.pos = pos;
	}

	public int[] getPos() {
		return pos;
	}

	public void setPos(int[] pos) {
		this.pos = pos;
	}

	public Node getLink() {
		return link;
	}

	public void setLink(Node link) {
		this.link = link;
	}
	
	

}
