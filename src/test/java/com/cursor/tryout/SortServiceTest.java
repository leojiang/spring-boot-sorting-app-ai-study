package com.cursor.tryout;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SortServiceTest {

    private final SortService sortService = new SortService();

    @Test
    void testBubbleSort() {
        int[] numbers = {64, 34, 25, 12, 22, 11, 90, 88, 76, 45};
        int[] expected = {11, 12, 22, 25, 34, 45, 64, 76, 88, 90};
        
        int[] result = sortService.bubbleSort(numbers);
        
        assertArrayEquals(expected, result);
        // Original array should remain unchanged
        assertArrayEquals(new int[]{64, 34, 25, 12, 22, 11, 90, 88, 76, 45}, numbers);
    }

    @Test
    void testBubbleSortInPlace() {
        int[] numbers = {5, 2, 8, 1, 9};
        int[] expected = {1, 2, 5, 8, 9};
        
        sortService.bubbleSortInPlace(numbers);
        
        assertArrayEquals(expected, numbers);
    }

    @Test
    void testBubbleSortWithEmptyArray() {
        int[] numbers = {};
        int[] result = sortService.bubbleSort(numbers);
        
        assertArrayEquals(numbers, result);
    }

    @Test
    void testBubbleSortWithSingleElement() {
        int[] numbers = {42};
        int[] result = sortService.bubbleSort(numbers);
        
        assertArrayEquals(numbers, result);
    }

    @Test
    void testBubbleSortWithNullArray() {
        int[] result = sortService.bubbleSort(null);
        
        assertNull(result);
    }

    @Test
    void testBubbleSortWithAlreadySortedArray() {
        int[] numbers = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        
        int[] result = sortService.bubbleSort(numbers);
        
        assertArrayEquals(expected, result);
    }

    @Test
    void testBubbleSortWithReverseSortedArray() {
        int[] numbers = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        
        int[] result = sortService.bubbleSort(numbers);
        
        assertArrayEquals(expected, result);
    }
}
