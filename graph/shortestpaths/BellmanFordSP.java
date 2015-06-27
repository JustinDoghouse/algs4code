package algs4.graph.shortestpaths;

import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;

public class BellmanFordSP {
	private Queue<Integer> q;
	private boolean[] onQ;
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private int cost;
	private Iterable<DirectedEdge> cycle;

	public BellmanFordSP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		onQ = new boolean[G.V()];
		q = new LinkedList<Integer>();
		edgeTo = new DirectedEdge[G.V()];

		for (int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		onQ[s] = true;
		q.add(s);
		while (!q.isEmpty()) {
			int v = q.remove();
			relax(G, v);
			onQ[v] = false;
		}

	}

	private void relax(EdgeWeightedDigraph G, int v) {
		for (DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (!onQ[w]) {
					q.add(w);
					onQ[w] = true;
				}
			}
			if (cost++ % G.V() == 0)
				findNegativeCycle();
		}
	}

	private void findNegativeCycle() {
		int V = edgeTo.length;
		EdgeWeightedDigraph spt;
		spt = new EdgeWeightedDigraph(V);
		for (int v = 0; v < V; v++) {
			if (edgeTo[v] != null)
				spt.addEdge(edgeTo[v]);
		}
		
		

	}

	 

	
	public static void main(String[] args) {
		In in = new In(args[0]);
		int V = in.readInt();
		String[] name = new String[V];
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
		for (int v = 0; v < V; v++) {
			name[v] = in.readString();
			for (int w = 0; w < V; w++) {
				double rate = StdIn.readDouble();
				DirectedEdge e = new DirectedEdge(v, w, -Math.log(rate));
				G.addEdge(e);
			}
		}
	}
}
