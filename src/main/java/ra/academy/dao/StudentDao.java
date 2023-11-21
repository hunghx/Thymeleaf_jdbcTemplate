package ra.academy.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ra.academy.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDao {
    private JdbcTemplate jdbcTemplate;

//    public StudentDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // chứ năng thêm mới
    public int save(Student s){
        String sql = "Insert into student (name, age, sex,address) values(?,?,?,?)";
        return jdbcTemplate.update(sql,s.getName(),s.getAge(),s.isSex(),s.getAddress());
    }
    public int update(Student s){
        String sql = "update student set name=?, age=?, sex=?,address=? where id =?";
        return jdbcTemplate.update(sql,s.getName(),s.getAge(),s.isSex(),s.getAddress(),s.getId());
    }
    public int delete(Long id){
        String sql = "delete  from student where id =?";
        return jdbcTemplate.update(sql,id);
    }
    public Student findById(Long id){
        String sql = "select * from student where id =?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(Student.class));
    }
    public List<Student> findAll(){
        String sql = "select * from student";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
           Student s = new Student();
           s.setId(rs.getLong("id"));
           s.setName(rs.getString("name"));
           s.setAge(rs.getInt("age"));
           s.setSex(rs.getBoolean("sex"));
           s.setAddress(rs.getString("address"));
           return s;
        });
    }


}
