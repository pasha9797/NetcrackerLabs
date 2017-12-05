package vsu.netcracker.sorters.impl;

import vsu.netcracker.sorters.Sorter;

import java.util.Comparator;

public class InsertionSorter<T> implements Sorter<T> {
    public void sort(T[] array, Comparator<T> comparator) {
        T temp;
        int j;
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0) {
                temp = array[i + 1];
                array[i + 1] = array[i];
                j = i;
                while (j > 0 && comparator.compare(temp, array[j - 1]) < 0) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = temp;
            }
        }
    }
}
