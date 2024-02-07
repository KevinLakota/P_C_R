package Rest.Rest;

import org.springframework.web.bind.annotation.*;
import Rest.DAO.*;
import java.util.List;

@RestController
public class AnimalController {

    private final AnimalDao animalDao;

    public AnimalController(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @PostMapping("/animal/save")
    public Animal save(@RequestBody Animal animal) {
        return animalDao.save(animal);
    }

    @GetMapping("/animal/getByIdAnimal/{id}")
    public Animal getByIdAnimal(@PathVariable Long id) {
        return animalDao.getByIdAnimal(id);
    }

    @GetMapping("/animal/getAll")
    public List<Animal> getAll() {
        return animalDao.getAll();
    }

    @GetMapping("/animal/getByChipNumber/{chip}")
    public Animal getByChipNumber(@PathVariable String chip) {
        return animalDao.getByChipNumber(chip);
    }

    @GetMapping("/animal/getAllByOwnerId/{idOwner}")
    public List<Animal> getAllByOwnerId(@PathVariable Long idOwner) {
        return animalDao.getAllByOwnerId(idOwner);
    }

    @GetMapping("/animal/getAllByNameChipNumber/{name}/{chipNumber}")
    public Animal getAllByNameChipNumber(@PathVariable String name, @PathVariable String chipNumber) {
        return animalDao.getAllByNameChipNumber(name, chipNumber);
    }

    @PostMapping("/animal/savePhoto")
    public void savePhoto(String base64, Long id) {
        animalDao.savePhoto(base64, id);
    }

    @DeleteMapping("/animal/delete/{id}")
    public void delete(@PathVariable Long id) {
        animalDao.delete(id);
    }
}
