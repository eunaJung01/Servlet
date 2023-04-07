package kuit.springbasic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class Question {

    private int questionId;

    private String writer;

    private String title;

    private String contents;

    private Date createdDate;

    private int countOfAnswer;

}
