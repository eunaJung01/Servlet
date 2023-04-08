package kuit.springbasic.web.dao;

import kuit.springbasic.core.jdbc.JdbcTemplate;
import kuit.springbasic.web.domain.Answer;

import java.util.List;

public class AnswerDao {

    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    public List<Answer> findAllByQuestionId(int questionId) {
        String sql = "select * from answers where questionId=? order by answerId";
        return jdbcTemplate.query(sql,
                pstmt -> pstmt.setObject(1, questionId),
                rs -> new Answer(rs.getInt("answerId"),
                        rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getDate("createdDate")));
    }

}
