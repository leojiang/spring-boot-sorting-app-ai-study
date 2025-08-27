package com.cursor.tryout;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST controller for sorting operations.
 * Provides endpoints for sorting arrays using bubble sort algorithm.
 */
@RestController
@RequestMapping("/api")
@Validated
public final class SortController {

    /** The sort service for performing sorting operations. */
    private final SortService sortService;

    /**
     * Constructor for SortController.
     * @param service the sort service to use
     */
    public SortController(final SortService service) {
        this.sortService = service;
    }

    /**
     * Sorts an array of numbers provided as a query parameter.
     * @param numbersParam comma-separated string of numbers
     * @return SortResponse containing original and sorted arrays
     */
    @GetMapping("/sort")
    public SortResponse sortArray(
            @RequestParam("numbers")
            @NotEmpty(message = "Query parameter 'numbers' is required")
            @Pattern(regexp = "^\\s*-?\\d+(\\s*,\\s*-?\\d+)*\\s*$",
                    message = "Invalid format. Use comma-separated integers")
            final String numbersParam) {
        try {
            final String[] numberStrings = numbersParam.split(",");
            final int[] numbers = new int[numberStrings.length];

            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i].trim());
            }

            final int[] sortedNumbers = sortService.bubbleSort(numbers);
            return new SortResponse(numbers, sortedNumbers);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid number format in query parameter. "
                            + "Use comma-separated integers.");
        }
    }

    /**
     * Sorts an array of numbers provided in the request body.
     * @param request the sort request containing numbers to sort
     * @return SortResponse containing original and sorted arrays
     */
    @PostMapping("/sort")
    public SortResponse sortArrayPost(
            @Valid @RequestBody final SortRequest request) {
        final int[] numbers = request.getNumbers();
        if (numbers == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Field 'numbers' is required");
        }
        final int[] sortedNumbers = sortService.bubbleSort(numbers);
        return new SortResponse(numbers, sortedNumbers);
    }

    /**
     * Health check endpoint.
     * @return status message
     */
    @GetMapping("/health")
    public String health() {
        return "Sort API is running!";
    }

    /**
     * Request class for sorting operations.
     */
    public static final class SortRequest {
        /** The numbers array to sort. */
        @NotNull(message = "Field 'numbers' must not be null")
        @Size(min = 1, message = "Field must contain at least one element")
        private int[] numbers;

        /** Default constructor. */
        public SortRequest() { }

        /**
         * Constructor with numbers.
         * @param numbersParam the numbers to sort
         */
        public SortRequest(final int[] numbersParam) {
            this.numbers = numbersParam;
        }

        /**
         * Gets the numbers array.
         * @return the numbers array
         */
        public int[] getNumbers() {
            return numbers;
        }

        /**
         * Sets the numbers array.
         * @param numbersParam the numbers array to set
         */
        public void setNumbers(final int[] numbersParam) {
            this.numbers = numbersParam;
        }
    }

    /**
     * Response class for sorting operations.
     */
    public static final class SortResponse {
        /** The original unsorted array. */
        private int[] original;
        /** The sorted array. */
        private int[] sorted;

        /** Default constructor. */
        public SortResponse() { }

        /**
         * Constructor with original and sorted arrays.
         * @param originalParam the original array
         * @param sortedParam the sorted array
         */
        public SortResponse(final int[] originalParam,
                final int[] sortedParam) {
            this.original = originalParam;
            this.sorted = sortedParam;
        }

        /**
         * Gets the original array.
         * @return the original array
         */
        public int[] getOriginal() {
            return original;
        }

        /**
         * Sets the original array.
         * @param originalParam the original array to set
         */
        public void setOriginal(final int[] originalParam) {
            this.original = originalParam;
        }

        /**
         * Gets the sorted array.
         * @return the sorted array
         */
        public int[] getSorted() {
            return sorted;
        }

        /**
         * Sets the sorted array.
         * @param sortedParam the sorted array to set
         */
        public void setSorted(final int[] sortedParam) {
            this.sorted = sortedParam;
        }
    }
}
