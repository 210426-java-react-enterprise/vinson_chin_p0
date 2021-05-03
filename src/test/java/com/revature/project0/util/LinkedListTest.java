package com.revature.project0.util;

import com.revature.project0.screens.Screen;

public class LinkedListTest {

    private LinkedList<String> sut;
    private LinkedList<? extends Screen> ex1; // generics with subtyping
    private LinkedList<?> ex2; // the ? denotes wildcard

    public void test_add_withNull() {

        // Arrange test
        sut = new LinkedList<>();

        // Act (perform the action to be tested)
        try {
            sut.add(null);
            System.err.println("Test: test_add_withNull did not pass!");
        } catch (IllegalArgumentException e) {
            // Assert
            System.out.println("Test: test_add_withNull passed!");
        }

    }

    public void test_add_withNonNullValue() {

        // Arrange (set up the test)
        sut = new LinkedList<>();

        // Act (do the test)
        sut.add("not null!!");

        // Assert (verify outcomes)
        if (sut.size() == 2) {
            System.out.println("Test: test_add_withNonNullValue passed!");
        } else {
            System.out.println("Test: test_add_withNonNullValue did not pass!");
        }

        assert sut.size() == 2;

    }
}
