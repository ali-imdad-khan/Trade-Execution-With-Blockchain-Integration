package org.example.trade_settlement.Service;

import org.example.trade_settlement.Model.User;
import org.example.trade_settlement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow( ()-> new RuntimeException("User not found."));
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
