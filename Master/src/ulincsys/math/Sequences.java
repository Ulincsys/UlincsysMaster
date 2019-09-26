package ulincsys.math;

import java.util.ArrayList;

public class Sequences {
	private static ArrayList<Long> sequenceGen = new ArrayList<Long>();
	
	public static ArrayList<Long> conSeqGen(long term1, long term2, int numberOfTerms) {
		if(numberOfTerms == 0) {
			return sequenceGen;
		} else {
			sequenceGen.add(term1);
			return conSeqGen(term2, term1 + term2, --numberOfTerms);
		}
	}
	
	public static ArrayList<Long> fib(int numberOfTerms) {
		return conSeqGen(1, 1, numberOfTerms);
	}
}
