package com.team.flipagain.client.domain;

import java.util.ArrayList;

/**
 * Created by delay on 30.03.2016.
 */
public class FieldOfStudy {
    //ATTRIBUTE
    private int studyID;
    private String name;
    private ArrayList<MRY_Module> moduleList;
    private ArrayList<FieldOfStudy> fieldOfStudyList;

    // Constructor
    public FieldOfStudy(int studyID, String name){
        this.studyID = studyID;
        this.name = name;
        fieldOfStudyList.add(this);
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

    public ArrayList<MRY_Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(ArrayList<MRY_Module> moduleList) {
        this.moduleList = moduleList;
    }
    public ArrayList<FieldOfStudy> getFieldOfStudyList() {
        return fieldOfStudyList;
    }

    public void setFieldOfStudyList(ArrayList<FieldOfStudy> fieldOfStudyList) {
        this.fieldOfStudyList = fieldOfStudyList;
    }
}
