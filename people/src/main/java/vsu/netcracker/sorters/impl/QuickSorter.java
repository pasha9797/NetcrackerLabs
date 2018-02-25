package vsu.netcracker.sorters.impl;

import vsu.netcracker.sorters.Sorter;

import java.util.Comparator;

public class QuickSorter<T> implements Sorter<T> {
    public void sort(T[] array, Comparator<T> comparator){
        quickSort(array, 0, array.length-1, comparator);
    }

    private void quickSort(T[] array, int start, int end, Comparator<T> comparator) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (comparator.compare(array[i], array[j]) <= 0)) {
                i++;
            }
            while (j > cur && (comparator.compare(array[cur], array[j]) <= 0)) {
                j--;
            }
            if (i < j) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        quickSort(array, start, cur, comparator);
        quickSort(array, cur + 1, end, comparator);
    }

    @Override
    public String toString() {
        return "QuickSorter";
    }
}
