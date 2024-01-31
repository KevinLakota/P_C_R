package Rest.DAO;

import java.util.List;

public interface AnimalDao {
	Animal save (Animal user);
	Animal getByIdAnimal(Long id);
	List<Animal> getAll();
	Animal getByChipNumber(String chip);
	List<Animal> getAllByOwnerId(Long idOwner);
	Animal getAllByNameChipNumber(String name, String chipNumber);
	void savePhoto(String base64, Long id);
	public void delete(Long idEmp);
}
