package com.mmh.models;

import java.util.List;

public class Question {
    public String title;
    public String questionId;
    public String fieldType;
    public String answer;

    public Question(String title, String questionId, String fieldType, String answer) {
        this.title = title;
        this.questionId = questionId;
        this.fieldType = fieldType;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
