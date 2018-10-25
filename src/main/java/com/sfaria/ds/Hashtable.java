package com.sfaria.ds;

/**
 * @author Scott Faria (scott.faria@gmail.com)
 */
public final class Hashtable<K, V> {

    // -------------------- Private Variables --------------------

    private final float loadFactor;
    private Entry<?, ?>[] buckets;

    // -------------------- Constructors --------------------

    public Hashtable(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException(String.format("Capacity must be >= 0: %d", initialCapacity));
        }

        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException(String.format("Load factor is too low or invalid: %d", loadFactor));
        }
        this.loadFactor = loadFactor;
        this.buckets = new Entry<?, ?>[initialCapacity];
    }

    // -------------------- Public Methods --------------------

    public final V put(K key, V value) {
        return null;
    }

    public final V get(K key) {
        return null;
    }

    public final V remove(K key) {
        return null;
    }

    public final boolean containsKey(K key) {
        return false;
    }

    // -------------------- Private Methods --------------------

    // -------------------- Inner Classes --------------------

    private static final class Entry<K, V> {
        private final K key;
        private final V value;
        private final Entry<K, V> next;

        public Entry(K key, V value) {
            this(key, value, null);
        }

        private Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
