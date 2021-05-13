package com.revature.vinson_chin_p0.services;

import com.revature.vinson_chin_p0.daos.UserDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.AppUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private UserDAO mockUserDao;

    @Before
    public void setUp() {
        mockUserDao = mock(UserDAO.class);
        sut = new UserService(mockUserDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockUserDao = null;

    }

    @Test
    public void test_registerWithValidUserAndAvailableUsernameAndPassword() {

        // Arrange
        AppUser validUser = new AppUser(0, "un", "pw", "email", "fn", "ln", "1231-56-46", 21356);
        AppUser expectedResult = new AppUser(1, "un", "pw", "email", "fn", "ln", "1231-56-46", 21356);
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(any(), anyString())).thenReturn(true);
        when(mockUserDao.save(any(), validUser)).thenReturn(expectedResult);

        // Act
        AppUser actualResult = sut.register(validUser);

        // Assert
        assertEquals(expectedResult, actualResult);
        verify(mockUserDao, times(1)).isUsernameAvailable(any(), anyString());
        verify(mockUserDao, times(1)).isEmailAvailable(any(), anyString());
        verify(mockUserDao, times(1)).save(any(), any());
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser(0, "sdf", "pw", "email", "fn", "ln", "2021-12-25", 1213156));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(0)).isEmailAvailable(any(), anyString());
            verify(mockUserDao, times(0)).save(any(), any());
        }


    }

    @Test
    public void test_registerWithValidUserAndTakenEmail() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(any(), anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser(0, "un", "pw", "taken-email", "fn", "ln", "2151-05-20", 123549494));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(1)).isUsernameAvailable(any(), anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(any(), anyString());
            verify(mockUserDao, times(0)).save(any(), any());
        }


    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser() {
        // Arrange
        AppUser invalidUser = new AppUser("", "", "", "", "", "", 0);

        // Act
        sut.register(invalidUser);

        // Assert
        verify(mockUserDao.isUsernameAvailable(any(), anyString()), times(1));
        verify(mockUserDao.isEmailAvailable(any(), anyString()), times(0));


    }


}
