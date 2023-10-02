package pepitosfret.io.pepitosfret.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pepitosfret.io.pepitosfret.DTO.UserDTO;
import pepitosfret.io.pepitosfret.models.Role;
import pepitosfret.io.pepitosfret.models.User;
import pepitosfret.io.pepitosfret.repository.RoleRepository;
import pepitosfret.io.pepitosfret.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User CreateUser(UserDTO userDTO, Set<String>roleName){
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        Set<Role> roles = roleName.stream()
                .map(roleRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return userRepository.save(user);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long UserId){
        userRepository.deleteById(UserId);
    }



}
