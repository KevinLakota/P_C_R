package Rest.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostgresDashboardODAO implements DashboardODAO {

	private JdbcTemplate jdbcTemplate;

	public PostgresDashboardODAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public int registeredPets(Long idOwner) {
		String query = "SELECT * FROM animal WHERE id_owner = " + idOwner + "AND castration = true ;";
		List<Animal> result = jdbcTemplate.query(query, new RowMapper<Animal>() {
			@Override
			public Animal mapRow(ResultSet rs, int rowNum) throws SQLException{
				long id = rs.getLong("id_animal");
				String name = rs.getString("name");
				Date birth_date = rs.getDate("birth_date");
				int weight = rs.getInt("weight");
				boolean castration = rs.getBoolean("castration");
				String sex = rs.getString("sex");
				String originCountryCode = rs.getString("origin_country_code");
				String note = rs.getString("note");
				//Image photo =  new Image(rs.getString("photo"));
				Date dateLastVisit = rs.getDate("date_last_visit");
				Date dateNextVisit = rs.getDate("date_next_visit");
				String chipNumber = rs.getString("chip_number");
				Long idOwner = rs.getLong("id_owner");
				Long idSpecies = rs.getLong("id_kind");
				return new Animal(id,name,birth_date,weight,castration,sex,
						originCountryCode,note,/*photo,*/dateLastVisit,dateNextVisit,
						chipNumber,idOwner,idSpecies);
			}
		});

		return result.size();
	}

	@Override
	public long countryOriginCount(Long idOwner) {
		String query = "SELECT COUNT(DISTINCT origin_country_code) as count FROM animal WHERE id_owner = " + idOwner + ";";
		long result = jdbcTemplate.queryForObject(query, new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException{
				long id = rs.getLong("count");

				return id;
			}
		});

		return result;
	}

	@Override
	public long doneExaminatons(Long idOwner) {
		String query = "SELECT COUNT(id_exam) as count FROM examination as e "
				+ "JOIN animal as a on a.id_animal = e.id_animal "
				+ "WHERE a.id_owner = " + idOwner + " AND e.when_date < CURRENT_DATE;";
		long result = jdbcTemplate.queryForObject(query, new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException{
				long id = rs.getLong("count");

				return id;
			}
		});

		return result;

	}

	@Override
	public String lastExaminatons(Long idOwner) {
		String query = "SELECT MAX(e.when_date) as lastExam FROM examination as e "
				+ "JOIN animal as a on a.id_animal = e.id_animal "
				+ "WHERE a.id_owner = " + idOwner + " AND e.when_date < CURRENT_DATE;";
		String result = jdbcTemplate.queryForObject(query, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException{
				String id = rs.getString("lastExam");

				return id;
			}
		});

		return result;

	}

	@Override
	public long upcomingEvents(Long idOwner) {
		String query = "SELECT COUNT(id_exam) as count FROM examination as e "
				+ "JOIN animal as a on a.id_animal = e.id_animal "
				+ "WHERE a.id_owner = " + idOwner + " AND e.when_date > CURRENT_DATE;";
		long result = jdbcTemplate.queryForObject(query, new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException{
				long id = rs.getLong("count");

				return id;
			}
		});

		return result;

	}
}
