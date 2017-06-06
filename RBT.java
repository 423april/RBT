
public class RBT {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	public Node nil, root;
	public RBT(){
		root = nil;
	}
	public void print(Node tree, int level){
		if(tree.right != null)
			print(tree.right, level+1);
		for(int i = 0; i < level; i++)
			System.out.print("    ");
		System.out.print(tree.val);
		if(tree.left != null)
			print(tree.left, level+1);
		}

	public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if(y.left != nil)
        	y.left.parent = x;
        y.parent = x.parent;
        if(x.parent == nil)
        	this.root = y;
        else if(x == x.parent.left)
        	x.parent.left = y;
        else
        	x.parent.right = y;
        y.left = x;
        x.parent = y;

    }
	public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if(y.right != nil)
        	y.right.parent = x;
        y.parent = x.parent;
        if(x.parent == nil)
        	this.root = y;
        else if(x == x.parent.right)
        	x.parent.right = y;
        else
        	x.parent.left = y;
        y.right = x;
        x.parent = y;

    }
	public void insert(Node z){

		Node y = nil;
		Node x = root;
		while(x != nil){
			y = x;
			if(z.val < x.val)
				x = x.left;
			else
				x = x.right;
		}
		z.parent = y;
		if(y == nil)
			this.root = z;
		else if(z.val < y.val)
			y.left = z;
		else
			y.right = z;
		z.left = nil;
		z.right = nil;
		z.color = RED;
		insertfixup(z);
	}
	
	public void insertfixup(Node z){
		if(z.parent != null){
		while(z.parent.color == RED){
			
			if(z.parent == z.parent.parent.left){
				Node y = z.parent.parent.right;
				if(y.color == RED){
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				}
				else if(z == z.parent.right){
					z = z.parent;
					leftRotate(z);
				}
				z.parent.color = BLACK;
				z.parent.parent.color = RED;
				rightRotate(z.parent.parent);
			}//end if
			else{
				Node y = z.parent.parent.left;
				if(y.color == RED){
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				}
				else if(z == z.parent.left){
					z = z.parent;
					rightRotate(z);
				}
					if(z.parent != null){
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					leftRotate(z.parent.parent);
			}
			}//end else
			
		}//end while
	}
		this.root.color = BLACK;

	}//end method
	public void transplant(Node u, Node v){
		if(u.parent == null)
			this.root = v;
		else if(u == u.parent.left)
			u.parent.left = v;
		else
			u.parent.right = v;
		if(v != null)
			v.parent = u.parent;
		
		
	}
	public Node minimum(Node x){
		while(x.left != nil)
			x = x.left;
		return x;
	}
	public Node maximum(Node x){
		while(x.right != nil)
			x = x.right;
		return x;
	}
	public void delete(Node z){
		Node x = null;
		Node y = z;
		y.originalcolor = y.color;
		if(z.left == nil){
			x = z.right;
			transplant(z,z.right);
		}
		else if(z.right == nil){
			x = z.left;
			transplant(z, z.left);
		}
		else{
			y = minimum(z.right);
			y.originalcolor = y.color;
			x = y.right;
			if(y.parent == z && x != null)
				x.parent = y;
			else if(y != null && y.right != null){
				transplant(y,y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			transplant(z,y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if(y.originalcolor == BLACK)
			deletefixup(x);
	}
	
	public void deletefixup(Node x){
		while(x != this.root && x.color == BLACK){
			if(x == x.parent.left){
				Node w = x.parent.right;
				if(w.color == RED){
					w.color = BLACK;
					x.parent.color = RED;
					w = x.parent.right;
				}
				if(w.left.color == BLACK && w.right.color == BLACK){
					w.color = RED;
					x = x.parent;
				}
				else if(w.right.color == BLACK){
					w.left.color = BLACK;
					w.color = RED;
					rightRotate(w);
					w = x.parent.right;
				}
				w.color = x.parent.color;
				x.parent.color = BLACK;
				w.right.color = BLACK;
				leftRotate(x.parent);
				x = this.root;
			}
			else{
				Node w = x.parent.left;
				if(w.color == RED){
					w.color = BLACK;
					x.parent.color = RED;
					w = x.parent.left;
				}
				if(w.right.color == BLACK && w.left.color == BLACK){
					w.color = RED;
					x = x.parent;
				}
				else if(w.left.color == BLACK){
					w.right.color = BLACK;
					w.color = RED;
					leftRotate(w);
					w = x.parent.left;
				}
				w.color = x.parent.color;
				x.parent.color = BLACK;
				w.left.color = BLACK;
				rightRotate(x.parent);
				x = this.root;
			}
		}
		if(x != null)
			x.color = BLACK;
	}
	public int count(Node tree){
		int c = 1;
		if(tree == null)
			return 0;
		else{
			c += count(tree.left);
			c += count(tree.right);
			return c;
		}
	}
	public int countBlack(Node tree){
		int nbBlack = 1;
		if(tree == null || tree.color == RED)
			return 0;
		nbBlack += countBlack(tree.left);
		nbBlack += countBlack(tree.left);
		if(tree.color == BLACK)
			nbBlack++;
		return nbBlack;
	}
	public int blackheight(Node tree){
		int bh = 1;
		if(tree == null)
			return 0;
		bh += blackheight(tree.right);
		if(tree.color == BLACK)
			bh++;
		return bh;
		
	}
	public void inorder(Node tree){
		if(tree == null)
			return;
		else{
			inorder(tree.left);
			System.out.print(" "+ tree.val);
			inorder(tree.right);
		}
	}
	public Node search(Node tree, int val){
			if(tree == null)
				return null;
			else if(val == tree.val)
				return tree;
			else if(val < tree.val)
				return search(tree.left, val);
			else
				return search(tree.right, val);
		}
}

