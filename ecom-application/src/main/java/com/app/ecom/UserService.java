package com.app.ecom;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    public List<User> fetchAllUsers() {
        return userList;
    }

    public List<User> addUsers(User user) {
        user.setId(nextId++);
        userList.add(user);
        return userList;
    }

    public Optional<User> fetchUser(Long id) {

 /*       for(User user : userList){
            if(Objects.equals(user.getId(), id)){
                return user;
            }
        }
        return null;
  */
        return userList.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    }

    public boolean updateUser(Long id, User updateUser){
        return userList.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updateUser.getFirstName());
                    existingUser.setLastName(updateUser.getLastName());
                    return true;
                }).orElse(false);
    }
}
