package spring.project.adi.service;

import org.springframework.stereotype.Service;
import spring.project.adi.repository.UserRepository;
import spring.project.adi.mapper.UserMapper;
import spring.project.adi.dto.CreateUserDTO;


import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UUID createUser(CreateUserDTO createUserDTO){
        var userEntity = this.userMapper.toEntity(createUserDTO);
        var savedUser = this.userRepository.save(userEntity);
        return savedUser.getId();
    }
}
