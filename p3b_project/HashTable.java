

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: HashTable.java  
//Files: CS 400
//Course: LEC 001 Spring, 2019
//
//Author: Sanghyun lee	
//Email: lee866@wisc.edu
//Lecturer's Name: Deb Deppeler
//
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully
//acknowledge and credit those sources of help here. Instructors and TAs do
//not need to be credited here, but tutors, friends, relatives, room mates,
//strangers, and others do. If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons: NONE.
//Online Sources: NONE.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * implement HashTableADT does not have any public or package level fields,
 * methods, or inner classes
 * 
 * @author sanghyun lee
 **/

public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

	/**
	 * Bucket Inner calss define an inner class,for storing key and value and
	 * 
	 **/
	private class Bucket<K, V> {
		K key; // initialize key (hash)
		V value; // initialize value
		Bucket<K, V> next; // the next element inserted in the bucket

		Bucket(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	}

	int capacity; // the number of elements the array can hold

	double loadFactor;
	Bucket[] table; // the array based hashtable.
	int numKeys;

	private V value;

	private K key;

	/**
	 * no args Hashtable constructor
	 **/
	public HashTable() {

	}

	/**
	 * Hashtable constructor indicated initial capacity and load factor threshold
	 * 
	 * @param initialCapacity,loadFactorThreshold
	 **/

	public HashTable(int initialCapacity, double loadFactorThreshold) {

		capacity = initialCapacity;
		this.loadFactor = loadFactorThreshold;
		table = new Bucket[capacity];

	}

	@Override
	public int numKeys() {
		// TODO Auto-generated method stub
		return this.numKeys;
	}

	@Override
	public double getLoadFactorThreshold() {
		// TODO Auto-generated method stub
		return this.loadFactor;
	}

	/**
	 * Returns the current load factor for this hash table load factor = number of
	 * items / current table size
	 * 
	 **/
	@Override
	public double getLoadFactor() {
		// TODO Auto-generated method stub
		return (double) this.numKeys / (double) this.capacity;
	}

	/**
	 * Return the current Capacity (table size) of the hash table array. Once
	 * increased, the capacity never decreases
	 * 
	 **/
	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return this.capacity;

	}

	/**
	 * Returns the collision resolution scheme used for this hash table. i used 5
	 * CHAINED BUCKET: array of linked nodes
	 **/
	@Override
	public int getCollisionResolution() {
		// TODO Auto-generated method stub
		return 5;
	}

	/**
	 * insert key,value and increse the number of keys If key is null, throw
	 * IllegalNullKeyException; If key is already in data structure, throw
	 * DuplicateKeyException();
	 * 
	 * @param key, value
	 **/
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		int hashIndex = Math.abs(key.hashCode() % capacity); // index calculated
		Bucket currIndex = null;

		// checked table and if table is empty insert index
		if (table[hashIndex] == null) {
			table[hashIndex] = new Bucket(key, value);
			numKeys++;
		} else {
			currIndex = table[hashIndex];
			if (currIndex.key.equals(key)) { // if the same key is found throw DuplicateKeyException
				throw new DuplicateKeyException();
			}
			if (!currIndex.key.equals(key) && currIndex.next != null) {
				currIndex = currIndex.next;
			} else {
				currIndex.next = new Bucket(key, value);
				numKeys++;
			}
		}
		if (getLoadFactor() >= loadFactor) {
			resize();
		}
	}

	/**
	 * when the table is full, making new size table and rehashing.
	 * 
	 **/
	private void resize() throws IllegalNullKeyException, DuplicateKeyException {

		capacity = (capacity * 2) + 1;
		Bucket[] prevTable = table;
		table = new Bucket[capacity];
		Bucket currIndex = null;
		this.numKeys = 0; 

		for (int i = 0; prevTable.length > i; i++) {
			currIndex = prevTable[i];

			if (currIndex != null) {

				insert((K) currIndex.key, (V) currIndex.value);
				currIndex = currIndex.next;
			}
		}

	}

	/**
	 * If key is found, remove the key and value from the data structure and decrese
	 * numKeys If key is null, throw IllegalNullKeyException, If key is not found,
	 * return false.
	 *
	 **/
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		int hashIndex = Math.abs(key.hashCode() % capacity);
		Bucket prevIndex = null;
		Bucket currIndex = table[hashIndex];

		while (currIndex != null) {
			if (prevIndex == null) {
				currIndex = currIndex.next;
				numKeys--;
				return true;
			}

			if (currIndex.key.equals(key) && prevIndex != null) {
				prevIndex.next = currIndex.next;
				numKeys--;
				return true;
			}

			prevIndex = currIndex;
			currIndex = currIndex.next;
		}
		return false;
	}

	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		int hashIndex = Math.abs(key.hashCode() % capacity);
		Bucket currIndex = table[hashIndex];

		if (!currIndex.key.equals(key)) {
			throw new KeyNotFoundException();
		}
		if (currIndex != null && currIndex.key.equals(key)) {
			return (V) currIndex.value;
		}
		currIndex = currIndex.next;
		return value;

	}

}
