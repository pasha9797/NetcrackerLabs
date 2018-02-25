package vsu.netcracker.sorters.impl;

import vsu.netcracker.sorters.Sorter;

import java.util.Comparator;

public class BubbleSorter<T> implements Sorter<T> {
    public void sort(T[] array, Comparator<T> comparator) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "BubbleSorter";
    }
}
