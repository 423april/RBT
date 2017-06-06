import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.lang.Integer;

public class Test {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		RBT rbt = new RBT();
		while (true) {
			String line = br.readLine();
			if (line == null || line.trim().equals(""))
				break;
			else {
				int n = Integer.parseInt(line);
				if (n > 0) {
					Node a = new Node(n);
					rbt.insert(a);
				} else if (n < 0) {
					if(rbt.search(rbt.root,-n)==rbt.nil)
						continue;
					rbt.delete(rbt.search(rbt.root, -n));
				} else {
					System.out.println("total = " + rbt.count(rbt.root));
					System.out.println("nb = " + rbt.countBlack(rbt.root));
					System.out.println("bh = " + rbt.blackheight(rbt.root));
					rbt.inorder(rbt.root);
				}
			}

		} // end of while
		br.close();
	}// end of main

}// end of class
