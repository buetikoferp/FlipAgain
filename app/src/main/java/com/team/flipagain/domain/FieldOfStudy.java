package com.team.flipagain.domain;

import java.io.Serializable;

/**
 * Created by delay on 30.03.2016.
 */
public class FieldOfStudy implements Serializable{
    //ATTRIBUTE
    private int studyID;
    private String name;


    // Constructor
    public FieldOfStudy(int studyID, String name){
        this.studyID = studyID;
        this.name = name;
    }


    public int getStudyID() {
        return studyID;
    }

    public void setStudyID(int studyID) {
        this.studyID = studyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
