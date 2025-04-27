package spring.project.adi.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import spring.project.adi.mapper.UserMapper;
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

    @Nested
    class createUser{

        @Test
        void testName() {
            
        }

    }


    class getUserById {

        @Test
        void testName() {

        }
    }
    

    class listUsers {

        @Test
        void testName() {

        }
    }

    
    class deleteUserById {

        @Test
        void testName() {

        }

    }


    class updateUserById {

        @Test
        void testName() {

        }

    }
    
}
