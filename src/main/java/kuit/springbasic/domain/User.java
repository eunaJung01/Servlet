package kuit.springbasic.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class User {

    private String userId;

    private String password;

    private String name;

    private String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public boolean isSameUser(User loggedInUser) {
        return isSameUser(loggedInUser.userId, loggedInUser.password);
    }

    public boolean isSameUser(String userId, String password) {
        return hasSameUserId(userId) && hasSamePassword(password);
    }

    public boolean hasSameUserId(String userId) {
        return userId.equals(this.userId);
    }

    public boolean hasSamePassword(String password) {
        if (password == null) {
            return false;
        }
        return password.equals(this.password);
    }

}
