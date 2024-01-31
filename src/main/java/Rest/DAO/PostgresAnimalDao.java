package Rest.DAO;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class PostgresAnimalDao implements AnimalDao {

	private JdbcTemplate jdbcTemplate;

	public PostgresAnimalDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Animal save(Animal animal) {

		if (animal.getIdAnimal() == null) { // INSERT

			String query = "INSERT INTO animal (name, birth_date, weight, castration, sex, "
					+ "origin_country_code, note, base64_photo, date_last_visit, "
					+ "date_next_visit, chip_number, id_owner, id_kind) "
					+ "VALUES (?,?,?,?,CAST(? AS sex_type),?,?,?,?,?,?,?,?)";

			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, animal.getName());

				statement.setDate(2, animal.getBirthDate());

				statement.setInt(3, animal.getWeight());
				statement.setBoolean(4, animal.isCastration());
				statement.setString(5, animal.getSex());
				statement.setString(6, animal.getOriginCountryCode());
				statement.setString(7, animal.getNote());
				statement.setString(8, animal.getPhoto());
				statement.setDate(9, animal.getDateNextVisit());
				statement.setDate(10, animal.getDateLastVisit());

				statement.setString(11, animal.getChipNumber());
				if (animal.getIdOwner() == null)
					statement.setObject(12, animal.getIdOwner());
				else
					statement.setLong(12, animal.getIdOwner());
				if (animal.getIdSpecies() == null)
					statement.setObject(13, animal.getIdSpecies());
				else
					statement.setLong(13, animal.getIdSpecies());
				return statement;
			},keyHolder);

			Long id = Long.parseLong(String.valueOf(keyHolder.getKeys().get("id_animal")));

			return new Animal(id, animal.getName(), animal.getBirthDate(), animal.getWeight(),
					animal.isCastration(), animal.getSex(), animal.getOriginCountryCode(), animal.getNote(),
					animal.getPhoto(), animal.getDateLastVisit(), animal.getDateNextVisit(), animal.getChipNumber(),
					animal.getIdOwner(), animal.getIdSpecies());

		} else {
			Long idS = null, idO = null;
			if (animal.getIdOwner() != 0)
				idO = animal.getIdOwner();
			if (animal.getIdSpecies() != 0)
				idS = animal.getIdSpecies();

			String query = "UPDATE animal SET name=?, birth_date=?, weight=?, castration=?, sex=CAST(? AS sex_type), "
					+ "origin_country_code=?, note=?, base64_photo=?, date_last_visit=?,"
					+ "date_next_visit=?, chip_number=?, id_owner=?, id_kind=?" + "WHERE id_animal=?";
			jdbcTemplate.update(query, animal.getName(), animal.getBirthDate(), animal.getWeight(),
					animal.isCastration(), animal.getSex(), animal.getOriginCountryCode(), animal.getNote(),
					animal.getPhoto(), animal.getDateLastVisit(), animal.getDateNextVisit(), animal.getChipNumber(),
					idO, idS, animal.getIdAnimal());

			return new Animal(animal.getIdAnimal(),animal.getName(), animal.getBirthDate(), animal.getWeight(),
					animal.isCastration(), animal.getSex(), animal.getOriginCountryCode(), animal.getNote(),
					animal.getPhoto(), animal.getDateLastVisit(), animal.getDateNextVisit(), animal.getChipNumber(),
					idO, idS);
		}

	}

	@Override
	public void savePhoto(String base64, Long id) {

		if (id == null) { // INSERT
			String query = "INSERT INTO animal (base64_photo) " + "VALUES (?)";
			jdbcTemplate.update(con -> {
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, base64);
				return statement;
			});
		} else {
			String query = "UPDATE animal SET base64_photo=? " + "WHERE id_animal=?";
			jdbcTemplate.update(query, base64, id);
		}

	}

	@Override
	public Animal getByIdAnimal(Long id) {
		String query = "SELECT * FROM animal WHERE id_animal = " + id + ";";
		Animal result = null;

		try {
			result = jdbcTemplate.queryForObject(query, new RowMapper<Animal>() {
				@Override
				public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
					long id = rs.getLong("id_animal");
					String name = rs.getString("name");
					Date birth_date = rs.getDate("birth_date");
					int weight = rs.getInt("weight");
					boolean castration = rs.getBoolean("castration");
					String sex = rs.getString("sex");
					String originCountryCode = rs.getString("origin_country_code");
					String note = rs.getString("note");
					String photo = rs.getString("base64_photo");
					Date dateLastVisit = rs.getDate("date_last_visit");
					Date dateNextVisit = rs.getDate("date_next_visit");
					String chipNumber = rs.getString("chip_number");
					Long idOwner = rs.getLong("id_owner");
					Long idSpecies = rs.getLong("id_kind");
					return new Animal(id, name, birth_date, weight, castration, sex, originCountryCode, note, photo,
							dateLastVisit, dateNextVisit, chipNumber, idOwner, idSpecies);
				}
			});
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
		return result;
	}

	@Override
	public Animal getByChipNumber(String chip) {
		String query = "SELECT * FROM animal WHERE chip_number = '" + chip + "';";
		Animal result = null;
		try {
			result = jdbcTemplate.queryForObject(query, new RowMapper<Animal>() {
				@Override
				public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
					long id = rs.getLong("id_animal");
					String name = rs.getString("name");
					Date birth_date = rs.getDate("birth_date");
					int weight = rs.getInt("weight");
					boolean castration = rs.getBoolean("castration");
					String sex = rs.getString("sex");
					String originCountryCode = rs.getString("origin_country_code");
					String note = rs.getString("note");
					String photo = rs.getString("base64_photo");
					Date dateLastVisit = rs.getDate("date_last_visit");
					Date dateNextVisit = rs.getDate("date_next_visit");
					String chipNumber = rs.getString("chip_number");
					Long idOwner = rs.getLong("id_owner");
					Long idSpecies = rs.getLong("id_kind");
					return new Animal(id, name, birth_date, weight, castration, sex, originCountryCode, note, photo,
							dateLastVisit, dateNextVisit, chipNumber, idOwner, idSpecies);
				}
			});
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
		return result;
	}

	@Override
	public List<Animal> getAll() {
		String query = "SELECT * FROM animal;";
		List<Animal> result = jdbcTemplate.query(query, new RowMapper<Animal>() {
			@Override
			public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getLong("id_animal");
				String name = rs.getString("name");
				Date birth_date = rs.getDate("birth_date");
				int weight = rs.getInt("weight");
				boolean castration = rs.getBoolean("castration");
				String sex = rs.getString("sex");
				String originCountryCode = rs.getString("origin_country_code");
				String note = rs.getString("note");
				String photo = rs.getString("base64_photo");
				Date dateLastVisit = rs.getDate("date_last_visit");
				Date dateNextVisit = rs.getDate("date_next_visit");
				String chipNumber = rs.getString("chip_number");
				Long idOwner = rs.getLong("id_owner");
				Long idSpecies = rs.getLong("id_kind");
				return new Animal(id, name, birth_date, weight, castration, sex, originCountryCode, note, photo,
						dateLastVisit, dateNextVisit, chipNumber, idOwner, idSpecies);
			}
		});
		return result;
	}

	@Override
	public List<Animal> getAllByOwnerId(Long idOwner) {
		String query = "SELECT * FROM animal WHERE id_owner = " + idOwner + " ORDER BY name;";
		List<Animal> result = jdbcTemplate.query(query, new RowMapper<Animal>() {
			@Override
			public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getLong("id_animal");
				String name = rs.getString("name");
				Date birth_date = rs.getDate("birth_date");
				int weight = rs.getInt("weight");
				boolean castration = rs.getBoolean("castration");
				String sex = rs.getString("sex");
				String originCountryCode = rs.getString("origin_country_code");
				String note = rs.getString("note");
				String photo = rs.getString("base64_photo");
				Date dateLastVisit = rs.getDate("date_last_visit");
				Date dateNextVisit = rs.getDate("date_next_visit");
				String chipNumber = rs.getString("chip_number");
				Long idOwner = rs.getLong("id_owner");
				Long idSpecies = rs.getLong("id_kind");
				return new Animal(id, name, birth_date, weight, castration, sex, originCountryCode, note, photo,
						dateLastVisit, dateNextVisit, chipNumber, idOwner, idSpecies);
			}
		});
		return result;
	}

	@Override
	public Animal getAllByNameChipNumber(String name, String chipNumber) {
		String query = "SELECT * FROM animal WHERE name = " + "'" + name + "'" + " AND chip_number = " + "'"
				+ chipNumber + "'" + ";";
		Animal result = null;
		try {
			result = jdbcTemplate.queryForObject(query, new RowMapper<Animal>() {
				@Override
				public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
					long id = rs.getLong("id_animal");
					String name = rs.getString("name");
					Date birth_date = rs.getDate("birth_date");
					int weight = rs.getInt("weight");
					boolean castration = rs.getBoolean("castration");
					String sex = rs.getString("sex");
					String originCountryCode = rs.getString("origin_country_code");
					String note = rs.getString("note");
					String photo = rs.getString("base64_photo");
					Date dateLastVisit = rs.getDate("date_last_visit");
					Date dateNextVisit = rs.getDate("date_next_visit");
					String chipNumber = rs.getString("chip_number");
					Long idOwner = rs.getLong("id_owner");
					Long idSpecies = rs.getLong("id_kind");
					return new Animal(id, name, birth_date, weight, castration, sex, originCountryCode, note, photo,
							dateLastVisit, dateNextVisit, chipNumber, idOwner, idSpecies);
				}
			});
		}catch (EmptyResultDataAccessException e) {
			return null;
		}

		return result;
	}

	@Override
	public void delete(Long idEmp) {


			String query = "DELETE FROM animal WHERE id_animal = " + idEmp + ";";
		try {
			jdbcTemplate.update(query);

		} catch (DataIntegrityViolationException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Input Error");
			alert.setHeaderText(null);
			alert.setContentText("For a given animal, there are associated examinations. Delete them first, and only then you can delete the animal.");
			alert.showAndWait();
		}
	}

}
