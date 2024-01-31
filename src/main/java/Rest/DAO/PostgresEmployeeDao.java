package Rest.DAO;

import io.micrometer.common.util.internal.logging.JdkLoggerFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class PostgresEmployeeDao implements EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    public PostgresEmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Employee save(Employee employee) {

        if (employee.getIdEmpl() == null) { // INSERT

            String query = "INSERT INTO employee (name_prefix, first_name, surname, name_suffix, "
                    + "birthdate, graduation_date, sex, hour_wage, phone_number, "
                    + "email, contract_num, base64_photo, id_specialty) "
                    + "VALUES (?,?,?,?,?,?,CAST(? AS sex_type),?,?,?,?,?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, employee.getNamePefix());
                statement.setString(2, employee.getFirstName());
                statement.setString(3, employee.getSurname());
                statement.setString(4, employee.getNameSuffix());
                statement.setDate(5, employee.getBirthDate());
                statement.setDate(6, employee.getGraduationDate());
                statement.setString(7, employee.getSex());
                statement.setDouble(8, employee.getHourWage());
                statement.setString(9, employee.getPhoneNumber());
                statement.setString(10, employee.getEmail());
                statement.setInt(11, employee.getContractNumber());
                statement.setString(12, employee.getPhoto());
                if (employee.getIdSpecialty() == null) statement.setObject(13, employee.getIdSpecialty());
                else statement.setLong(13, employee.getIdSpecialty());

                return statement;
            }, keyHolder);
            Long id = Long.parseLong(String.valueOf(keyHolder.getKeys().get("id_emp")));
            return new Employee(id, employee.getNamePefix(), employee.getFirstName(), employee.getSurname(), employee.getNameSuffix(), employee.getBirthDate()
                    , employee.getGraduationDate(), employee.getSex(), employee.getHourWage(), employee.getPhoneNumber(), employee.getEmail(),
                    employee.getContractNumber(), employee.getPhoto(), employee.getIdSpecialty());
        } else {
            Long idS = null;
            if (employee.getIdSpecialty() != 0) idS = employee.getIdSpecialty();


            String query = "UPDATE employee SET name_prefix=?, first_name=?, surname=?, name_suffix=?, birthdate=?, "
                    + "graduation_date=?, sex=CAST(? AS sex_type), hour_wage=?, phone_number=?,"
                    + "email=?, contract_num=?, base64_photo=?, id_specialty=?"
                    + "WHERE id_emp=?";
            jdbcTemplate.update(query, employee.getNamePefix(), employee.getFirstName(), employee.getSurname(), employee.getNameSuffix(),
                    employee.getBirthDate(), employee.getGraduationDate(), employee.getSex(), employee.getHourWage(), employee.getPhoneNumber(),
                    employee.getEmail(), employee.getContractNumber(), employee.getPhoto() != null ? employee.getPhoto() : null, idS, employee.getIdEmpl());

            return new Employee(employee.getIdEmpl(), employee.getNamePefix(), employee.getFirstName(), employee.getSurname(), employee.getNameSuffix(), employee.getBirthDate()
                    , employee.getGraduationDate(), employee.getSex(), employee.getHourWage(), employee.getPhoneNumber(), employee.getEmail(),
                    employee.getContractNumber(), employee.getPhoto(), employee.getIdSpecialty());
        }

    }

    @Override
    public Employee getByEmpId(Long id) {
        String query = "SELECT * FROM employee WHERE id_emp = " + id + ";";
        Employee result = null;

        try {
            result = jdbcTemplate.queryForObject(query, new RowMapper<Employee>() {
                @Override
                public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                    long id = rs.getLong("id_emp");
                    String namePrefix = rs.getString("name_prefix");
                    String firstName = rs.getString("first_name");
                    String surname = rs.getString("surname");
                    String nameSuffix = rs.getString("name_suffix");
                    Date birthdate = rs.getDate("birthdate");
                    Date graduationDate = rs.getDate("graduation_date");
                    String sex = rs.getString("sex");
                    Double hourWage = rs.getDouble("hour_wage");
                    String phoneNumber = rs.getString("phone_number");
                    String email = rs.getString("email");
                    int contractNum = rs.getInt("contract_num");
                    String photo = rs.getString("base64_photo");
                    Long idSpecialty = rs.getLong("id_specialty");
                    return new Employee(id, namePrefix, firstName, surname, nameSuffix, birthdate,
                            graduationDate, sex, hourWage, phoneNumber, email,
                            contractNum, photo, idSpecialty);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }


        return result;
    }

    @Override
    public List<Employee> getAll() {
        String query = "SELECT * FROM employee ORDER BY id_emp;";
        List<Employee> result = jdbcTemplate.query(query, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id_emp");
                String namePrefix = rs.getString("name_prefix");
                String firstName = rs.getString("first_name");
                String surname = rs.getString("surname");
                String nameSuffix = rs.getString("name_suffix");
                Date birthdate = rs.getDate("birthdate");
                Date graduationDate = rs.getDate("graduation_date");
                String sex = rs.getString("sex");
                Double hourWage = rs.getDouble("hour_wage");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                int contractNum = rs.getInt("contract_num");
                String photo = rs.getString("base64_photo");
                Long idSpecialty = rs.getLong("id_specialty");
                return new Employee(id, namePrefix, firstName, surname, nameSuffix, birthdate,
                        graduationDate, sex, hourWage, phoneNumber, email,
                        contractNum, photo, idSpecialty);
            }
        });

        return result;
    }

    @Override
    public void savePhoto(String base64, Long id) {

        if (id == null) { // INSERT
            String query = "INSERT INTO employee (base64_photo) " + "VALUES (?)";
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, base64);
                return statement;
            });
        } else {
            String query = "UPDATE employee SET base64_photo=? " + "WHERE id_emp=?";
            jdbcTemplate.update(query, base64, id);
        }

    }

    @Override
    public void delete(Long idEmp) {
        String query = "DELETE FROM employee WHERE id_emp = " + idEmp + ";";
        try {
            jdbcTemplate.update(query);

        } catch (DataIntegrityViolationException e) {
            jdbcTemplate.update(query);
        }
    }
}
