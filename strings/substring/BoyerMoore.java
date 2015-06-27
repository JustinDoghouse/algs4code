package algs4.strings.substring;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class BoyerMoore {
	
	
	private static final int R = 256;
	private String pat;
	private int[] right;
	public BoyerMoore(String pat){
		this.pat = pat;
		int M = pat.length();
		right = new int[R];
		for( int i = 0; i < R ; i++){
			right[i] = -1;
		}
		for(int j = 0; j < M; j++)
			right[pat.charAt(j)] = j;
	}
	public int search(String txt){
		int N = txt.length();
		int M = pat.length();
		int skip;
		for(int i = 0; i < N + M; i+= skip){
			skip = 0;
			for( int j = M - 1; j >= 0; j--){
				if( txt.charAt(i + j) != pat.charAt(j)){
					skip = j - right[txt.charAt(i+j)];
					if(skip < 1) skip = 1;
					break;
				}
			}
			if(skip == 0) return i;
		}
		return N;
	}
	
	public static void main(String[] args) {
		
		String pat = StdIn.readString();
		String txt = StdIn.readString();
		BoyerMoore kmp = new BoyerMoore(pat);
		int offset = kmp.search(txt);
		StdOut.print(offset);
	}

}
