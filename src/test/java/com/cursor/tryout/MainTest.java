package com.cursor.tryout;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMainClassExists() {
        // Test that the Main class can be instantiated
        assertNotNull(Main.class);
    }

    @Test
    void testSortServiceIntegration() {
        // Test that the service can be used independently
        SortService sortService = new SortService();
        int[] numbers = {5, 2, 8, 1, 9};
        int[] expected = {1, 2, 5, 8, 9};
        
        int[] result = sortService.bubbleSort(numbers);
        assertArrayEquals(expected, result);
    }
}
