package com.ncr.challenge;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.google.common.hash.Hashing;
import com.ncr.challenge.entities.User;
import com.ncr.challenge.exceptions.InvalidPasswordResponseException;
import com.ncr.challenge.exceptions.ResponseException;
import com.ncr.challenge.exceptions.UserNotFoundResponseException;
import com.ncr.challenge.models.SessionInfoModel;
import com.ncr.challenge.repositories.UserRepository;
import com.ncr.challenge.services.LoginService;


@SpringBootTest
class LoginTest {
  
  @Autowired
  UserRepository userRepository;

  User mockUser() {
    User user = new User();
    user.setUserName("perez");
    user.setPassword("PPerez99");
    return user;
  }

  String hashPassword(String password) {
    return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
  }
  
  @Test
  void testLoginSuccessful() throws UserNotFoundResponseException, InvalidPasswordResponseException {
    LoginService loginService = new LoginService();

    String perezHashedPass = hashPassword(mockUser().getPassword());

    Optional<User> optionalUser = userRepository.findByUserName(mockUser().getUserName());
    SessionInfoModel loggedUser = loginService.doLogin(optionalUser, perezHashedPass);
    assertEquals("perez" , loggedUser.getUser().getId());
  }
  
  @Test
  void testLoginUserNotFound() throws UserNotFoundResponseException, InvalidPasswordResponseException {
    LoginService loginService = new LoginService();

    String perezHashedPass = hashPassword(mockUser().getPassword());

    Optional<User> optionalUser = userRepository.findByUserName("perezABC");
    
    ResponseException exception = assertThrows(UserNotFoundResponseException.class, () -> {
      loginService.doLogin(optionalUser, perezHashedPass);
    });

    assertTrue(exception.getField().contains("user"));
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    assertEquals("Usuario no encontrado.", exception.getMessage());
  }
  
  @Test
  void testLoginWrongPass() throws UserNotFoundResponseException, InvalidPasswordResponseException {
    LoginService loginService = new LoginService();

    String perezHashedWrongPass = hashPassword("PPPerez99");

    Optional<User> optionalUser = userRepository.findByUserName(mockUser().getUserName());

    ResponseException exception = assertThrows(InvalidPasswordResponseException.class, () -> {
      loginService.doLogin(optionalUser, perezHashedWrongPass);
    });

    assertTrue(exception.getField().contains("password"));
    assertEquals(HttpStatus.FORBIDDEN, exception.getStatus());
    assertTrue(exception.getMessage().contains("Password"));
  }
}
