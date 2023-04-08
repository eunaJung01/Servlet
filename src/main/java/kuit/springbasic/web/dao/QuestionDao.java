package kuit.springbasic.web.dao;

import kuit.springbasic.core.jdbc.JdbcTemplate;
import kuit.springbasic.core.jdbc.KeyHolder;
import kuit.springbasic.web.domain.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public Question insert(Question question) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();
        String sql = "insert into questions (writer, title, contents, createdDate) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setObject(4, question.getCreatedDate());
        }, keyHolder);
        return findByQuestionId(keyHolder.getId());
    }

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

    public Question findByQuestionId(int questionId) throws SQLException {
        String sql = "select questionId, writer, title, contents, createdDate, countOfAnswer from questions where questionId=?";
        return jdbcTemplate.queryForObject(sql,
                pstmt -> pstmt.setObject(1, questionId),
                rs -> new Question(rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("countOfAnswer")));
    }

}
