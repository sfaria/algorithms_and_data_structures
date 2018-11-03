package com.sfaria.algo;

/**
 * @author Scott Faria
 */
public final class BinarySearch {

    // -------------------- Public Static Methods --------------------

    public static <T extends Comparable<T>> boolean search(T[] array, T item) {
        if (array == null) {
            throw new IllegalArgumentException("Null array!");
        }
        if (item == null) {
            return false;
        }
        return binarySearch(array, item, 0, array.length - 1);
    }

    // -------------------- Private Static Methods --------------------

    private static <T extends Comparable<T>> boolean binarySearch(T[] array, T item, int low, int high) {
        if (low > high) {
            return false;
        }

        int mid = low + (high - low) / 2;
        T middleItem = array[mid];
        if (item.compareTo(middleItem) == 0) {
            return true;
        } else if (item.compareTo(middleItem) < 0) {
            return binarySearch(array, item, low, mid - 1);
        } else {
            return binarySearch(array, item, mid + 1, high);
        }
    }

}
