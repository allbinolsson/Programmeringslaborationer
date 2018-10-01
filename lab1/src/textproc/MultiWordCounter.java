package textproc;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {
	
	private Map<String, Integer> map = new TreeMap<String, Integer>();
	
	public MultiWordCounter (String[] words) {
		for(int i = 0; i < words.length; i++) {
			map.put(words[i], 0);
		}
	}
	
	public void process (String w) {
		if (map.containsKey(w)) {
			map.put(w, map.get(w) + 1);
		}
	}
	
	public void report () {
		for (String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key));
		}
	}
}
