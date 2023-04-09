package kuit.servlet.web.dao;

import kuit.servlet.core.jdbc.JdbcTemplate;
import kuit.servlet.core.jdbc.KeyHolder;
import kuit.servlet.web.domain.Answer;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {

    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    public Answer insert(Answer answer) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();
        String sql = "insert into answers (writer, contents, createdDate, questionId) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setObject(3, answer.getCreatedDate());
            pstmt.setObject(4, answer.getQuestionId());
        }, keyHolder);
        return findById(keyHolder.getId());
    }

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

    public Answer findById(int answerId) throws SQLException {
        String sql = "select answerId, writer, contents, createdDate, questionId from answers where answerId = ?";
        return jdbcTemplate.queryForObject(sql,
                pstmt -> pstmt.setObject(1, answerId),
                rs -> new Answer(rs.getInt("answerId"),
                        rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getDate("createdDate")));
    }

}
