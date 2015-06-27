package algs4.graph;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstSearch {
	private boolean[] marked;
	private int[] edgeTo;

	private final int s;
	
	public DepthFirstSearch(Graph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G,s);
	}
	private void dfs(Graph G, int v){
		marked[v] = true;
		for(int w: G.adj(v))
			
			if(!marked[v]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
	}
	public boolean marked(int w){
		return marked[w];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	public static void main(String[] args) {

	}

}
