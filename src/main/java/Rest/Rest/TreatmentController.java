package Rest.Rest;

import Rest.DAO.TreatmentAnimal;
import org.springframework.web.bind.annotation.*;
import Rest.DAO.TreatmentDao;

import java.util.List;

@RestController
public class TreatmentController {

    private final TreatmentDao treatmentDao;

    public TreatmentController(TreatmentDao treatmentDao) {
        this.treatmentDao = treatmentDao;
    }
    @PostMapping("/treatment/save")
    public void save(TreatmentAnimal.Treatment treatment) {
        treatmentDao.save(treatment);
    }

    @DeleteMapping("/treatment/delete/{idTreatment}")
    public void delete(@PathVariable Long idTreatment) {
        treatmentDao.delete(idTreatment);
    }

    @GetMapping("/treatment/getAll")
    public List<TreatmentAnimal.Treatment> getAll() {
        return treatmentDao.getAll();
    }
}
