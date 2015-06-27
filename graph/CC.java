package algs4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.*;

public class CC {
	private int N;	
	private boolean[] marked;
	private int[] components;
	public CC(Graph G){
		components = new int[G.V()];
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++){
			if(!marked[v]){
				dfs(G,v);
				N++;
			}
				
		}
	}
	private void dfs(Graph G, int v){
		marked[v] = true;
		components[v] = N;
		for(int w: G.adj(v))
			if(!marked[w]) {
				
				dfs(G, w);
			}
	}
	public boolean connected(int v, int w){
		return components[v] == components[w];
	}
	public int id(int v){
		return components[v];
	}
	public int count(){return N;}
	
	
	
	public boolean marked(int w){
		return marked[w];
	}
	
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	public static void main(String[] args) {
		
		Graph G = new Graph(new In(args[0]));
		CC cc =new CC(G);
		
		int M = cc.count();
		StdOut.println(M+ " Components");
		Bag<Integer>[] cmp;
		cmp = (Bag<Integer>[]) new Bag[M];
		for(int i= 0; i < M; i++)
			cmp[i] = new Bag<Integer>();
		for(int v = 0; v < G.V(); v++)
			cmp[cc.id(v)].add(v);
		for(int i = 0; i< M; i++){
			for(int v: cmp[i]){
				StdOut.print(v+" ");
				
			}
			StdOut.println();
		}
		
	}

}
