package org.example;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(Object[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        Object[] tempArray = new Object[array.length];
        mergeSort(array, tempArray, 0, array.length - 1);
    }

    private static void mergeSort(Object[] array, Object[] tempArray, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, tempArray, left, mid);
            mergeSort(array, tempArray, mid + 1, right);
            merge(array, tempArray, left, mid, right);
        }
    }

    private static void merge(Object[] array, Object[] tempArray, int left, int mid, int right) {
        System.arraycopy(array, left, tempArray, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            Comparable leftElement = (Comparable) tempArray[i];
            Comparable rightElement = (Comparable) tempArray[j];

            if (leftElement == null) {
                array[k++] = rightElement;
                j++;
            } else if (rightElement == null) {
                array[k++] = leftElement;
                i++;
            } else if (leftElement.compareTo(rightElement) <= 0) {
                array[k++] = leftElement;
                i++;
            } else {
                array[k++] = rightElement;
                j++;
            }
        }

        while (i <= mid) {
            array[k++] = tempArray[i++];
        }
    }
}
