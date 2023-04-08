package kuit.springbasic.dao;

import kuit.springbasic.core.jdbc.connection.ConnectionManager;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDaoTest {

    private QuestionDao questionDao;
    private ResourceDatabasePopulator populator;

    @BeforeEach
    public void setup() {
        questionDao = new QuestionDao();
        populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void crud() throws SQLException {
        // create + read
        Question expected = new Question("writer", "title", "contents", 0);
        Question actual = questionDao.insert(expected);
        assertThat(expected.equals(actual)).isTrue();

        // update
        actual.updateTitleAndContents("title", "con");
        questionDao.update(actual);

        Question updated = questionDao.findByQuestionId(actual.getQuestionId());
        assertThat(actual.getTitle()).isEqualTo(updated.getTitle());
        assertThat(actual.getContents()).isEqualTo(updated.getContents());

        // delete
        questionDao.delete(actual.getQuestionId());
        assertThat(questionDao.findAll().size()).isEqualTo(6);
    }

}
