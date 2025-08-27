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

import java.util.Arrays;

/**
 * REST controller for sorting operations.
 * Provides endpoints for sorting arrays using bubble sort algorithm.
 */
@RestController
@RequestMapping("/api")
@Validated
public class SortController {

    /** The sort service for performing sorting operations. */
    private final SortService sortService;

    /**
     * Constructor for SortController.
     * @param sortServiceParam the sort service to use
     */
    public SortController(final SortService sortServiceParam) {
        this.sortService = sortServiceParam;
    }

    /**
     * Health check endpoint.
     * @return health status message
     */
    @GetMapping("/health")
    public String health() {
        return "Sort API is running!";
    }

    /**
     * GET endpoint for sorting arrays.
     * @param numbersParam comma-separated string of numbers
     * @return SortResponse with original and sorted arrays
     */
    @GetMapping("/sort")
    public SortResponse sortArrayGet(
            @RequestParam("numbers")
            @NotEmpty(message = "Parameter 'numbers' must not be empty")
            @Pattern(regexp = "^\\d+(,\\d+)*$",
                    message = "Parameter 'numbers' must contain only "
                            + "comma-separated integers")
            final String numbersParam) {
        try {
            final int[] numbers = Arrays.stream(numbersParam.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return new SortResponse(numbers, sortService.bubbleSort(numbers));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid number format");
        }
    }

    /**
     * POST endpoint for sorting arrays.
     * @param request the sort request containing the array to sort
     * @return SortResponse with original and sorted arrays
     */
    @PostMapping("/sort")
    public SortResponse sortArrayPost(
            @Valid @RequestBody final SortRequest request) {
        return new SortResponse(request.getNumbers(),
                sortService.bubbleSort(request.getNumbers()));
    }

    /**
     * Request class for sorting operations.
     */
    public static final class SortRequest {
        /** The numbers array to sort. */
        @NotNull(message = "Field 'numbers' must not be null")
        @Size(min = 1, message = "Field must contain at least one element")
        private int[] numbers;

        /**
         * Get the numbers array.
         * @return the numbers array
         */
        public int[] getNumbers() {
            return numbers;
        }

        /**
         * Set the numbers array.
         * @param numbersParam the numbers array to set
         */
        public void setNumbers(final int[] numbersParam) {
            this.numbers = numbersParam;
        }

        /** Default constructor. */
        public SortRequest() { }
    }

    /**
     * Response class for sorting operations.
     */
    public static final class SortResponse {
        /** The original unsorted array. */
        private int[] original;
        /** The sorted array. */
        private int[] sorted;

        /**
         * Constructor for SortResponse.
         * @param originalParam the original array
         * @param sortedParam the sorted array
         */
        public SortResponse(final int[] originalParam,
                final int[] sortedParam) {
            this.original = originalParam;
            this.sorted = sortedParam;
        }

        /**
         * Get the original array.
         * @return the original array
         */
        public int[] getOriginal() {
            return original;
        }

        /**
         * Set the original array.
         * @param originalParam the original array to set
         */
        public void setOriginal(final int[] originalParam) {
            this.original = originalParam;
        }

        /**
         * Get the sorted array.
         * @return the sorted array
         */
        public int[] getSorted() {
            return sorted;
        }

        /**
         * Set the sorted array.
         * @param sortedParam the sorted array to set
         */
        public void setSorted(final int[] sortedParam) {
            this.sorted = sortedParam;
        }

        /** Default constructor. */
        public SortResponse() { }
    }
}
