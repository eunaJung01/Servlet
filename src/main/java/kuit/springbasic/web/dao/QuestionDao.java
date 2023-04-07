package kuit.springbasic.web.dao;

import kuit.springbasic.core.jdbc.JdbcTemplate;
import kuit.springbasic.web.domain.Question;

import java.util.List;

public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public List<Question> findAll() {
        String sql = "select * from questions order by questionId";
        return jdbcTemplate.query(sql, rs ->
                new Question(rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("countOfAnswer")));
    }

}
