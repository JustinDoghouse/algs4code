package algs4.strings.sort;


import edu.princeton.cs.introcs.*;


public class MSD {
	private static final int M = 15;
	private static int R = 256;
	private static String[] aux;
	
	
	private static int charAt(String s, int d){
		if(s.length() <= d)
			return -1;
		return s.charAt(d);
	}
	
	public static void sort(String[] a){
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N - 1, 0);
	}
	private static void sort(String[] a, int lo, int hi, int d){
		if(lo + M >= hi){
			insertSort(a,lo,hi,d);
			return;
		}
		int[] count = new int[R+2];
		for(int i = lo; i < hi; i++)
			count[charAt(a[i],d)+2]++;
		for(int i = 0; i < R+1; i++)
			count[i + 1] += count[i];
		for(int i = lo; i < hi; i++)
			aux[count[charAt(a[i],d) + 1]++] = a[i];
		for(int i = lo; i < hi; i++)
			a[i] = aux[i - lo];
		for( int i = 0; i < R; i++)
			sort(a,lo+count[i], lo+count[i+1] -1, d+1);
	}
	private static void insertSort(String[] a, int lo, int hi, int d){
		for( int i = lo; i <= hi; i ++)
			for( int j = i; j > lo && lessto(a[j],a[j-1],d);j--)
				exch(a,j,j-1);
	}
	private static boolean lessto(String a, String b, int d){
		return a.substring(d).compareTo(b.substring(d)) < 0;
	}
	private static void exch(String[] a, int lt, int i){
		String tmp = a[lt];
		a[lt] = a[i];
		a[i] = tmp;
	}
	
	public static void main(String[] args){
		In in = new In( args[0]);
		String[] s = in.readAllStrings();
		sort(s);
		for( String a:s)
			StdOut.println(a);
	}
}
