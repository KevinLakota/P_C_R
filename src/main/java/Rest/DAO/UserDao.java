package Rest.DAO;

import java.util.List;

public interface UserDao {

	User save (User user);
	User getByUsername (String username);
	User getByIdOwner(Long idOwner);
	void delete(Long idUser);
	List<User> getAll();
	User getByIdEmp(Long idOwner);
}
