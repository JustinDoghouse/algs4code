package algs4.strings.sort;
import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.introcs.*;
import edu.princeton.cs.introcs.StdOut;
public class KeyIndexedCounting {
	public static void main(String[] args) {
		
		Alphabet alpha = new Alphabet(args[0]);
		int R = alpha.R();
		int[] count = new int[R];
		
		String s = StdIn.readLine();
		int N = s.length();
		for( int i = 0; i < N ; i ++)
			if( alpha.contains(s.charAt(i)))
				count[alpha.toIndex(s.charAt(i))]++;
		for(int i = 0; i < R; i++){
			StdOut.println(alpha.toChar(i) + ' ' + count[i]);
		}
	}
}
