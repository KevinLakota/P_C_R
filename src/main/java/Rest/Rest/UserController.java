package Rest.Rest;

import Rest.DAO.User;
import Rest.DAO.UserDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/user/save")
    public User save(@RequestBody User user) {
        return userDao.save(user);
    }

    @GetMapping("/user/getByUsername/{username}")
    public User getByUsername(@PathVariable String username) {
        return userDao.getByUsername(username);
    }

    @GetMapping("/user/getByIdOwner/{idOwner}")
    public User getByIdOwner(@PathVariable Long idOwner) {
        return userDao.getByIdOwner(idOwner);
    }

    @GetMapping("/user/getByIdEmp/{idEmp}")
    public User getByIdEmp(@PathVariable Long idEmp) {
        return userDao.getByIdEmp(idEmp);
    }

    @DeleteMapping("/user/delete/{id}")
    public void delete(@PathVariable Long id) {
        userDao.delete(id);
    }

    @GetMapping("/user/getAll")
    public List<User> getAll() {
        return userDao.getAll();
    }
}
