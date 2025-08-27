package com.cursor.tryout;

import org.springframework.stereotype.Service;

@Service
public class SortService {
    
    /**
     * Sorts an array of integers using bubble sort algorithm
     * @param arr the array to sort
     * @return the sorted array
     */
    public int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        
        int[] sortedArray = arr.clone();
        int n = sortedArray.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                    swapped = true;
                }
            }
            
            if (!swapped) {
                break;
            }
        }
        
        return sortedArray;
    }
    
    /**
     * Sorts an array of integers using bubble sort algorithm (in-place)
     * @param arr the array to sort in-place
     */
    public void bubbleSortInPlace(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        int n = arr.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            if (!swapped) {
                break;
            }
        }
    }
}
