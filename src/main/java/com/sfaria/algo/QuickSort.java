package com.sfaria.algo;

/**
 * @author Scott Faria
 */
public final class QuickSort {

    // -------------------- Public Static Methods --------------------

    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Null array!");
        }
        if (array.length == 0 || array.length == 1) {
            // already sorted or just empty
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    // -------------------- Private Static Methods --------------------

    private static <T extends Comparable<T>> void quickSort(T[] array, int left, int right) {
        int partition = partition(array, left, right);
        if (left < partition - 1) {
            quickSort(array, left, partition - 1);
        }
        if (partition < right) {
            quickSort(array, partition, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        T pivot = array[(left + right) / 2];
        while (left <= right) {
            while (array[left].compareTo(pivot) < 0) {
                left++;
            }
            while (array[right].compareTo(pivot) > 0) {
                right--;
            }
            if (left <= right) {
                T val = array[left];
                array[left] = array[right];
                array[right] = val;
                left++;
                right--;
            }
        }
        return left;
    }
}
