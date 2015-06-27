package algs4.searching;


import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class BST<Key extends Comparable<Key>, Val> {
	private Node root;
	private class Node{
		private Key key;
		private Val val;
		private Node left, right;
		private int N;
		public Node(Key key, Val val, int N){
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	public int size(){ return size(root);}
	
	private int size(Node x){ if (x == null) return 0; return x.N;}
	
	public Val get(Key key){
		return get(key, root);
	}
	public Val get(Key key, Node x){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if		(cmp < 0)  	return get(key, x.left);
		else if (cmp > 0)	return get(key,x.right);
		else				return x.val;
	}
	
	public void put(Key key, Val val){
		root = put(key, val, root);
	}
	private Node put(Key key, Val val, Node x){
		if(x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if		(cmp < 0) x.left = put(key, val, x.left);
		else if (cmp > 0) x.right = put(key, val, x.right);
		else x.val = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public Key min(){ return min(root).key; }
	private Node min(Node x){
		if(x.left == null) return x;
		return min(x.left);
	}
	public Key max(){return max(root).key;}
	private Node max(Node x){
		if( x.right == null) return x;
		return max(x.right);
	}
	
	public Key select(int k){return select(root, k).key;}
	private Node select(Node x, int k){
		if(x == null) return null;
		int cmp = size(x.left);
		if		(cmp > k) return select(x.left, k);
		else if (cmp < k) return select(x.right, k - cmp - 1);
		return x;
	}
	
	public int rank(Key key){
		return rank(root,key);
	}
	public int rank(Node x, Key key){
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if		(cmp < 0) return rank(x.left, key);
		else if (cmp > 0) return rank(x.right, key) + size(x.left) + 1;
		else return size(x.left);
	}

	
	public void deleteMin(){
		root = deleteMin(root);
	}
	private Node deleteMin(Node x){
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	public void delete(Key key){ root = delete(root, key);}
	public Node delete(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if		(cmp < 0) x.left = delete(x.left, key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else{
			if (x.right == null) return x.left;
			if (x.left == null) return x.right;
			Node t = x;
			x = min(x.right);
			x.right = deleteMin(x.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> q = new Queue<Key>();
		keys(root,q, lo, hi);
		return q;
	}
	private void keys(Node x, Queue<Key> q, Key lo, Key hi){
		if(x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0) keys(x.left, q, lo, hi);
		if(cmplo <= 0 && cmphi >= 0) q.enqueue(x.key);
		if(cmphi > 0) keys(x.right, q, lo, hi);
	}
	
    
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }
    
    public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; i < 10; i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

      
        

        

        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s) + "|");
        StdOut.println();
        StdOut.println(st.min());
        StdOut.println("size: " + st.size());
        for(int i = 0; i < 10; i ++)
        	StdOut.println("select:"+st.select(i));
   
        	StdOut.println("rank: "+ st.rank("8"));
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s) + "|");
    }
	
}
