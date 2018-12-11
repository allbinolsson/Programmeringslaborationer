package map;

public class SimpleHashMap<K, V> implements Map<K, V> {

	private final double LOAD_FACTOR = 0.75;
	private Entry<K, V>[] entries;
	private int size;
	private int capacity;

	public SimpleHashMap() {
		entries = (Entry<K, V>[]) new Entry[16];
		capacity = 16;
		size = 0;
	}

	public SimpleHashMap(int capacity) {
		entries = (Entry<K, V>[]) new Entry[capacity];
		this.capacity = capacity;
		size = 0;
	}

	public String show() {
		String string = "";
		Entry<K, V> entry;

		for (int i = 0; i < entries.length; i++) {
			entry = entries[i];

			if (entry != null) {
				while (true) {
					string += entry.toString();

					if (entry.next != null) {
						entry = entry.next;
					} else {
						break;
					}
				}
			}
		}

		return string;
	}

	@Override
	public V get(Object key) {
		Entry<K, V> entry = find(index((K) key), (K) key);

		if (entry == null) {
			return null;
		} else {
			return entry.getValue();
		}
	}

	@Override
	public boolean isEmpty() {
		return (size() == 0);
	}

	@Override
	public V put(K key, V value) {
		int index= index(key);
		Entry<K, V> put = new Entry<>(key, value);
        Entry<K, V> found = find(index, key);
        
        rehash();
        
        if (found != null) {
        	return found.setValue(value);
        } else {
        	Entry<K, V> head = entries[index];
        	if (head == null) {
        		entries[index] = put;
        		size++;
        		return null;
        	} else {
        		while (head.next != null) {
        			head = head.next;
        		}
        		size++;
        		head.next = put;
        		return null;
        	}
        }
	}

	private void rehash() {
		if (size / capacity > LOAD_FACTOR) {
			capacity *= 2;
			Entry<K, V>[] old = entries;
			entries = (Entry<K, V>[]) new Entry[capacity];
		}
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	private int index(K key) {
		return Math.abs(key.hashCode() % entries.length);
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> head = entries[index];	// First element of list at index
		
		while (head != null) {	// If head is not null
			if (head.key.equals(key)) {	// If keys are equal
				return head;
			}
			
			head = head.next;	// Move forward in list
		}
		
		return null;	// If no Entry was found
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V prevVal = this.value;
			this.value = value;
			return prevVal;
		}

		@Override
		public String toString() {
			return key.toString() + " = " + value.toString();
		}

	}
}
