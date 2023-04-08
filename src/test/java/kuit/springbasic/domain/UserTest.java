package kuit.springbasic.domain;

import kuit.springbasic.web.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private static final String userId = "euna";
    private static final String password = "1";
    private static final String name = "aralla";
    private static final String email = "christinejung10@daum.net";

    private static final String password_updated = "2";
    private static final String name_updated = "orollo";

    private User user;

    @BeforeEach
    void init() {
        user = new User(userId, password, name, email);
    }

    @Test
    void isSameUser() {
        assertThat(user.equals(new User(userId, password))).isTrue();
    }

    @Test
    void update() {
        User user1 = new User(userId, password_updated, name_updated, email);
        user.update(user1);

        assertThat(user.getPassword()).isEqualTo(password_updated);
        assertThat(user.getName()).isEqualTo(name_updated);
        assertThat(user.getEmail()).isEqualTo(email);
    }

}
