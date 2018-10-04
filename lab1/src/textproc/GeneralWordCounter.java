package textproc;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

public class GeneralWordCounter implements TextProcessor{
	
	private Set<String> exception = new HashSet<String>();
	private Map<String, Integer> wordMap = new TreeMap<String, Integer>();	
	
	public GeneralWordCounter (Set<String> wordSet) {
		for (String k : wordSet) {
			exception.add(k);
		}
	}
	
	public void process(String w) {
		if (wordMap.containsKey(w) && !exception.contains(w)) {
			wordMap.put(w, wordMap.get(w) + 1);
		} else if (!wordMap.containsKey(w) && !exception.contains(w)) {
			wordMap.put(w, 1);
		}
	}
	
	public Set<Map.Entry<String, Integer>> getWords () {	// Tillagd i laboration 3.
		return wordMap.entrySet();
	}
	
	public void report() {		
		Set<Map.Entry<String, Integer>> wordSet = wordMap.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for (int i = 0; i < 5; i++) {
			System.out.println(wordList.get(i));
		}
		
	} 
}
