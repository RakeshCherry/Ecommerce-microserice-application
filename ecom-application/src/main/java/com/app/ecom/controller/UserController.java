package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

//    private List<User> userList = new ArrayList<>();

    public final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> fetchUser(@PathVariable Long id) {
/*        User user = userService.fetchUser(id);
        if(user != null)
        return ResponseEntity.ok(userService.fetchUser(id));
        return ResponseEntity.notFound().build();
 */
        return userService.fetchUser(id)
                .map(ResponseEntity:: ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUsers(@RequestBody UserRequest userRequest) {
        userService.addUsers(userRequest);
        return ResponseEntity.ok("New User Added Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                           @RequestBody UserRequest updateUserRequest) {
        boolean updated = userService.updateUser(id, updateUserRequest);
        if(updated)
            return ResponseEntity.ok("User Updated Successfully");
        return ResponseEntity.notFound().build();


    }
}
