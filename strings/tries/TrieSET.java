package algs4.strings.tries;

import edu.princeton.cs.introcs.*;
import edu.princeton.cs.algs4.Queue;

public class TrieSET<Value> {
	private Node root;
	private int N;
	private class Node{
		char c;
		Value val;
		Node less,son, higher;
	}
	
	public Value get(String key){
		return get(root, key, 0).val;
	}
	private Node get(Node x, String key, int deep){
		if(x == null) 
			return null;
		char c = key.charAt(deep);
		if		(c < x.c) return get(x.less, key, deep);
		else if (c > x.c) return get(x.higher, key, deep);
		else if (deep < key.length() - 1)
			return get(x.son, key, deep + 1);
		return x;			
	}
	
	
	public void put(String key, Value val){
		root = put(root, key,0, val);
	}
	private Node put(Node x, String key, int deep, Value val){
		char c = key.charAt(deep);
		if( x == null){
			x =new Node();
			x.c = c;
		}
		if(c < x.c )
			x.less = put(x.less, key, deep, val);
		else if ( c > x.c)
			x.higher = put(x.higher, key, deep, val);
		else if (key.length() > deep + 1)
			x.son = put(x.son, key, deep + 1, val);
		else
			x.val = val;
		return x;
	}
}
