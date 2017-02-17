package com.example.lavanya.celebrityquiz;

import java.net.URL;

/**
 * Created by lavanya on 1/28/17.
 */

public class QuizObject {
    int celebid;
    String celebname;
    // String celeburl;
    byte imageInbyte[];
    //public QuizObject(int celebid, String celebname, String celeburl){
    public QuizObject(int celebid, String celebname, byte imageInbyte[]){
        this.celebid=celebid;
        this.celebname=celebname;
        //this.celeburl=celeburl;
        this.imageInbyte= imageInbyte;

    }

    public int getCelebid() {
        return celebid;
    }

    public String getCelebname() {
        return celebname;
    }

    /*public String getCeleburl() {
        return celeburl;
    }*/
    public byte[] getImageInbyte() {
        return imageInbyte;
    }

}
