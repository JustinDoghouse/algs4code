package algs4.strings.substring;

import java.math.BigInteger;
import java.util.Random;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class RabinKarp {
	private String pat;
	private long patHash;
	private int M;
	private static final int R = 256;
	private long RM;
	private long Q;
	
	public RabinKarp(String pat){
		this.pat = pat;
		this.M = pat.length();
		Q = longRandomPrime();
		RM = 1;
		for(int i = 1; i < M ; i++)
			RM = (R * RM) % Q;
		patHash = hash(pat, M);
	}
	
	public int search(String txt){
		long h = hash(txt, M);
		int N = txt.length();
		if( h == patHash && check(0, txt)) return 0;
		for(int i = 0; i < N-M; i++){
			
			h = (h + Q - RM * txt.charAt(i)%Q ) % Q;
			h = (h * R + txt.charAt(i + M) )% Q;
			if( h == patHash && check(i+1, txt)) return i+1;
		}
		return N;
	}
	
	private boolean check(int i, String s){
		return true;
	}
	private long hash(String key, int len){
		long a = 0;
		for( int i = 0; i < len; i ++){
			a = (a*R + key.charAt(i)) % Q;
		}
		return a;
	}
	private long longRandomPrime(){
		BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
	}
	public static void main(String[] args) {
		
		String pat = StdIn.readString();
		String txt = StdIn.readString();
		RabinKarp kmp = new RabinKarp(pat);
		int offset = kmp.search(txt);
		StdOut.print(offset);
	}

}
