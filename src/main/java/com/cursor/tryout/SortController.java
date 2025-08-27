package com.cursor.tryout;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@Validated
public class SortController {

    private final SortService sortService;

    public SortController(SortService sortService) {
        this.sortService = sortService;
    }

    @GetMapping("/sort")
    public SortResponse sortArray(
            @RequestParam("numbers")
            @NotEmpty(message = "Query parameter 'numbers' is required")
            @Pattern(regexp = "^\\s*-?\\d+(\\s*,\\s*-?\\d+)*\\s*$", message = "Invalid format. Use comma-separated integers")
            String numbersParam) {
        try {
            String[] numberStrings = numbersParam.split(",");
            int[] numbers = new int[numberStrings.length];

            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i].trim());
            }

            int[] sortedNumbers = sortService.bubbleSort(numbers);
            return new SortResponse(numbers, sortedNumbers);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid number format in query parameter. Use comma-separated integers.");
        }
    }

    @PostMapping("/sort")
    public SortResponse sortArrayPost(@Valid @RequestBody SortRequest request) {
        int[] numbers = request.getNumbers();
        if (numbers == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'numbers' is required");
        }
        int[] sortedNumbers = sortService.bubbleSort(numbers);
        return new SortResponse(numbers, sortedNumbers);
    }

    @GetMapping("/health")
    public String health() {
        return "Sort API is running!";
    }

    // Request and Response classes
    public static class SortRequest {
        @NotNull(message = "Field 'numbers' must not be null")
        @Size(min = 1, message = "Field 'numbers' must contain at least one element")
        private int[] numbers;

        public SortRequest() {}

        public SortRequest(int[] numbers) {
            this.numbers = numbers;
        }

        public int[] getNumbers() {
            return numbers;
        }

        public void setNumbers(int[] numbers) {
            this.numbers = numbers;
        }
    }

    public static class SortResponse {
        private int[] original;
        private int[] sorted;

        public SortResponse() {}

        public SortResponse(int[] original, int[] sorted) {
            this.original = original;
            this.sorted = sorted;
        }

        public int[] getOriginal() {
            return original;
        }

        public void setOriginal(int[] original) {
            this.original = original;
        }

        public int[] getSorted() {
            return sorted;
        }

        public void setSorted(int[] sorted) {
            this.sorted = sorted;
        }
    }
}
