package com.revature.vinson_chin_p0.services;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Tests for AccountService.
 *
 */

public class AccountServiceTest {

    private AccountService sut;
    private AccountDAO mockAccountDao;

    @Before
    public void setUp() {
        mockAccountDao = mock(AccountDAO.class);
        sut = new AccountService(mockAccountDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockAccountDao = null;
    }

    @Test
    public void test_createWithValidAccountAndAvailableName() {

        // Arrange
        Account validAccount = new Account(0, 1, 0.0, "type", "test");
        Account expectedResult = new Account(1, 1, 0.0, "type", "test");
        when(mockAccountDao.isNameAvailable(any(), anyString(), anyInt())).thenReturn(true);
        when(mockAccountDao.save(any(), eq(validAccount))).thenReturn(expectedResult);

        // Act
        Account actualResult = sut.create(validAccount);

        // Assert
        assertEquals(expectedResult, actualResult);
        verify(mockAccountDao, times(1)).isNameAvailable(any(), anyString(), anyInt());
        verify(mockAccountDao, times(1)).save(any(), any());
    }

    @Test
    public void test_createWithValidAccountAndTakenName() {
        // Arrange
        when(mockAccountDao.isNameAvailable(any(), anyString(), anyInt())).thenReturn(false);

        // Act
        try {
            sut.create(new Account(0, 1, 0.0, "type", "test"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockAccountDao, times(0)).save(any(), any());
        }


    }

    @Test(expected = InvalidRequestException.class)
    public void test_createWithInvalidAccount() {
        // Arrange
        Account invalidAccount = new Account(0,0,"","");

        // Act
        sut.create(invalidAccount);

        // Assert
        verify(mockAccountDao.isNameAvailable(any(), anyString(), anyInt()), times(1));

    }

    @Test
    public void test_updateWithValidAccountAndAvailableName() {

        // Arrange
        Account validAccount = new Account(0, 1, 0.0, "type", "test");
        Account expectedResult = new Account(1, 1, 0.0, "type", "test");
        when(mockAccountDao.isUpdatedNameAvailable(any(), anyString(), anyInt(), anyInt())).thenReturn(true);
        when(mockAccountDao.update(any(), eq(validAccount))).thenReturn(expectedResult);

        // Act
        Account actualResult = sut.update(validAccount);

        // Assert
        assertEquals(expectedResult, actualResult);
        verify(mockAccountDao, times(1)).isUpdatedNameAvailable(any(), anyString(), anyInt(), anyInt());
        verify(mockAccountDao, times(1)).update(any(), any());
    }

    @Test
    public void test_updateWithValidAccountAndTakenName() {
        // Arrange
        when(mockAccountDao.isUpdatedNameAvailable(any(), anyString(), anyInt(), anyInt())).thenReturn(false);

        // Act
        try {
            sut.update(new Account(0, 1, 0.0, "type", "test"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockAccountDao, times(0)).update(any(), any());
        }


    }

    @Test(expected = InvalidRequestException.class)
    public void test_updateWithInvalidAccount() {
        // Arrange
        Account invalidAccount = new Account(0,0,"","");

        // Act
        sut.update(invalidAccount);

        // Assert
        verify(mockAccountDao.isNameAvailable(any(), anyString(), anyInt()), times(1));

    }

    @Test
    public void test_updateBalanceWithValidAccount() {

        // Arrange
        Account validAccount = new Account(0, 1, 0.0, "type", "test");
        Account expectedResult = new Account(1, 1, 0.0, "type", "test");
        when(mockAccountDao.updateBalance(eq(validAccount))).thenReturn(expectedResult);

        // Act
        Account actualResult = sut.updateBalance(validAccount);

        // Assert
        assertEquals(expectedResult, actualResult);
        verify(mockAccountDao, times(1)).updateBalance(any());
    }

    @Test(expected = InvalidRequestException.class)
    public void test_updateBalanceWithInvalidAccount() {
        // Arrange
        Account invalidAccount = new Account(0,0,"","");

        // Act
        sut.updateBalance(invalidAccount);

        // Assert
        verify(mockAccountDao.updateBalance(any()), times(0));

    }
}
