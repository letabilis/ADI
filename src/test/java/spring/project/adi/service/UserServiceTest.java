package spring.project.adi.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.time.Instant;
import java.util.UUID;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import spring.project.adi.dto.CreateUserDTO;
import spring.project.adi.mapper.UserMapper;
import spring.project.adi.model.User;
import spring.project.adi.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    // Act
    // Arrange
    // Assert

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;


    @Nested
    class createUser{

        @Test
        void shouldSucessfullyCreateUser() {

            // Arrange
            var userSample = new User(
                UUID.randomUUID(), 
                "name", 
                "email", 
                "password", 
                Instant.now(), 
                Instant.now()
            );

            doReturn(userSample).when(userRepository).save(userArgumentCaptor.capture());

            var input = new CreateUserDTO("Markus Jenkins", "markus@popcorn.com", "789");
            
            var userCaptured = userArgumentCaptor.getValue();


            // Act
            var output = userService.createUser(input);
            

            // Assert
            assertNotNull(output);


            assertEquals(input.name(), userCaptured.getName());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
        }

        @Test
        void shouldThrowExceptionWhenWhenErrorOccurs() {
            // Arrange

            doThrow(new RuntimeException()).when(userRepository).save(userArgumentCaptor.capture());

            var input = new CreateUserDTO("name", "email@email.com", "123");

            // Act && Assert
            assertThrows(RuntimeException.class, () -> userService.createUser(input));
        }
    }

    @Nested
    class getUserById {

        @Test
        void shouldSucessfullyGetUserByIdWhenOptionalIsPresent() {
            // Arrange
            var userSample = new User(
                UUID.randomUUID(), 
                "name", 
                "email", 
                "password", 
                Instant.now(), 
                Instant.now()
            );
            doReturn(Optional.of(userSample)).when(userRepository).findById(uuidArgumentCaptor.capture());

            var input = userSample.getId();

            // Act

            var output = userService.getUserById(input);

            // Assert

            assertTrue(output.isPresent());
            assertEquals(userSample.getId(), userArgumentCaptor.getValue());

        }
        void shouldUnsucessfullyGetUserByIdWhenOptionalIsEmpty() {
            // Arrange
          
            var input = UUID.randomUUID();

            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());


            // Act

            var output = userService.getUserById(input);

            // Assert
            
            assertTrue(output.isEmpty());
            assertEquals(input, userArgumentCaptor.getValue());

        }
    }
    

    @Nested
    class listUsers {

        @Test
        void shouldSucessfullyRetrieveUserList() {
            // Arrange
            List<User> userList = new ArrayList<>(Arrays.asList(
                new User(
                    UUID.randomUUID(), 
                    "nameOne", 
                    "emailOne@email.com", 
                    "987", 
                    Instant.now(), 
                    null
                ), 
                new User(
                UUID.randomUUID(), 
                "nameTwo",
                 "emailTwo@email.com", 
                 "654", 
                 Instant.now(), 
                 null
                 )
            )); 
                
            doReturn(userList).when(userRepository).findAll();


            // Act
            var output = userService.listUsers();

            // Assert
            assertTrue(output.containsAll(userMapper.toDTOList(userList)));
        }
        
        @Test
        void shouldRetrieveEmptyUserList() {
            // Arrange

            List<User> emptyUserList = new ArrayList<>();
            
            doReturn(emptyUserList).when(userRepository).findAll();


            // Act
            var output = userService.listUsers();

            // Assert
            
            assertEquals(output, emptyUserList);
        }
    }


    @Nested
    class deleteUserById {

        @Test
        void testName() {

        }

    }

    @Nested
    class updateUserById {

        @Test
        void testName() {

        }

    }
    
}
