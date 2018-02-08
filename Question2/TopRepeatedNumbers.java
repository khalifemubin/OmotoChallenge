import java.util.Scanner; 
import java.lang.Exception;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

class TopRepeatedNumbers{
	public static void main(String args[]) {
		System.out.println("\n\n\nThis program prints the top 5 most repeated words from List");
		boolean validListSize = false;
		int listSize = 0;
		Scanner scanner = new Scanner(System.in);
		
		do{
			System.out.println("\n\n\nEnter size of list");	
			listSize = scanner.nextInt();
			
			validListSize = (listSize<=0)? false : true;
			
		}while (!validListSize);
		
		String wordList[] = new String[listSize];
		
		int i = 0;
		System.out.println("\n\n\nStart entering "+listSize+" words");
		
		while(i<listSize)
		{
			System.out.println("\n\n\nEnter word number "+(i+1)+":");
			wordList[i] = scanner.next();
			i++;
		}
		
		Map<String, Integer> wordCount = new HashMap<String, Integer>();

		for(String word: wordList) {
		  Integer count = wordCount.get(word);          
		  wordCount.put(word, (count==null) ? 1 : count+1);
		}
		
		Set<Entry<String, Integer>> set = wordCount.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        
		Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
		
		System.out.println("\n\n\nAnd the top results are:");
		int row = 1;
        for(Map.Entry<String, Integer> entry:list){
			if(row<=5){
				System.out.println(entry.getKey()+" appeared "+entry.getValue() + " times.");
				row++;
			}else{
				break;
			}
        }
		
	}
	
	
}