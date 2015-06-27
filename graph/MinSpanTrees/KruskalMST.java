package algs4.graph.MinSpanTrees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class KruskalMST {
	private Queue<Edge> mst;

	public KruskalMST(EdgeWeightedGraph G) {
		mst = new Queue<Edge>();
		MinPQ<Edge> pq = new MinPQ<Edge>(G.V() + 1);
		for (Edge e : G.edges())
			pq.insert(e);
		UF uf = new UF(G.V());

		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (uf.connected(v, w))
				continue;
			mst.enqueue(e);
			uf.union(v, w);
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

		KruskalMST mst = new KruskalMST(G);
		for (Edge e : mst.edges()) {
			StdOut.println(e);
		}
		StdOut.println(mst.weight());
	}
}
