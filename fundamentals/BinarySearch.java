package algs4.fundamentals;
import java.util.*;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class BinarySearch {
	public static int rank(int key, int[] a){
		return rank(key, a, 0, a.length - 1);
	}
	public static int rank(int key, int[] a, int lo, int hi){
		if(lo <= hi){
			int mid = (lo + hi)/2;
			if		( a[mid] > key) return rank(key, a, lo, mid - 1); 
			else if ( a[mid] < key) return rank(key, a, mid +1, hi);
			return  mid;
		}
		return -1;
	}
	public static void main(String[] args){
		int[] whitelist = {1,4,3,8,9};
		Arrays.sort(whitelist);
		while(!StdIn.isEmpty()){
			int key = StdIn.readInt();
			if (rank(key, whitelist) == -1) StdOut.println(key);
		}
	}
}
