package questions.leetcode.questions.google.chase;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class HashMapWithExpiration<K, V> {

	ConcurrentHashMap<K, Value<V>> map;
	PriorityBlockingQueue<Entry<K>> queue;
	boolean isPruning;
	
	public HashMapWithExpiration() {
		this.map = new ConcurrentHashMap<>();
		this.queue = new PriorityBlockingQueue<Entry<K>>();
	}
	
	public V get(K key) {
		Value<V> value = map.get(key);
		
		if (value == null) {
			return null;
		}
		
		// the entry has expired
		if (value.expiration < getCurrentTime()) {
			return null;
		}
		
		return value.val;
	}
	
	public void put(K key, V val, long ttl) {
		long expiration = getCurrentTime() + ttl;
		map.put(key, new Value<V>(val, expiration));
		queue.offer(new Entry<K>(key, expiration));
		
//		this.pruning();
	}
	
	// If the cache has very rare write operation and you don't want the cleanup() to block the read,
	// create a maintenance thread to that calls pruning at certain interval.
	// Otherwise, you can perform the clean up during the write operations, because that way we can
	// avoid competing write locks with user operation. 
	public void pruning() {
		isPruning = true;
		while (queue.peek().expiration <= getCurrentTime()) {
			map.remove(queue.poll().key);
		}
		isPruning = false;
	}
	
	private long getCurrentTime() {
		return System.currentTimeMillis();
	}
	
	private class Value<T> {
		T val;
		long expiration;
		
		public Value(T val, long expiration) {
			this.val = val;
			this.expiration = expiration;
		}
	}
	
	private class Entry<T> implements Comparable<Entry<T>> {
		T key;
		long expiration;
		
		public Entry(T key, long expiration) {
			this.key = key;
			this.expiration = expiration;
		}
		
		@Override
		public int compareTo(Entry<T> o) {
			return Long.compare(this.expiration, o.expiration);
		}
	}
}
