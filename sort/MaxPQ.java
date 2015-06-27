package algs4.sort;

import edu.princeton.cs.introcs.StdOut;
 
public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N = 0;
	public MaxPQ(int maxN){
		pq = (Key[]) new Comparable[maxN + 1];
	}
	public boolean isEmpty(){return N == 0;}
	public int size(){return N;}
	public void insert(Key v){
		pq[++N] = v;
		swim(N);
	}
	

	public Key delMax(){
		Key max = pq[1];
		exch(pq, 1,N--);
		sink(1);
		return max;
	}
	 
	public static void sort(Comparable[] a){
		int N = a.length - 1;
		for(int k = N/2; k >= 1; k--)
			sink(a, k, N);
		while(N > 1){
			exch(a, 1, N--);
			sink(a,1,N);
		}
		
	}
	
	private void swim(int idx){
		while(idx > 1 && less(pq[idx/2], pq[idx])){
			exch(pq,idx/2, idx);
			idx = idx / 2;
		}
	}	

	private void sink(int idx){
		while(2 * idx <= N ){
			int j = idx * 2;
			if		(j < N && less(j,j+1))j++;
			if 		(!less(idx, j)) break;
			exch(j, idx);
			idx = j;
		}
	}
	 
	private static void sink(Comparable[] a,int idx, int N){
		while(2 * idx <= N ){
			int j = idx * 2;
			if		(j < N && less(a[j],a[j+1]))j++;
			if 		(!less(a[idx], a[j])) break;
			exch(a,j, idx);
			idx = j;
		}
	}
	private boolean less(int a, int b){
		return pq[a].compareTo(pq[b]) < 0;
	}
	private static boolean less(Comparable a, Comparable b){
		return a.compareTo(b) < 0;
	}
	 
	private static void exch(Comparable[] a, int i , int j){
		Comparable t = a[i]; a[i] = a[j]; a[j] = t;
	}
	private void exch(int i , int j){
		Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;
	}
	public static boolean isSorted(Comparable[] a){
		for (int i = 1; i < a.length; i++){
			if (less(a[i] , a[i+1])) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		
        Integer[] a = {5,43,5,2,3,5,7,9,1,0,6,4};
        MaxPQ<Integer> pq = new MaxPQ<Integer>(a.length);
        for(Integer i: a)
        	pq.insert(i);
        for(int i = 0; i < a.length; i ++)
        	StdOut.println(pq.delMax());
	}

}
