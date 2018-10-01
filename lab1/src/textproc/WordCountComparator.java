package textproc;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>>{

	public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
		
		int compared = arg1.getValue() - arg0.getValue();
		
		if (compared == 0) {
			return arg0.getKey().compareTo(arg1.getKey());
		}
		
		return compared;
	}
	
}