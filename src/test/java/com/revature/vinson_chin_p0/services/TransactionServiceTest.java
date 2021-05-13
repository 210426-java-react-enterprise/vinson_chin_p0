package com.revature.vinson_chin_p0.services;

import com.revature.vinson_chin_p0.daos.TransactionDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.models.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Tests for TransactionService.
 *
 */

public class TransactionServiceTest {

    private TransactionService sut;
    private TransactionDAO mockTransactionDao;

    @Before
    public void setUp() {
        mockTransactionDao = mock(TransactionDAO.class);
        sut = new TransactionService(mockTransactionDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockTransactionDao = null;
    }

    @Test
    public void test_createWithValidTransaction() {

        // Arrange
        Transaction validTransaction = new Transaction(0, 1, 1.0, "type");
        Transaction expectedResult = new Transaction(1, 1, 1.0, "type");
        when(mockTransactionDao.save(eq(validTransaction))).thenReturn(expectedResult);

        // Act
        Transaction actualResult = sut.create(validTransaction);

        // Assert
        assertEquals(expectedResult, actualResult);
        verify(mockTransactionDao, times(1)).save(any());
    }

    @Test(expected = InvalidRequestException.class)
    public void test_createWithInvalidTransaction() {
        // Arrange
        Transaction invalidTransaction = new Transaction(0,0,0.0,"");

        // Act
        sut.create(invalidTransaction);

        // Assert
        verify(mockTransactionDao.save(any()), times(0));

    }
}
