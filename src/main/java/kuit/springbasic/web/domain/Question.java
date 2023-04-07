package kuit.springbasic.web.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.sql.Date;

@EqualsAndHashCode
@Getter
public class Question {

    private int questionId;

    private String writer;

    private String title;

    private String contents;

    private Date createdDate;

    private int countOfAnswer;

    public Question(int questionId, String writer, String title, String contents, Date createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

}
