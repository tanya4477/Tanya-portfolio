package com.example.sam.numbersapp;

import java.io.Serializable;

/**
 * This is the POJO class for Main Activity
 * Created by tanya on 03.06.16.
 */
public class MasterData implements Serializable{
    public String image;
    public String name;

    MasterData(String image,  String name) {
        this.image = image;
        this.name = name;
    }
}
