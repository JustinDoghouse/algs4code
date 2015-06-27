package algs4.graph.MinSpanTrees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class PrimMST {
	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] marked;
	private IndexMinPQ<Double> pq;

	public PrimMST(EdgeWeightedGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		for (int i = 1; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		marked = new boolean[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		pq.insert(0, 0.00);
		while (!pq.isEmpty())
			visit(G, pq.delMin());
	}

	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			int w = e.other(v);
			if (marked[w])
				continue;
			if (e.weight() < distTo[w]) {
				distTo[w] = e.weight();
				edgeTo[w] = e;
				if (pq.contains(w))
					pq.changeKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}

	public Iterable<Edge> edges() {
		Queue<Edge> q = new Queue<Edge>();
		for (Edge e : edgeTo)
			q.enqueue(e);
		return q;
	}

	public double weight() {
		double w = 0;
		for (double d : distTo)
			w += d;
		return w;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedGraph G;
		G = new EdgeWeightedGraph(in);

		PrimMST mst = new PrimMST(G);
		for (Edge e : mst.edges()) {
			StdOut.println(e);
		}
		StdOut.println(mst.weight());
	}
}
