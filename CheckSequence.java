import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class CheckSequence {
	
	public boolean checkSequence(String cards) {
		boolean valChk = false;
		int noPairs = 0;
		List<Character> defaultCards = new ArrayList<Character>(Arrays.asList('S', 'D', 'C', 'D'));
		List<Character> finalCheck = new ArrayList<Character>();
		List<String> cardsList = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(cards, ","); 
		while (tokenizer.hasMoreTokens()) { 
			noPairs++;
			cardsList.add(tokenizer.nextToken());
		}
		for (int i=1; i<cardsList.size(); i++) {
			if(cardsList.get(i).charAt(0)==cardsList.get(i-1).charAt(0) && defaultCards.contains(cardsList.get(i-1).charAt(0))) {
				valChk = true;
			} else {
				return false;
			}
			if(cardsList.get(i).charAt(1)=='#' && cardsList.get(i-1).charAt(1)=='#') {
				valChk = true;
			} else {
				return false;
			}
			if(i==1) {
				finalCheck.add(cardsList.get(i-1).charAt(2));
				finalCheck.add(cardsList.get(i).charAt(2));
//				valChk = true;
			} else {
				finalCheck.add(cardsList.get(i).charAt(2));
//				return false;
			}
		}
		
		if(checkSeq(finalCheck)) {
			return true;
		} 
		return false;
		
	}

	private boolean checkSeq(List<Character> finalCheck) {
		// TODO Auto-generated method stub
		List<Integer> seqInt = new ArrayList<Integer>();
		boolean aceChk = false;
		boolean checkSeq = false;
		for(int i=0; i<finalCheck.size(); i++) {
			if(finalCheck.get(i).equals('J')) {
				seqInt.add(11);
			} else if (finalCheck.get(i).equals('Q')) {
				seqInt.add(12);
			} else if (finalCheck.get(i).equals('K')) {
				seqInt.add(13);
			} else if (finalCheck.get(i).equals('A')) {
				aceChk = true;
				seqInt.add(1);
			} else if (finalCheck.get(i).equals('1')) {
//				aceChk = true;
				seqInt.add(10);
			} else {
				seqInt.add(Integer.valueOf(finalCheck.get(i)-48));
			}
		}
		checkSeq = orderedWithNoGap(seqInt);
		if(aceChk && !checkSeq) {
			seqInt.remove(0);
			seqInt.add(0, 14);
			checkSeq = orderedWithNoGap(seqInt);
		} 
		return checkSeq;
	}
	
	private static boolean orderedWithNoGap(List<Integer> list) {       
        Collections.sort(list);
        Integer prev = null;
        boolean seq = true;
        int c = 0;
        for(Integer i : list) {
            if(prev != null && prev+1 == i) {
            	seq = true;
        	} else {
        		if(c!=0) {
        			return false;
        		}
        	}
            c++;
            prev = i;
        }
        return seq;
    }
}
