package kuit.springbasic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class User {

    private String userId;

    private String password;

    private String name;

    private String email;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

}
