package pepitosfret.io.pepitosfret.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pepitosfret.io.pepitosfret.DTO.UserDTO;
import pepitosfret.io.pepitosfret.models.User;
import pepitosfret.io.pepitosfret.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDto){
        User newUser = userService.CreateUser(userDto,userDto.getRoles());
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> userOpt = userService.findById(id);
        if(userOpt.isPresent()){
            return ResponseEntity.ok(userOpt.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User updateduser = userService.updateUser(user);
       return new ResponseEntity<>(updateduser,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
