package algs4.searching;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

public class SeparateChainingHashST<Key, Value> {
	private int N;	
	private int M; 
	private SequentialSearchST<Key, Value>[] st;
	
	public SeparateChainingHashST(){this(997);}
	public SeparateChainingHashST(int M){
		this.M = M;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for(int i = 0; i < M; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff)%M;
	}
	public Value get(Key key){
		return (Value) st[hash(key)].get(key);
	}
	
	public void put(Key key, Value val){
		st[hash(key)].put(key, val);
	}
	public Iterable<Key> keys(){
		Queue q = new Queue<Key>();
		for(int i = 0; i < M; i++)
			for(Key k:st[i].keys())
				q.enqueue(k);
		return q;
	}
	
	
	public static void main(String[] args) {
		

	}

}
