package com.professsionalandroid.apps.wearary;

import java.io.Serializable;

public class Activity3_memo implements Serializable {
    String maintext; //메모
    String subtext; //날짜



    public Activity3_memo(String maintext, String subtext) {
        this.maintext = maintext;
        this.subtext = subtext;

    }



    public String getMaintext() {
        return maintext;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }


}





