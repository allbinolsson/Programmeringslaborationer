package testqueue;

import static org.junit.Assert.*;

import java.util.Queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {

	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	/** Testar att slå samman två tomma köer. */
	@Test
	public final void testTwoEmpty() {
		q1.append(q2);
		assertTrue("Fel storlek efter sammanslagning", q1.isEmpty());
		assertTrue(q2.isEmpty());
		// Finns inga att objekt att kontrollera ordning på
	}

	/** Testar att slå samman en tom kö med en icke-tom */
	@Test
	public final void testAppendEmpty() {
		for (int i = 0; i < 5; i++) {
			q1.offer(i); 	// Ger q1 5 värden
		}
		
		int joinedSize = q1.size() + q2.size();	// Den sammanlagda storleken
		
		q1.append(q2);	// q2 är tom
		assertTrue("Listan har fel storlek", q1.size() == joinedSize);	// Kollar om storleken stämmer
		
		for (int i = 0; i < 5; i++) {
			int k = q1.poll();
			assertEquals("Värdena ligger i fel ordning", i, k);	// Kollar om rätt element ligger på rätt palts
		}
	
		assertTrue("Den andra listan är inte tom", q2.isEmpty());	// Detta kommer alltid stämma
	}

	/** */
	@Test
	public final void testAppendToEmpty() {
		for (int i = 0; i < 5; i++) {
			q2.offer(i); 	// Ger q2 5 värden
		}
		
		int joinedSize = q1.size() + q2.size();
		
		q1.append(q2);
		
		assertTrue("Storleken stämmer inte", joinedSize == q1.size());
		
		for (int i = 0; i < 5; i++) {
			int k = q1.poll();
			assertEquals("Elementen ligger i fel ordning", i, k);
		}
		
		assertTrue("Den andra listan är inte tom", q2.isEmpty());
	}

	/** */
	@Test
	public final void testNonEmpty() {
		for (int i = 0; i < 5; i++) {
			q1.offer(i);	// Ger q1 5 värden
		}
		
		for (int i = 5; i < 10; i++) {
			q2.offer(i);	// Ger q2 5 värden
		}
		
		q1.append(q2);
		assertTrue("Storleken stämmer inte", q1.size() == 10);
		
		for (int i = 0; i < 10; i++) {
			int k = q1.poll();
			assertEquals("Elementen hamnar i fel ordning", k, i);
		}
		
		assertTrue("Den andra listan är inte tom", q2.isEmpty());
	}

	/** Testar att konkatenera en kö med sig själv */
	@Test
	public final void testAppendSelf() {
		try {
			q1.append(q1);
			fail("Möjligt att konkatenera en kö med sig själv");
		} catch (IllegalArgumentException e) {

		}
	}

}
