
/**
 * Filename:   MyProfiler.java
 * Project:    p3b-201901     
 * Authors:    sanghyunlee/001
 *
 * Semester:   Spring 2019
 * Course:     CS400
 * 
 * Due Date:   March.28.10pm
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */
import java.util.TreeMap;

public class MyProfiler<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable;
	TreeMap<K, V> treemap;

	/**
	 * Initialize treemap and hashtable.
	 * 
	 * @param num_elements - number of elements
	 */
	public MyProfiler(int num_elements) {
		treemap = new TreeMap<>();
		hashtable = new HashTable<>(num_elements, .80);
	}

	/**
	 * Insert the key value pair into treemap and hashtable.
	 * 
	 * @param key   - key to insert
	 * @param value - value to insert with specified key
	 */
	public void insert(K key, V value) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			hashtable.insert(key, value);
			treemap.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieve the specified value from treemap and hashtable.
	 * 
	 * @param key - key to retrieve from the data structure
	 */
	public void retrieve(K key) {
		try {
			hashtable.get(key);
			treemap.get(key);
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Main Method to run the Profiler class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int numElements = Integer.parseInt(args[0]);
			MyProfiler<Integer, Integer> profile = new MyProfiler<>(numElements);

			for (int i = 0; i < numElements; i++)
				profile.insert(i, i);

			for (int i = 0; i < numElements; i++)
				profile.retrieve(i);

			String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Usage: java MyProfiler <number_of_elements>");
			System.exit(1);
		}
	}
}
