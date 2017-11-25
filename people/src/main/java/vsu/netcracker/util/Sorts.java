package vsu.netcracker.util;

import vsu.netcracker.model.Person;

import java.util.Comparator;

public class Sorts<T> {
    public enum SortTypes {QUICK, BUBBLE, INSERTION}

    public void quickSort(T[] array, int start, int end, Comparator<T> comparator) {
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

    public void bubbleSort(T[] array, Comparator<T> comparator) {
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

    public void insertionSort(T[] array, Comparator<T> comparator) {
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
