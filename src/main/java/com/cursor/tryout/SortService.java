package com.cursor.tryout;

import org.springframework.stereotype.Service;

/**
 * Service class for sorting operations.
 * Provides bubble sort implementation for integer arrays.
 */
@Service
public class SortService {

    /**
     * Sorts an array of integers using bubble sort algorithm.
     * @param arr the array to sort
     * @return the sorted array
     */
    public int[] bubbleSort(final int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        final int[] sortedArray = arr.clone();
        final int n = sortedArray.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    // Swap elements
                    final int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no swapping occurred, array is sorted
            if (!swapped) {
                break;
            }
        }

        return sortedArray;
    }

    /**
     * Sorts an array in place using bubble sort algorithm.
     * @param arr the array to sort (will be modified)
     */
    public void bubbleSortInPlace(final int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        final int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    final int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no swapping occurred, array is sorted
            if (!swapped) {
                break;
            }
        }
    }
}
