package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };
	
	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
		Scanner exceptionScan = new Scanner(new File("undantagsord.txt"));
		
		Set<String> exceptions = new HashSet<String>();
		
		while (exceptionScan.hasNext()) {
			exceptions.add(exceptionScan.next());
		}
		
		List<TextProcessor> pList = new ArrayList<TextProcessor>();
		pList.add(new SingleWordCounter("nils"));
		pList.add(new SingleWordCounter("norge"));
		pList.add(new MultiWordCounter(REGIONS));
		pList.add(new GeneralWordCounter(exceptions));

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for (TextProcessor p : pList) {
				p.process(word);
			}
		}

		s.close();

		for (TextProcessor p : pList) {
			p.report();
		}
		
		long t1 = System.nanoTime();
		System.out.println("Tid: " + (t1-t0) / 1000000.0 + " ms");
	}
}