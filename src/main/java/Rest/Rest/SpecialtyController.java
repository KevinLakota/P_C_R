package Rest.Rest;
import Rest.DAO.Specialty;
import org.springframework.web.bind.annotation.*;
import Rest.DAO.SpecialtyDao;
import java.util.List;

@RestController
public class SpecialtyController {

    private final SpecialtyDao specialtyDao;

    public SpecialtyController(SpecialtyDao specialtyDao) {this.specialtyDao = specialtyDao;}

    @PostMapping("/specialty/save")
    public Specialty save(@RequestBody Specialty specialty) {
        return specialtyDao.save(specialty);
    }

    @GetMapping("/specialty/getByOwnerId/{id}")
    public Specialty getByOwnerId(@PathVariable Long id) {
        return specialtyDao.getByOwnerId(id);
    }

    @GetMapping("/specialty/getAll")
    public List<Specialty> getAll() {
        return specialtyDao.getAll();
    }

    @DeleteMapping("/specialty/delete/{id}")
    public void delete(@PathVariable Long id) {
        specialtyDao.delete(id);
    }
}
