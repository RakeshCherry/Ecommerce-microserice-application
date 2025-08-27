package com.app.ecom.service;

import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

/*   private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;
 */

    private final UserRepository userRepository;

    public List<UserResponse> fetchAllUsers() {
//        return userList;
//        return userRepository.findAll();
        return userRepository.findAll().stream()
                .map(this:: mapToUserResponse)
                .collect(Collectors.toList());

    }



    public void addUsers(UserRequest userRequest) {
//        user.setId(nextId++);
//        userList.add(user);
//        return userList;
        User user = new User();
        updateUserFromUpdate(user, userRequest);
        userRepository.save(user);
    }

    public Optional<UserResponse> fetchUser(Long id) {

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
        return userRepository.findById(id)
                .map(this:: mapToUserResponse);
    }

    public boolean updateUser(Long id, UserRequest updateUserRequest){
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
                    updateUserFromUpdate(existingUser, updateUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    private void updateUserFromUpdate(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        if(userRequest.getAddress() != null){
            Address address = new Address();
            address.setCountry(userRequest.getAddress().getCountry());
            address.setCity(userRequest.getAddress().getCity());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setStreet(userRequest.getAddress().getStreet());
            address.setState(userRequest.getAddress().getState());
            user.setAddress(address);
        }
    }

    public UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(String.valueOf(user.getPhoneNumber()));
        response.setRole(user.getRole());

        if(user.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setState(user.getAddress().getState());
            response.setAddress(addressDTO);
        }
        return response;
    }
}
