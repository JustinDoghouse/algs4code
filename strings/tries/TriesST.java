package algs4.strings.tries;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.*;

public class TriesST<Value> {
	private static int R = 256;
	private Node root;
	private int N;
	public TriesST(){
		
	}
	
	
	private static class Node{
		private Object val;
		Node[] next = new Node[R]; 
	}
	
	public Value get(String key){
		Node x = get(key, root,0);
		if(x == null) return null;
		return (Value) x.val;
	}
	private Node get(String key, Node x,int d){
		if( x== null)
			return null;
		if(d == key.length())
			return x;
		return get(key,x.next[key.charAt(d)],d+1);
	}
	public void put(String key, Value val){
		root = put(root,key,val,0);
	}
	private Node put(Node x, String key, Value val, int d){
		if(x == null) x = new Node();
		if(key.length() == d){
			x.val = val; return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}
	public int size(){
		return size(root);
	}
	private int size(Node x){
		if(x == null) return 0;
		int sz = 0;
		if( x.val != null) sz++;
		for(int i = 0; i < R; i++){
			sz += size(x.next[i]);
		}
		return sz;
	}
	

	private void collect(Node x,String pre, Queue<String> q){
		if(x == null) return;
		if(x.val!= null) q.enqueue(pre);
		for(char i = 0; i < R ; i++)
			collect(x.next[i], pre+i, q);
	}
	public Iterable<String> keysWithPrefix(String pre){
		Queue<String> q = new Queue<String>();
		collect(get(pre,root,0), pre, q);
		return q;
	}
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}

	
	public Iterable<String> keysThatMatch(String pat)
	{
	Queue<String> q = new Queue<String>();
	collect(root, "", pat, q);
	return q;
	}
	public void collect(Node x, String pre, String pat, Queue<String> q)
	{
		int d = pre.length();
		if(x == null) return;
		if(pre.length() == pat.length()){
			if(x.val != null) q.enqueue(pre);
			return;
		}
		char next = pat.charAt(d);
		for(char c = 0; c < R; c++)
			if(next == '.' || next == c)
				collect(x.next[c],pre+c,pat,q);
	}
	
	
	public String longestPrefixOf(String s){
		int length = search(root, s, 0,0);
		return s.substring(0,length);
	}
	private int search(Node x, String s, int d, int len){
		if(x == null)
			return len;
		if(x.val != null)
			len = d;
		if(d == s.length())
			return len;
		char c = s.charAt(d);
		return search(x.next[c], s, d + 1, len);
		
	}
	
	public void delete(String key){
		root = delete(root,key,0);
	}
	private Node delete(Node x, String key, int d){
		if(x == null) return null;
		if(key.length() == d)
			x.val = null;
		else{
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d+1);
		}
		if(x.val != null)
			return x;
		for(char c = 0; c< R; c++)
			if( x.next[c] != null )
				return x;
		return null;
		
	}
	
	
	public static void main(String args[]){
		In in = new In(args[0]);
		TriesST<Integer> st = new TriesST<Integer>();
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }
		
		if(st.size() < 100){
			StdOut.println("keys(\"\"):");
			for( String key : st.keys()){
				StdOut.println(key + " " + st.get(key));
			}
			StdOut.println();
		}
        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("keysWithPrefix(\"shor\"):");
        for (String s : st.keysWithPrefix("shor"))
            StdOut.println(s);
        StdOut.println();

        StdOut.println("keysThatMatch(\".he.l.\"):");
        for (String s : st.keysThatMatch(".he.l."))
            StdOut.println(s);
		
	}
	
}
