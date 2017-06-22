import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        while(true) {
        	RBT rbt = new RBT();
            String line = br.readLine();
            if (line==null) break;
            else{
            	int n = Integer.parseInt(line);
            	if(n>0){
            		Node a = new Node(n);
            		rbt.insert(a);
            	}
            	else if(n<0){
            		Node b = rbt.search(rbt.root, -n);
            		rbt.delete(b);
            	}
            	else{
            		break;
            	}
            }
        }
        br.close();

        BufferedReader k = new BufferedReader(new FileReader("search.txt"));
        while(true){
        	PrintWriter pw = new PrintWriter("output.txt");
        	String line = k.readLine();
        	int n = Integer.parseInt(line);
        	if(line == null) break;
        	else{
        		Node c = rbt.search(rbt.root, n);
        		if(c != rbt.nil || c != null){
        			pw.print(rbt.maximum(c.left).val + " ");
        			pw.print(c.val + " ");
        			pw.print(rbt.minimum(c.right).val);
        		}
        		else{
        			int a = n;
        			int b = n;
        			while(a-1 != 0){
        				Node d = rbt.search(rbt.root, a);
        				if(d != rbt.nil || d != null){
        					pw.print(d.val + " ");
        					break;
        				}
        				a--;
        				if(a == 0)
        					pw.print("NIL ");
        			}
        			pw.print("NIL ");
        			while(true){
        				Node e = rbt.search(rbt.root, b);
        				if(e != rbt.nil || e != null){
        					pw.print(e.val);
        					break;
        				}
        				b++;
        			}
        		}

        	}
        }
    }
}