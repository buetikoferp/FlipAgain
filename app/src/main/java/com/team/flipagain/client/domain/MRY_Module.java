package com.team.flipagain.client.domain;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class MRY_Module {
    //ATTRIBUT
    private int moduleId;
    private String moduleName;

    //REFERENCE
    private MRY_FieldOfStudy fieldOfStudy;

    //GETTER SETTER
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public MRY_FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(MRY_FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }
}
