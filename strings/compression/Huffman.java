package algs4.strings.compression;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.introcs.*;

public class Huffman {
	private static final int R = 256;
	
	private static class Node implements Comparable<Node>{
		private int freq;
		private char ch;
		private final Node left, right;
		public Node(char ch, int freq, Node left, Node right){
			this.freq = freq;
			this.ch = ch;
			this.left = left;
			this.right = right;
		}
		public boolean isLeaf(){
			return this.left == null && this.right == null;
		}
		public int compareTo(Node that){
			return this.freq - that.freq;
		}
		
	}
	
	private static Node buildTrie(int[] freq){
		MinPQ<Node> pq = new MinPQ<Node>();
		for(char i = 0; i < R; i ++)
			if(freq[i] > 0)
				pq.insert(new Node(i, freq[i],null,null));
		
		while(pq.size() > 1){
			Node left = pq.delMin();
			Node right = pq.delMin();
			pq.insert(new Node('\0',left.freq + right.freq,left, right));
		}
		return pq.delMin();
	}
	
	private static void buildCode(String[] st, Node x, String s){
		if(x.isLeaf()){
			st[x.ch] = s; return;
		}
		buildCode(st, x.left, s+'0');
		buildCode(st, x.right, s+'1');
	}
	
	private static void writeTrie(Node x){
		if(x.isLeaf()){
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch);
		}
		BinaryStdOut.write(false);
		writeTrie(x.left);
		writeTrie(x.right);
		
	}
	private static Node readTrie(){
		if(BinaryStdIn.readBoolean())
			return new Node(BinaryStdIn.readChar(), 0, null, null);
		return new Node('\0', 0, readTrie(),readTrie());
	}
	
	
	public static void compress(){
		
		String s = BinaryStdIn.readString();
		char[] input = s.toCharArray();
		
		
		int[] freq = new int[R];
		for(int i = 0; i < input.length; i++)
			freq[input[i]]++;
		
		
		Node root = buildTrie(freq);
		
		
		String[] st = new String[R];
		buildCode(st, root, "");
		
		
		writeTrie(root);
		
		
		BinaryStdOut.write(input.length);
		
		
		for (int i = 0; i < input.length; i++){
			String code = st[input[i]];
			for(int j = 0; j < code.length(); j++){
				if(code.charAt(j) == '1')
					BinaryStdOut.write(true);
				else BinaryStdOut.write(false);
			}
		}
		BinaryStdOut.close();
	}
	public static void main(String[] args) {
		

	}

}
