package Rest.Rest;
import Rest.DAO.SpeciesDao;
import Rest.DAO.TreatmentAnimal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpeciesController {

    private final SpeciesDao speciesDao;

    public SpeciesController(SpeciesDao speciesDao) {
        this.speciesDao = speciesDao;
    }

    @PostMapping("/species/save")
    public TreatmentAnimal.Species save(TreatmentAnimal.Species species) {
        return speciesDao.save(species);
    }

    @GetMapping("/species/getById/{id}")
    public TreatmentAnimal.Species getById(@PathVariable Long id) {
        return speciesDao.getById(id);
    }

    @DeleteMapping("/species/delete/{id}")
    public void delete(@PathVariable Long id) {
        speciesDao.delete(id);
    }

    @GetMapping("/species/getAll")
    public List<TreatmentAnimal.Species> getAll() {
        return speciesDao.getAll();
    }
}
