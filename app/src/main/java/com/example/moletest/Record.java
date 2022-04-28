package com.example.moletest;

import java.time.LocalDate;
import java.util.Date;

public class Record {
    private int score;
    private String user;
//    private Date date;

    public Record(int score, String user) {
        this.score = score;
        this.user = user;
//        this.date = date;
    }



    public String getScore() {
        return String.valueOf(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }


    @Override
    public String toString() {
        return "Record{" +

                ", score=" + score +
                ", user='" + user + '\'' +
                '}';
    }
}