package algs4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.introcs.In;

public class Graph {
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	public Graph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int i = 0; i < V; i++)
			adj[i] = new Bag<Integer>();
	}
	public Graph(In in){
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	public int V(){ return V;}
	public static void main(String[] args) {
		In in = new In(args[0]);

	}

}
