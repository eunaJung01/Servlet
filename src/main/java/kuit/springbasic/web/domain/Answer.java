package kuit.springbasic.web.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
@EqualsAndHashCode
public class Answer {

    private int answerId;

    private int questionId;

    private String writer;

    private String contents;

    private Date createdDate;

    public Answer(int answerId, int questionId, String writer, String contents, Date createdDate) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
    }

}
