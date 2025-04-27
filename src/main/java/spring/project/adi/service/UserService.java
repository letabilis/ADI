package spring.project.adi.service;

import org.springframework.stereotype.Service;
import spring.project.adi.repository.UserRepository;
import spring.project.adi.mapper.UserMapper;
import spring.project.adi.dto.CreateUserDTO;
import spring.project.adi.dto.GetUserDTO;
import spring.project.adi.dto.UpdateUserDTO;

import java.util.List;
import java.util.Optional;
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

    public Optional<GetUserDTO> getUserById(UUID userId) {
        return userRepository
        .findById(userId)
        .map(this.userMapper::toDTO);  
    }

    public List<GetUserDTO> listUsers(){
        return this.userMapper.toDTOList(this.userRepository.findAll());
    }

    public void deleteUserById(UUID userId) {
        if (this.userRepository.existsById(userId)) {
            this.userRepository.deleteById(userId);
        }
    }

    public void updateUserById(UUID userId, UpdateUserDTO updateUserDTO) {

        var userEntity = this.userRepository.findById(userId);
        if (userEntity.isPresent()) {
            var user = userEntity.get();
            
            var name = updateUserDTO.name();
            var password = updateUserDTO.password();

            if (name != null) {
                user.setName(updateUserDTO.name());
            }

            if (password != null) {
                user.setPassword(updateUserDTO.password());
            }

            this.userRepository.save(user);
        }
    }
}
