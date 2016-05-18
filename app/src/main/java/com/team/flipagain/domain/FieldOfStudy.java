package com.team.flipagain.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by delay on 30.03.2016.
 */
public class FieldOfStudy implements Serializable{
    //ATTRIBUTE
    private int studyID;
    private String name;
    private ArrayList<Module> moduleList;


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


    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }


}
