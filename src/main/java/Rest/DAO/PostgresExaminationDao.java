package Rest.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class PostgresExaminationDao implements ExaminationDao {

	private JdbcTemplate jdbcTemplate;

	public PostgresExaminationDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Examination save(Examination examination) {

		if (examination.getIdExam() == null) { // INSERT

			String query = "INSERT INTO examination (when_date, when_time, comment,id_emp,id_animal ) "
					+ "VALUES (?,?,?,?,?)";
			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				LocalDateTime d = examination.getDate();

				Date buffer = Date.valueOf(d.toLocalDate());

				statement.setDate(1, buffer);

				Time timeBuffer = Time.valueOf(d.toLocalTime());

				statement.setTime(2, /*
										 * new java.sql.Date(Date.from((examination.getDate()).atZone(ZoneId.
										 * systemDefault()).toInstant()).getTime())
										 */ timeBuffer);

				statement.setString(3, examination.getComment());

				statement.setObject(4, examination.getEmpl());
				statement.setObject(5, examination.getAnimal());

				return statement;
			}, keyHolder);
			Long id = Long.parseLong(String.valueOf(keyHolder.getKeys().get("id_exam")));
			return new Examination(id, examination.getDate(), examination.getComment(), examination.getEmpl(),
					examination.getAnimal());
		} else {

			String query = "UPDATE examination SET when_date=?, when_time=?, comment=?, id_emp=?, id_animal=?  "
					+ "WHERE id_exam=?";

			LocalDateTime d = examination.getDate();
			Date buffer = Date.valueOf(d.toLocalDate());
			Time timeBuffer = Time.valueOf(d.toLocalTime());

			int count = jdbcTemplate.update(query, /*
													 * new java.sql.Date(Date.from((examination.getDate()).atZone(ZoneId
													 * .systemDefault()).toInstant()).getTime())
													 */ buffer, timeBuffer, examination.getComment(),
					examination.getEmpl(), examination.getAnimal(), examination.getIdExam());

			if (count == 0) {
				String query1 = "INSERT INTO examination (id_exam, when_date, when_time, comment,id_emp,id_animal ) "
						+ "VALUES (?,?,?,?,?,?)";
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(con -> {
					PreparedStatement statement = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);

					statement.setObject(1, examination.getIdExam());

					LocalDateTime d1 = examination.getDate();
					Date buffer1 = Date.valueOf(d1.toLocalDate());
					statement.setDate(2, buffer1);

					Time timeBuffer1 = Time.valueOf(d1.toLocalTime());
					statement.setTime(3, timeBuffer1);

					statement.setString(4, examination.getComment());
					statement.setObject(5, examination.getEmpl());
					statement.setObject(6, examination.getAnimal());

					return statement;
				}, keyHolder);
				Long id = Long.parseLong(String.valueOf(keyHolder.getKeys().get("id_exam")));
				return new Examination(id, examination.getDate(), examination.getComment(), examination.getEmpl(),
						examination.getAnimal());
			}
			return examination;
		}

	}

	@Override
	public List<Examination> getByDate(LocalDate date) {
		String query = "SELECT * FROM examination WHERE when_date = " + "'" + date + "'" + ";";
		List<Examination> result = jdbcTemplate.query(query, new RowMapper<Examination>() {
			@Override
			public Examination mapRow(ResultSet rs, int rowNum) throws SQLException {
				Long id = rs.getLong("id_exam");
				String whenDate = rs.getString("when_date");
				String whenTime = rs.getString("when_time");
				String comment = rs.getString("comment");
				// String nameSuffix = rs.getString("photo");
				Long idEmp = rs.getLong("id_emp");
				Long idAnimal = rs.getLong("id_animal");

				String[] whenDateField = whenDate.split("-");
				String[] whenTimeField = whenTime.split(":");
				LocalDate ld = LocalDate.of(Integer.parseInt(whenDateField[0]), Integer.parseInt(whenDateField[1]),
						Integer.parseInt(whenDateField[2]));
				LocalTime lt = LocalTime.of(Integer.parseInt(whenTimeField[0]), Integer.parseInt(whenTimeField[1]));
				LocalDateTime ldt = LocalDateTime.of(ld, lt);

				return new Examination(id, ldt, comment, idEmp, idAnimal);

			}
		});

		return result;
	}

	@Override
	public List<LocalDate> getAllDate(Long idEmpl, Long idOwner) {
		String query = "";
		if (idEmpl != 0) {
			query = "SELECT when_date FROM examination;";
		} else {
			query = "SELECT when_date FROM examination JOIN animal ON examination.id_animal= animal.id_animal WHERE animal.id_owner = "
					+ idOwner;
		}
		List<LocalDate> result = jdbcTemplate.query(query, new RowMapper<LocalDate>() {
			@Override
			public LocalDate mapRow(ResultSet rs, int rowNum) throws SQLException {

				String whenDate = rs.getString("when_date");

				String[] whenDateField = whenDate.split("-");

				LocalDate ld = LocalDate.of(Integer.parseInt(whenDateField[0]), Integer.parseInt(whenDateField[1]),
						Integer.parseInt(whenDateField[2]));

				return ld;

			}
		});

		return result;
	}

	@Override
	public List<Examination> getAll() {
		String query = "SELECT * FROM examination;";
		List<Examination> result = jdbcTemplate.query(query, new RowMapper<Examination>() {
			@Override
			public Examination mapRow(ResultSet rs, int rowNum) throws SQLException {
				Long id = rs.getLong("id_exam");
				String whenDate = rs.getString("when_date");
				String whenTime = rs.getString("when_time");
				String comment = rs.getString("comment");
				// String nameSuffix = rs.getString("photo");
				Long idEmp = rs.getLong("id_emp");
				Long idAnimal = rs.getLong("id_animal");

				String[] whenDateField = whenDate.split("-");
				String[] whenTimeField = whenTime.split(":");
				LocalDate ld = LocalDate.of(Integer.parseInt(whenDateField[0]), Integer.parseInt(whenDateField[1]),
						Integer.parseInt(whenDateField[2]));
				LocalTime lt = LocalTime.of(Integer.parseInt(whenTimeField[0]), Integer.parseInt(whenTimeField[1]));
				LocalDateTime ldt = LocalDateTime.of(ld, lt);

				return new Examination(id, ldt, comment, idEmp, idAnimal);

			}
		});

		return result;
	}

	//gpt
	@Override
	public Long getUnusedId() {
		String query = "SELECT MIN(id_exam + 1) AS unused_id\n" + "FROM examination\n"
				+ "WHERE NOT EXISTS (SELECT 1 FROM examination t2 WHERE t2.id_exam  = examination .id_exam  + 1);";
		Long result = jdbcTemplate.queryForObject(query, new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				Long id = rs.getLong("unused_id");
				return new Long(id);
			}
		});
		return result;
	}

	@Override
	public void delete(Long idEmp) {
		String query = "DELETE FROM examination WHERE id_exam = " + idEmp + ";";
		int count = jdbcTemplate.update(query);
		if (count == 0) {
			throw new EntityNotFoundException("chyba");
		}
	}
	

	@Override
	public List<Examination> getByIdEmp(long id) {
		String query = "SELECT * FROM examination WHERE id_emp = " + id + ";";
		List<Examination> result = jdbcTemplate.query(query, new RowMapper<Examination>() {
			@Override
			public Examination mapRow(ResultSet rs, int rowNum) throws SQLException {
				Long id = rs.getLong("id_exam");
				String whenDate = rs.getString("when_date");
				String whenTime = rs.getString("when_time");
				String comment = rs.getString("comment");
				// String nameSuffix = rs.getString("photo");
				Long idEmp = rs.getLong("id_emp");
				Long idAnimal = rs.getLong("id_animal");

				String[] whenDateField = whenDate.split("-");
				String[] whenTimeField = whenTime.split(":");
				LocalDate ld = LocalDate.of(Integer.parseInt(whenDateField[0]), Integer.parseInt(whenDateField[1]),
						Integer.parseInt(whenDateField[2]));
				LocalTime lt = LocalTime.of(Integer.parseInt(whenTimeField[0]), Integer.parseInt(whenTimeField[1]));
				LocalDateTime ldt = LocalDateTime.of(ld, lt);

				return new Examination(id, ldt, comment, idEmp, idAnimal);
			}
		});

		return result;
	}
}
