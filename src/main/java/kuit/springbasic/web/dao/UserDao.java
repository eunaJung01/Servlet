package kuit.springbasic.web.dao;

import kuit.springbasic.core.jdbc.JdbcTemplate;
import kuit.springbasic.domain.User;

public class UserDao {

    JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

    public void insert(User user) {
        String sql = "insert into users values (?, ?, ?, ?)";

        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

}
