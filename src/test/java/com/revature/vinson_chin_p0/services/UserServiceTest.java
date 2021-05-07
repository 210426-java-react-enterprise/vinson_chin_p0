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
        AppUser validUser = new AppUser(0, "un", "pw", "email", "fn", "ln", "");
        AppUser expectedResult = new AppUser(1, "un", "pw", "email", "fn", "ln", "");
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(true);
        when(mockUserDao.save(validUser)).thenReturn(expectedResult);

        // Act
        AppUser actualResult = sut.register(validUser);

        // Assert
        assertEquals(expectedResult, actualResult);
        verify(mockUserDao, times(1)).isUsernameAvailable(anyString());
        verify(mockUserDao, times(1)).isEmailAvailable(anyString());
        verify(mockUserDao, times(1)).save(any());
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser(0, "sdf", "pw", "email", "fn", "ln", ""));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(0)).isEmailAvailable(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }

    @Test
    public void test_registerWithValidUserAndTakenEmail() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser(0, "un", "pw", "taken-email", "fn", "ln", ""));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(1)).isUsernameAvailable(anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser() {
        // Arrange
        AppUser invalidUser = new AppUser("", "", "", "", "", "");

        // Act
        sut.register(invalidUser);

        // Assert
        verify(mockUserDao.isUsernameAvailable(anyString()), times(1));
        verify(mockUserDao.isEmailAvailable(anyString()), times(0));


    }


}

// Stubbing
//class UserDAOStub extends UserDAO {
//    @Override
//    public void save(AppUser newUser) {
//        newUser.setId(1);
//    }
//
//    @Override
//    public boolean isUsernameAvailable(String username) {
//        return true;
//    }
//
//    @Override
//    public boolean isEmailAvailable(String email) {
//        return true;
//    }
//
//    @Override
//    public AppUser findUserByUsernameAndPassword(String username, String password) {
//        AppUser fakeUser = new AppUser(username, password, "fake", "fake", "fake", 18);
//        fakeUser.setId(1);
//        return fakeUser;
//    }
//}
