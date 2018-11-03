package com.sfaria.algo;

import java.lang.reflect.Array;

/**
 * @author Scott Faria
 */
public final class MergeSort {

    // -------------------- Public Methods --------------------

    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Null array!");
        }
        if (array.length == 0 || array.length == 1) {
            // already sorted
            return;
        }
        mergeSort(array, 0, array.length - 1);
    }

    // -------------------- Private Methods --------------------

    private static <T extends Comparable<T>> void mergeSort(T[] array, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergeSort(array, low, middle);
            mergeSort(array, middle + 1, high);
            merge(array, low, middle, high);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, int low, int middle, int high) {
        T[] temp = createGenericArray(array, high - low + 1);

        int i = low;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= high) {
            T t1 = array[i];
            T t2 = array[j];
            if ((t1.compareTo(t2) < 0)) {
                temp[k] = t1;
                i++;
            } else {
                temp[k] = t2;
                j++;
            }
            k++;
        }

        while (i <= middle) {
            temp[k++] = array[i++];
        }

        while (j <= high) {
            temp[k++] = array[j++];
        }

        for (i = low; i <= high; i++) {
            array[i] = temp[i - low];
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] createGenericArray(T[] array, int length) {
        return (T[]) Array.newInstance(array.getClass().getComponentType(), length);
    }
}
