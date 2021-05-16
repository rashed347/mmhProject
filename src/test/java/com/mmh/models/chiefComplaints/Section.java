package com.mmh.models.chiefComplaints;

import com.mmh.models.Question;

import java.util.List;

public class Section {
    public String header;
    public List<Question> questions;

    public Section(String header, List<Question> questions) {
        this.header = header;
        this.questions = questions;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
