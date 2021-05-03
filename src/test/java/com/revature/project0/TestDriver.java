package com.revature.project0;

import com.revature.project0.util.LinkedListTest;

public class TestDriver {

    public static void main(String[] args) {
        LinkedListTest tester = new LinkedListTest();
        tester.test_add_withNull();
        tester.test_add_withNonNullValue();
    }

}
