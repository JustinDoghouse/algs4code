package algs4.strings.sort;

import edu.princeton.cs.introcs.*;

public class LSD {
	public static void sort(String[] a, int W){
		
		int N = a.length;
		int R = 256;
		String[] aux = new String[N];
		
		for (int d = W-1; d >= 0; d--){
			int[] count = new int[R+1];
			for(int i = 0; i < N; i++){
				count[a[i].charAt(d) + 1]++;
			}
			for(int i = 1; i < R+1; i++){
				count[i] += count[i-1]; 
			}
			for(int i = 0; i < N; i++){
				aux[count[a[i].charAt(d)]++] = a[i];
			}
			for(int i = 0; i < N; i++)
				a[i] = aux[i];
			
		}
				
		
	}
	public static void main(String[] args) {
		
		In in = new In(args[0]);

		String[] a = in.readAllStrings();
		int N = a.length;
        int W = a[0].length();
        for (int i = 0; i < N; i++)
            assert a[i].length() == W : "Strings must have fixed length";

        
        sort(a, W);

        
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
	}

}
