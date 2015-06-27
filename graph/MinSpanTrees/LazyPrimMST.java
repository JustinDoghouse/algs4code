package algs4.graph.MinSpanTrees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class LazyPrimMST {
	
	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;

	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>(G.E());
		mst = new Queue<Edge>();
		marked = new boolean[G.V()];
		visit(G, 0);

		while (!pq.isEmpty()) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (marked[v] && marked[w])
				continue;
			mst.enqueue(e);
			if (!marked[v])
				visit(G, v);
			else
				visit(G, w);
		}
	}

	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			if (!marked[e.other(v)])
				pq.insert(e);
		}
	}

	public Iterable<Edge> edges() {
		return mst;
	}

	public double weight() {
		
		double w = 0;
		for (Edge e : mst)
			w += e.weight();
		return w;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedGraph G;
		G = new EdgeWeightedGraph(in);

		LazyPrimMST mst = new LazyPrimMST(G);
		for (Edge e : mst.edges()) {
			StdOut.println(e);
		}
		StdOut.println(mst.weight());
	}
}
