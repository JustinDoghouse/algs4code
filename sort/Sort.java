package algs4.sort;

import stdlib.StdRandom;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Sort {
	
	
	public static void selectSort(Comparable[] a){
		int N = a.length;
		for(int i = 0; i < N; i++){
			int min = i;
			for(int j = i + 1; j < N; j++ )
				if (less(a[j],a[min])) min = j; 
			exch(a, min, i);
		}
	}
	 
	public static void insertSort(Comparable[] a){
		insertSort(a,0,a.length - 1);
	}
	public static void insertSort(Comparable[] a,int lo, int hi){
		for(int i = lo; i < hi+1; i++){
			int j = i;
			Comparable t = a[i];
			for(; j > lo && less(t, a[j-1]); j--){
			
				a[j] = a[j - 1];
			}
			a[j] = t;
			
		}
	}
	
	public static void shellSort(Comparable[] a){
		int N = a.length;
		int h = 1;
		while(h < N/3) h = 3*h + 1;
		while( h >= 1){
			for(int i = h; i < N; i++){
				for(int j = i; j >0 && less(a[j],a[j-h]); j-= h)
					exch(a, j , j-h);
			}
			h = h/3;
		}
	}

	
	private static Comparable[] aux;
	private static int small = 10;
	public static void mergeSort(Comparable[] a){
		aux = new Comparable[a.length];
		mergeSort(a,0,a.length - 1);
	}
	private static void mergeSort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		if(hi <= lo + small){
			insertSort(a,lo,hi);
			return;
		}
		int mid = ( hi + lo) / 2;
		mergeSort(a,lo,mid);
		mergeSort(a,mid + 1,hi);
		if(less(a[mid+1],a[mid]))
				merge(a,lo,mid,hi);
	}
	private static void merge(Comparable[]a, int lo, int mid, int hi){
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
		for(int k = lo; k <= hi; k++){
			if		(i > mid) 				a[k] = aux[j++];
			else if (j > hi)				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))  a[k] = aux[j++];
			else							a[k] = aux[i++];
		}
					
	}
	
	public static void quickSort(Comparable[] a){
		StdRandom.shuffle(a);
		quickSort(a, 0, a.length - 1);
	}
	public static void quickSort(Comparable[] a, int lo, int hi){
		if(hi <= lo + small) {insertSort(a,lo,hi);return;}
		
		int j = triPartition(a,lo,hi);
		quickSort(a, lo, j - 1);
		quickSort(a, j+1, hi);
	}
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo, j = hi + 1;
		Comparable v = a[lo];
		while(true){
			while(less(a[++i], v)) if(i >= hi) break;
			while(less(v, a[--j]))  ;
			if( i >= j) break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}
	private static int triPartition(Comparable[] a, int lo, int hi){
		exch(a, lo, mid(a, lo, hi));
		return partition(a, lo, hi);
	}
	private static int mid(Comparable[] a, int lo, int hi){
		int mid = (lo + hi)/2;
		if		(less(a[lo], a[mid]))
			if		(less(a[mid] , a[hi]))
				return mid;
			else if (less(a[hi],a[lo]) )
				return lo;
			else return hi;
		else	
			if		(less(a[lo], a[hi]))
				return lo;
			else if (less(a[mid], a[hi]))
				return mid;
			else return hi;
	}
	
	
	
	public static void quick3waySort(Comparable[] a){
		StdRandom.shuffle(a);
		quick3way(a,0, a.length - 1);
	}
	private static void quick3way(Comparable[] a, int lo, int hi){
		if( hi <= lo) return;
		int lt = lo,i = lo + 1, gt = hi;
		Comparable v = a[lo];
		while( i <= gt){
			int cmp = a[i].compareTo(v);
			if		(cmp < 0) exch(a, i++, lt++);
			else if	(cmp > 0) exch(a, i, gt--);
			else			i++;
		}
		quick3way(a, lo, lt - 1);
		quick3way(a, gt +1, hi);
	}


	
	private static boolean less(Comparable a, Comparable b){
		return a.compareTo(b) < 0;
	}
	 
	private static void exch(Comparable[] a, int i , int j){
		Comparable t = a[i]; a[i] = a[j]; a[j] = t;
	}
	public static boolean isSorted(Comparable[] a){
		for (int i = 1; i < a.length; i++){
			if (less(a[i] , a[i+1])) return false;
		}
		return true;
	}
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
    }
	public static void main(String[] args) {
        Integer[] a = {12341231,1,5,43,5,2,3,5,7,9,1,0,6,4};
        
        MaxPQ.sort(a);
       
        Sort.show(a);
        
        
       
	}

}
