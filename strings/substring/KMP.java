package algs4.strings.substring;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class KMP {
	String pat;
	private static final int R = 256;
	private int[][] dfa;
	public KMP(String p){
		this.pat = p;
		int M = p.length();
		dfa = new int[R][M];
		int x = 0;
		dfa[pat.charAt(0)][0] = 1;
		for(int i = 1; i < M; i++){
			for(int j = 0; j < R; j++){
				dfa[j][i] = dfa[j][x];
			}
			dfa[pat.charAt(i)][i] = i + 1;
			x = dfa[pat.charAt(i)][x];
		}
	}
	public int search(String txt){
		int N = txt.length(), M = pat.length();
		int i,j = 0 ;
		for(i = 0; i < N && j < M;i++){
			j = dfa[txt.charAt(i)][j];
		}
		if( j == M)
			return i - M;
		return N;
	}
	public static void main(String[] args) {
		
		String pat = StdIn.readString();
		String txt = StdIn.readString();
		KMP kmp = new KMP(pat);
		int offset = kmp.search(txt);
		StdOut.print(offset);
	}

}
