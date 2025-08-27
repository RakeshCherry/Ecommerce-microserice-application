package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

/*   private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;
 */

    private final UserRepository userRepository;

    public List<User> fetchAllUsers() {
//        return userList;
        return userRepository.findAll();
    }

    public void addUsers(User user) {
//        user.setId(nextId++);
//        userList.add(user);
//        return userList;
        userRepository.save(user);
    }

    public Optional<User> fetchUser(Long id) {

 /*       for(User user : userList){
            if(Objects.equals(user.getId(), id)){
                return user;
            }
        }
        return null;
  */

   /*
        return userList.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    */
        return userRepository.findById(id);
    }

    public boolean updateUser(Long id, User updateUser){
    /*    return userList.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updateUser.getFirstName());
                    existingUser.setLastName(updateUser.getLastName());
                    return true;
                }).orElse(false);
     */
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateUser.getFirstName());
                    existingUser.setLastName(updateUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
