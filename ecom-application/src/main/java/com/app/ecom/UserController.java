package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private List<User> userList = new ArrayList<>();

    public final UserService userService = new UserService();

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> fetchUser(@PathVariable Long id) {
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
    public ResponseEntity<String> createUsers(@RequestBody User user) {
        userService.addUsers(user);
        return ResponseEntity.ok("New User Added Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                           @RequestBody User updateUser) {
        boolean updated = userService.updateUser(id, updateUser);
        if(updated)
            return ResponseEntity.ok("User Updated Successfully");
        return ResponseEntity.notFound().build();


    }
}
