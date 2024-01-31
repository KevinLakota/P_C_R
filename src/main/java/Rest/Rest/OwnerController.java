package Rest.Rest;
import org.springframework.web.bind.annotation.*;
import Rest.DAO.*;
import java.util.List;

@RestController
public class OwnerController {

    private final OwnerDao ownerDao;

    public OwnerController(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @PostMapping("/owner/save")
    public Owner save(Owner owner) {
        return ownerDao.save(owner);
    }

    @GetMapping("/owner/getByPhoneNumber/{phoneNumber}")
    public Owner getByPhoneNumber(@PathVariable String phoneNumber) {
        return ownerDao.getByPhoneNumber(phoneNumber);
    }

    @GetMapping("/owner/getByIdOwner/{idOwner}")
    public Owner getById(@PathVariable Long idOwner) {
        return ownerDao.getById(idOwner);
    }

    @GetMapping("/owner/getAll")
    public List<Owner> getAll() {
        return ownerDao.getAll();
    }

    @DeleteMapping("/owner/delete/{idOwner}")
    public void delete(@PathVariable Long idOwner) {
        ownerDao.delete(idOwner);
    }

    @GetMapping("/owner/getByEmail/{email}")
    public Owner getByEmail(@PathVariable String email) {
        return ownerDao.getByEmail(email);
    }
}
