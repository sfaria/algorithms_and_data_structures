package com.sfaria.ds;

/**
 *
 * @author Scott Faria <scott.faria@protonmail.com>
 */
@SuppressWarnings("unchecked")
public final class ArrayList<T> {

    // -------------------- Private Variables --------------------

    private Object[] arr;
    private int len = 0;

    // -------------------- Constructors --------------------

    public ArrayList() {
        this(10);
    }

    public ArrayList(int initialSize) {
        this.arr = new Object[initialSize];
        this.len = 0;
    }

    // -------------------- Public Methods --------------------

    public final int size() {
        return len;
    }

    public final T get(int index) {
        if (index < 0 || index >= len) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return (T) arr[index];
    }

    public final void add(T item) {
        if (len == arr.length) {
            resize();
        }
        arr[len++] = item;
    }

    public final int indexOf(T item) {
        return indexOfImpl(item);
    }

    public final T remove(int index) {
        if (index < 0 || index >= len) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Object old = arr[index];
        //noinspection ManualArrayCopy
        for (int idx = index; idx < len; idx++) {
            arr[idx] = arr[idx + 1];
        }
        len--;
        return (T) old;
    }

    public final void add(T item, int index) {
        if (index < 0 || index > len) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (len + 1 == arr.length) {
            resize();
        }

        len++;
        //noinspection ManualArrayCopy
        for (int idx = len - 1; idx > index; idx--) {
            arr[idx] = arr[idx - 1];
        }
        arr[index] = item;
    }

    public final T set(T item, int index) {
        if (index < 0 || index >= len) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Object old = arr[index];
        arr[index] = item;
        return (T) old;
    }

    public final boolean contains(T item) {
        return indexOf(item) != -1;
    }

    // -------------------- Private Methods --------------------

    private int indexOfImpl(T item) {
        for (int i = 0; i < arr.length; i++) {
            T obj = (T) arr[i];
            if (equals(obj, item)) {
                return i;
            }
        }
        return -1;
    }

    private boolean equals(T o1, T o2) {
        return o1 != null && o1.equals(o2);
    }

    private void resize() {
        Object[] arr = new Object[this.arr.length * 2];
        //noinspection ManualArrayCopy
        for (int i = 0; i < this.arr.length; i++) {
            arr[i] = this.arr[i];
        }
        this.arr = arr;
    }
}

