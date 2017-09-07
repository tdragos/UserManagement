package netgloo.controllers;

import netgloo.models.User;
import netgloo.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/create")
    @ResponseBody
    @PostMapping(produces = "application/json")
    public String create(@RequestBody User user) {
        try {
            userDao.save(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    @RequestMapping("/delete")
    @ResponseBody
    @DeleteMapping(produces = "application/json")
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        } catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    @RequestMapping("/email")
    @ResponseBody
    @GetMapping(produces = "application/json")
    public String getByEmail(String email) {
        String userId;
        try {
            User user = userDao.findByEmail(email);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    @PutMapping(produces = "application/json")
    public String updateUser(@RequestBody User user) {
        try {
            User updatedUser = userDao.findOne(user.getId());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setName(user.getName());
            userDao.save(updatedUser);
        } catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

}
