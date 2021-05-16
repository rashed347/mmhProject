package com.mmh.models.generalForm;

import com.mmh.models.Question;

import java.util.List;

public class GeneralForm {
    public String tabName;
    public List<Question> questions;

    public GeneralForm(String tabName, List<Question> questions) {
        this.tabName = tabName;
        this.questions = questions;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
