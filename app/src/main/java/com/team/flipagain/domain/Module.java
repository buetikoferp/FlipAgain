package com.team.flipagain.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class Module implements Serializable{
    /**
     *
     */

    //ATTRIBUT
    private int moduleId;
    private String moduleName;
    private ArrayList<Bundle> bundlesOfModuleList;

    public Module(int moduleId, String moduleName){
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.bundlesOfModuleList = new ArrayList<Bundle>();
    }

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

    public ArrayList<Bundle> getBundlesOfModuleList() {
        return bundlesOfModuleList;
    }

    public void setBundlesOfModuleList(ArrayList<Bundle> bundlesOfModuleList) {
        this.bundlesOfModuleList = bundlesOfModuleList;
    }
}
