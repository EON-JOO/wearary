package com.professsionalandroid.apps.wearary;

import java.io.Serializable;

public class activity3_memo implements Serializable {
    String maintext; //메모
    String subtext; //날짜



    public activity3_memo(String maintext, String subtext, int isDone) {
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





