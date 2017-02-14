package com.example.lavanya.celebrityquiz;

import java.net.URL;

/**
 * Created by lavanya on 1/28/17.
 */

public class QuizObject {
    int celebid;
    String celebname;
    String celeburl;
    public QuizObject(int celebid, String celebname, String celeburl){
        this.celebid=celebid;
        this.celebname=celebname;
        this.celeburl=celeburl;
    }

    public int getCelebid() {
        return celebid;
    }

    public String getCelebname() {
        return celebname;
    }

    public String getCeleburl() {
        return celeburl;
    }
}
