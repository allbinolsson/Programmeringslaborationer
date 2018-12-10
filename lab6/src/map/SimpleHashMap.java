package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	
	private final double LOAD_FACTOR = 0.75;
	private Entry<K, V>[] entries;
	private int size;
	private int capacity;
	
	public SimpleHashMap () {
		entries = (Entry<K, V>[]) new Entry[16];
		capacity = 16;
	}
	
	public SimpleHashMap (int capacity) {
		entries = (Entry<K, V>[]) new Entry[capacity];
		this.capacity = capacity;
	}
	
	public String show () {
		String string = "";
		
		for (int i = 0; i < entries.length; i++) {
			if (entries[i + 1] != null) {
				string += show(entries[i]);	
				string += "\n";
			} else {
				string += show(entries[i]);	
			}
		}
		
		return string;
	}
	
	private String show (Entry<K, V> entry) {
		String string = "";
		
		if (entry.next != null) {
			show(entry.next);
		} else {
			string += entry.toString();
		}
		
		return string;
	}
	
	@Override
	public V get(Object key) {
		Entry<K, V> entry = find(index((K)key), (K)key);
		
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
		int index = index(key);
		Entry<K, V> entry = find(index, key);
		
		rehash();	// This won't do anything unless it's needed.
		
		if (entry == null) {			
			entries[index] = new Entry<>(key, value);
			size++;
			return null;
		} else {
			V previousValue = entry.getValue();
			entries[index] = new Entry<>(key, value);
			size++;
			return previousValue;
		}
	}
	
	private void rehash () {
		while (size() / capacity > LOAD_FACTOR) {
			capacity++;
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
	
	private int index (K key) {
		return key.hashCode() % entries.length;
	}
	
	private Entry<K, V> find (int index, K key) {
		Entry<K, V> entry = entries[index];
		
		while (true) {
			if (entry.getKey().equals(key)) {
				return entry;
			} else if (entry.next != null) {
				entry = entry.next;
				if (entry.getKey().equals(key)) {
					return entry;
				}
			} else {
				break;
			}
		}
		
		return null;
	}
	
	public static class Entry<K, V> implements Map.Entry<K, V>{
		private K key;
		private V value;
		private Entry<K, V> next;
		
		public Entry (K key, V value) {
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
			this.value = value;
			return this.value;
		}
		
		@Override
		public String toString () {
			return key.toString() + " = " + value.toString();
		}
		
	}
}
