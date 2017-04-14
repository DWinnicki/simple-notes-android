package com.winnicki.simplenotes.model;

/**
 * Created by winnicki on 2017-04-13.
 */

public class Authentication {
    private String question;
    private String answer;
    private String password;
    //private Note note;

    public Authentication(String question, String answer, String password) {
        this.question = question;
        this.answer = answer;
        this.password = password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Recovery Question: " + question +
                "\nAnswer: " + answer +
                "\nPassword: " + password;
    }
}
