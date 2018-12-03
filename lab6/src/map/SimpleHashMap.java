package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	
	private Entry<K, V>[] entries;
	
	public SimpleHashMap () {
		entries = (Entry<K, V>[]) new Entry[16];
	}
	
	public SimpleHashMap (int capacity) {
		entries = (Entry<K, V>[]) new Entry[capacity];
	}
	
	public String show () {
		String string = "";
		for (Entry<K, V> entry : entries) {
			string += entry.toString() + "\n";
		}
		
		return string;
	}
	
	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (size() == 0);
	}

	@Override
	public V put(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		int size = 0;
		
		for (Entry<K, V> entry : entries) {
			size++;
		}
		return size;
	}
	
	public static class Entry<K, V> implements Map.Entry<K, V>{
		K key;
		V value;
		
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
