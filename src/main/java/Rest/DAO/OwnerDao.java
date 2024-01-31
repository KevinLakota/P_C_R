package Rest.DAO;

import java.util.List;

public interface OwnerDao {
	Owner save (Owner owner);
	Owner getByPhoneNumber(String phoneNumber);
	Owner getById(Long idOwner);
	List<Owner> getAll();
	void delete(Long idEmp);
	Owner getByEmail(String email);
}
