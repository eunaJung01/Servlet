package kuit.servlet.dao;

import kuit.servlet.core.jdbc.connection.ConnectionManager;
import kuit.servlet.web.dao.UserDao;
import kuit.servlet.web.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    private static UserDao userDao;
    private ResourceDatabasePopulator populator;

    @BeforeEach
    public void setup() {
        userDao = new UserDao();
        populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void crud() throws Exception {
        // create
        User expected = new User("userId", "password", "name", "jungwoo@email.com");
        userDao.insert(expected);
        User actual = userDao.findByUserId(expected.getUserId());
        assertThat(expected.equals(actual)).isTrue();

        // update
        expected.update(new User("userId", "password2", "name2", "jungwoo2@email.com"));
        userDao.update(expected);
        actual = userDao.findByUserId(expected.getUserId());
        assertThat(expected.equals(actual)).isTrue();

        // read
        List<User> users = userDao.findAll();
        assertThat(users.size()).isEqualTo(2);
    }

}
