package com.revature.vinson_chin_p0.util;

import org.junit.*;

/**
 * Tests for LinkedList
 *
 */

public class LinkedListTest {

    private LinkedList<String> sut;

    @Before
    public void setUpTest() {
        sut = new LinkedList<>();
    }

    @After
    public void tearDownTest() {
        sut = null;
    }

    @Test
    public void test_addWithNonNullValue() {
        // Arrange (prepare the test)
        int expectedSize = 1;

        // Act (do the test)
        sut.add("data");

        // Assert (ensure the results)
        int actualSize = sut.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = Exception.class)
    public void test_addWithNullValue() {
        // Arrange
        // sometimes blank if there's nothing in particular to do

        // Act
        sut.add(null);

        // Assert
        // sometimes blank, especially if you expect an exception to be thrown
    }

    @Test
    public void test_pollWithEmptyList() {
        // Arrange
        // nothing to do here...

        // Act
        String actualResult = sut.poll();

        // Assert
        Assert.assertNull(actualResult);
    }

    @Test
    public void test_pollWithPopulatedList() {
        // Arrange
        sut.add("test data 1");
        sut.add("test data 2");
        String expectedResult = "test data 1";
        int expectedSize = 1;

        // Act
        String actualResult = sut.poll();

        // Assert
        int actualSize = sut.size();
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void test_peekWithEmptyList() {

        String actualResult = sut.peek();

        Assert.assertNull(actualResult);

    }

    @Test
    public void test_peekWithPopulatedList() {

        sut.add("test data 1");
        sut.add("test data 2");
        String expectedResult = "test data 1";
        int expectedSize = 2;

        String actualResult = sut.peek();

        int actualSize = sut.size();
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSize, actualSize);

    }

    @Test
    public void test_containsWithEmptyList() {

        Boolean actualResult = sut.contains("test data 1");

        Assert.assertEquals(false, actualResult);

    }

    @Test
    public void test_containsWithPopulatedList() {

        sut.add("test data 1");
        sut.add("test data 2");

        Boolean actualResult = sut.contains("test data 2");

        Assert.assertEquals(true, actualResult);

    }



}
