
public class Node {

	public int val;
	public Node parent;
	public Node left, right;
	public boolean color;
	public boolean originalcolor; 
	public Node(int newval){
		this.val = newval;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
	
}
