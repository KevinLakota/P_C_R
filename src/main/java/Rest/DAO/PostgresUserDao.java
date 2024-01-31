package Rest.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostgresUserDao implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public PostgresUserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User save(User user) {

		if (user.getIdUser() == null) { // INSERT

			String query = "INSERT INTO users (username, passwd, salt,admin,id_owner,id_empl) "
					+ "VALUES (?,?,?,?,?,?)";
			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPasswd());
				statement.setBytes(3, user.getSalt());
				statement.setBoolean(4, user.isAdmin());
				if(user.getIdOwner() == null) statement.setObject(5, user.getIdOwner());
				else statement.setLong(5, user.getIdOwner());
				if(user.getIdEmpl() == null) statement.setObject(6, user.getIdEmpl());
				else statement.setLong(6, user.getIdEmpl());

				return statement;
			} , keyHolder);
			Long id = Long.parseLong(String.valueOf(keyHolder.getKeys().get("id_user")));

			return new User(id, user.getUsername(), user.getPasswd(), user.getSalt(), user.isAdmin(), user.getIdOwner(), user.getIdEmpl());
		}else {
			// tym ze ten riadok uz existuje tak iba prepisem heslo a salt a netreba robit osobibtnu dalsiu metodu

			Long idE = null, idO = null;
			if(user.getIdEmpl() != 0)  idE = user.getIdEmpl();
			if(user.getIdOwner() != 0)  idO = user.getIdOwner();


			String query = "UPDATE users SET username=?, passwd=?, salt=?, admin=?, id_owner=?, id_empl=?  "
					+ "WHERE id_user=?";
			int count = jdbcTemplate.update(query,user.getUsername(), user.getPasswd(), user.getSalt(),
					user.isAdmin(), idO, idE, user.getIdUser());

			/*int count = jdbcTemplate.update(con -> {
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPasswd());
				statement.setBytes(3, user.getSalt());
				statement.setBoolean(4, user.isAdmin());
				if(user.getIdOwner() == null) statement.setObject(5, user.getIdOwner());
				else statement.setLong(5, user.getIdOwner());
				if(user.getIdEmpl() == null) statement.setObject(6, user.getIdEmpl());
				else statement.setLong(6, user.getIdEmpl());
				statement.setLong(7, user.getIdUser());

				return statement;
			});*/

			if(count == 0 ) {
				throw new EntityNotFoundException("chyba");
			}

			return new User(user.getIdUser(), user.getUsername(), user.getPasswd(), user.getSalt(), user.isAdmin(), idO, idE);

			//return user;
		}
	}

	@Override
	public User getByUsername(String email) {
		User result = null;
		String query = "SELECT * FROM users WHERE username = " + "'" + email + "'" +";";

		try {
			result = jdbcTemplate.queryForObject(query, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
					Long id = rs.getLong("id_user");
					String username = rs.getString("username");
					String passwd = rs.getString("passwd");
					byte[] salt = rs.getBytes("salt");
					boolean admin = rs.getBoolean("admin");
					Long idOwner = rs.getLong("id_owner");
					Long idEmpl = rs.getLong("id_empl");
					System.out.println(username);
					return new User(id,username,passwd,salt,admin,idOwner,idEmpl);
				}
			});
		}
		catch (Exception EmptyResultDataAccessException) {
			return null;
		}

		return result;
	}

	@Override
	public User getByIdEmp(Long idOwner) {
		User result = null;
		String query = "SELECT * FROM users WHERE id_empl = " +  idOwner  +";";

		try {
			result = jdbcTemplate.queryForObject(query, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
					Long id = rs.getLong("id_user");
					String username = rs.getString("username");
					String passwd = rs.getString("passwd");
					byte[] salt = rs.getBytes("salt");
					boolean admin = rs.getBoolean("admin");
					Long idOwner = rs.getLong("id_owner");
					Long idEmpl = rs.getLong("id_empl");
					System.out.println(username);
					return new User(id,username,passwd,salt,admin,idOwner,idEmpl);
				}
			});
		}
		catch (Exception EmptyResultDataAccessException) {
			return null;
		}

		return result;
	}

	@Override
	public User getByIdOwner(Long idOwner) {
		User result = null;
		String query = "SELECT * FROM users WHERE id_owner = " +  idOwner  +";";

		try {
			result = jdbcTemplate.queryForObject(query, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
					Long id = rs.getLong("id_user");
					String username = rs.getString("username");
					String passwd = rs.getString("passwd");
					byte[] salt = rs.getBytes("salt");
					boolean admin = rs.getBoolean("admin");
					Long idOwner = rs.getLong("id_owner");
					Long idEmpl = rs.getLong("id_empl");
					System.out.println(username);
					return new User(id,username,passwd,salt,admin,idOwner,idEmpl);
				}
			});
		}
		catch (Exception EmptyResultDataAccessException) {
			return null;
		}

		return result;
	}

	@Override
	public List<User> getAll() {
		String query = "SELECT * FROM users;";


		List<User> result = jdbcTemplate.query(query, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
					Long id = rs.getLong("id_user");
					String username = rs.getString("username");
					String passwd = rs.getString("passwd");
					byte[] salt = rs.getBytes("salt");
					boolean admin = rs.getBoolean("admin");
					Long idOwner = rs.getLong("id_owner");
					Long idEmpl = rs.getLong("id_empl");
					System.out.println(username);
					return new User(id,username,passwd,salt,admin,idOwner,idEmpl);
				}
			});



		return result;
	}

	@Override
	public void delete(Long idUser) {
		String query = "DELETE FROM users WHERE id_user = " + idUser + ";";
		int count = jdbcTemplate.update(query);
		if(count == 0) {
			throw new EntityNotFoundException("chyba");
		}
	}

}
