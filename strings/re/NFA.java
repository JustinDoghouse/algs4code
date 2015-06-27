package algs4.strings.re;
import java.util.Stack;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.introcs.*;

class NFA {
	private int M;
	private Digraph G;
	private char[] re;
	
	public NFA(String regexp){
		re =regexp.toCharArray();
		M = re.length;
		G = new Digraph(M + 1);
		Stack<Integer> stk = new Stack<Integer>();
		
		for( int i = 0; i < M; i++){
			int lp = i;
			if		(re[i] == '(' || re[i] == '|')
				stk.push(i);
			else if (re[i] == ')'){
				int or = stk.pop();
				if(or == '|'){
					lp = stk.pop();
					G.addEdge(lp, or + 1);
					G.addEdge(or, i);
				}
				else lp = or;
			}
			if 		(i < M - 1 && re[i + 1] == '*'){
				G.addEdge(lp,i+1);
				G.addEdge(i+1, lp);
			}
			if		(re[i] == '(' || re[i] ==')' || re[i] == '*')
				G.addEdge(i, i+1);
		}
	}
	
	public boolean recognies(String txt){
		Bag<Integer> bag = new Bag<Integer>();
		DirectedDFS dfs = new DirectedDFS(G, 0);
		for(int i = 0; i < G.V(); i++){
			if(dfs.marked(i) ) bag.add(i);
		}
		for(int i = 0; i < txt.length(); i++){
			Bag<Integer> match = new Bag<Integer>();
			for(int v: bag)
				if(v < M)
					if(re[v] == txt.charAt(i) || re[v] == '.')
						match.add(v + 1);
			
			bag = new Bag<Integer>();
			dfs = new DirectedDFS(G,match);
			for( int v = 0; v < G.V(); v ++){
				if(dfs.marked(v)) bag.add(v);
			}
			for(int v: bag) if(v == M) return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		String txt = StdIn.readString();
		String re = StdIn.readString();
		NFA nfa = new NFA(re);
		StdOut.println(nfa.recognies(txt));
	}

}
