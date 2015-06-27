package algs4.graph;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class KosarajuSCC {
	private int N; 
	private boolean[] marked;
	private int[] components;

	public KosarajuSCC(Digraph G) {
		components = new int[G.V()];
		marked = new boolean[G.V()];
		DepthFirstOrder rdf = new DepthFirstOrder(G.reverse());
		
		for (int v : rdf.reversePost())
			if (!marked[v]) {
				dfs(G, v);
				N++;
			}
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		components[v] = N;
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);

	}

	public boolean stronglyConnected(int v, int w) {
		return components[v] == components[w];
	}

	public int id(int v) {
		return components[v];
	}

	public int count() {
		return N;
	}

	public boolean marked(int w) {
		return marked[w];
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public static void main(String[] args) {
		Digraph G = new Digraph(new In(args[0]));
		KosarajuSCC scc = new KosarajuSCC(G);
		for (int v = 0; v < G.V(); v++)
			StdOut.println(v + ": " + scc.id(v));
	}
}
