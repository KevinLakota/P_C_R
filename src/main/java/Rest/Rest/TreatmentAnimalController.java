package Rest.Rest;

import Rest.DAO.TreatmentAnimal;
import Rest.DAO.TreatmentAnimalDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TreatmentAnimalController {

    private final TreatmentAnimalDao treatmentAnimalDao;

    public TreatmentAnimalController(TreatmentAnimalDao treatmentAnimalDao) {
        this.treatmentAnimalDao = treatmentAnimalDao;
    }

    @PostMapping("/treatmentAnimal/save")
    public void save(@RequestBody TreatmentAnimal ta) {
        treatmentAnimalDao.save(ta);
    }

    @DeleteMapping("/treatmentAnimal/delete/{when}")
    public void delete(@PathVariable String when) {
        treatmentAnimalDao.delete(when);
    }

    @GetMapping("/treatmentAnimal/getAll")
    public List<TreatmentAnimal> getAll() {
        return treatmentAnimalDao.getAll();
    }
}
