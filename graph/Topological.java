package algs4.graph;

import edu.princeton.cs.algs4.SymbolDigraph;
import edu.princeton.cs.introcs.StdOut;

public class Topological {
	private Iterable<Integer> order;

	public Topological(edu.princeton.cs.algs4.Digraph digraph) {
		edu.princeton.cs.algs4.DirectedCycle cyclefineder = new edu.princeton.cs.algs4.DirectedCycle(
				digraph);
		if (!cyclefineder.hasCycle()) {
			edu.princeton.cs.algs4.DepthFirstOrder dfs = new edu.princeton.cs.algs4.DepthFirstOrder(
					digraph);
			order = dfs.reversePost();
		}
	}

	public Iterable<Integer> order() {
		return order;
	}

	public boolean isDAG() {
		return order != null;
	}

	public static void main(String[] args) {
		String filename = args[0];
		String separator = args[1];
		SymbolDigraph sg = new SymbolDigraph(filename, separator);

		Topological top = new Topological(sg.G());
		for (int v : top.order())
			StdOut.println(sg.name(v));
	}

}
