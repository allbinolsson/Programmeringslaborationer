package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> node = new QueueNode<E>(e);
		
		if (size == 0) {
			node.next = node;	// Om listan är tom sätts noden in och får sig 
			last = node;		// som nästa nod
		} else {
			node.next = last.next; 	// Annars får noden den före detta sista nodens nästa
			last.next = node;		// och dess next uppdateras till den insatta noden
			last = node;			// Slutligen sätts node som sista elementet i listan.
		}
		
		if (last.equals(node)) {
			size++;		// Ökar listans storlek
			System.out.println("The specified element is added to the rear of this queue");
			return true;
		} else {
			return false;
		}
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {	
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		
		if (size == 0) {
			return null;
		} else {
			return last.next.element;
		}
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		
		if (size == 0) {
			return null;
		} else {
			E head = last.next.element;
			QueueNode<E> newHead = last.next.next; 	// Tar fram andra noden
			
			last.next = newHead;
			
			size--;
			System.out.println("The head of the queue is removed if it was not empty");
			
			return head;
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call.
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		
		if (q.equals(this)) {
			throw new IllegalArgumentException();
		}
		
		if (q.size == 0) {
			return;
		} else if (size == 0) {
			size += q.size;	// Flyttar över alla objekt
			last = q.last;	// Referensen till sista objeket ger samtliga andra objekt
			
			q.last = null;	// Tar bort elementen från q (den blir tom) 
			q.size = 0;
		} else {
			QueueNode<E> tempHead = last.next;
			
			size += q.size;	// Slår samman storlekarna
			last.next = q.last.next;	// Referens till första objektet i kön 
			last = q.last;	// Sätter last til sista objektet i q
			last.next = tempHead; 	// Sätter sista elementets next till första elementet
			
			q.last = null;
			q.size = 0;
		}
	}
	
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private int counter;	// Värde som räknar genom kön.
		
		private QueueIterator () {
			
			if (size == 0) {
				pos = null;
			} else {
				pos = last; 	// Sista elementet i listan
			}
			
			counter = 0; 		// Startvärde, 1 för första elementet i kön
		}
		
		public boolean hasNext() {
			return !(size == 0 || counter >= size); 	// False om kön är tom eller om pos är sist i kön
		}

		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				counter++;		// Ökar räknaren för att hänvisa till nästa element
				pos = pos.next; 	// Ändrar referensen till pos till nästa element
				return pos.element;
			}
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
