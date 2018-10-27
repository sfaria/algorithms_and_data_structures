package com.sfaria.ds;

import java.util.Objects;

/**
 * @author Scott Faria (scott.faria@gmail.com)
 */
public final class Hashtable<K, V> {

    // -------------------- Private Variables --------------------

    private final float loadFactor;

    private int itemCount;
    private Entry<?, ?>[] entries;

    // -------------------- Constructors --------------------

    public Hashtable(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException(String.format("Capacity must be >= 0: %d", initialCapacity));
        }

        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException(String.format("Load factor is too low or invalid: %d", loadFactor));
        }
        this.loadFactor = loadFactor;
        this.itemCount = 0;
        this.entries = new Entry<?, ?>[initialCapacity];
    }

    // -------------------- Public Methods --------------------

    public final int size() {
        return itemCount;
    }

    @SuppressWarnings("unchecked")
    public final V put(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        int index = (key.hashCode() & 0x7FFFFFFF) % entries.length;
        Entry<K, V> entry = (Entry<K, V>) entries[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                //noinspection unchecked
                V oldValue = (V) entry.value;
                entry.value = value;
                return oldValue;
            }
            entry = entry.next;
        }

        itemCount++;
        rehash();
        index = (key.hashCode() & 0x7FFFFFFF) % entries.length;
        if (entries[index] != null) {
            Entry<K, V> newEntry = new Entry<>(key, value, ((Entry<K, V>) entries[index]));
            entries[index] = newEntry;
        } else {
            entries[index] = new Entry<>(key, value);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public final V get(K key) {
        Objects.requireNonNull(key);
        int index = (key.hashCode() & 0x7FFFFFFF) % entries.length;
        Entry<?, ?> entry = entries[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return (V) entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public final V remove(K key) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(key);
        int index = (key.hashCode() & 0x7FFFFFFF) % entries.length;
        Entry<K, V> entry = (Entry<K, V>) entries[index];
        Entry<K, V> previous = null;
        while (entry != null) {
            if (entry.key.equals(key)) {
                V value = entry.value;
                if (previous == null) {
                    entries[index] = entry.next;
                } else {
                    previous.next = entry.next;
                }
                itemCount--;
                return value;
            }
            previous = entry;
            entry = entry.next;
        }
        return null;
    }

    // -------------------- Private Methods --------------------

    @SuppressWarnings("unchecked")
    private void rehash() {
        if ((itemCount * loadFactor) > entries.length) {
            Entry<?, ?>[] newEntries = new Entry<?, ?>[entries.length * 2];
            for (Entry<?, ?> entry : entries) {
                if (entry != null) {
                    int newIndex = (entry.key.hashCode() & 0x7FFFFFFF) % newEntries.length;
                    Entry<K, V> extantEntry = (Entry<K, V>) newEntries[newIndex];
                    if (extantEntry != null) {
                        while (extantEntry.next != null) {
                            extantEntry = extantEntry.next;
                        }
                        extantEntry.next = (Entry<K, V>) entry;
                    } else {
                        newEntries[newIndex] = entry;
                    }
                }
            }
            entries = newEntries;
        }
    }

    // -------------------- Inner Classes --------------------

    private static final class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        Entry(K key, V value) {
            this(key, value, null);
        }

        private Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
