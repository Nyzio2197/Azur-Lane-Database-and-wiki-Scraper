package com.AXC.ALDatabase.Characters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/**
 * @author Alan Xiao (axcdevelopment@gmail.com)
 */

public class Skin {

    // basic info
    private String ENClientName;
    private String CNClientName;
    private String JPClientName;

    // art info
    private String chibiUrl;

    // voice transcriptions: CN, JP, EN order
    private String[] shipDescription;
    private String[] selfIntroduction;
    private String[] acquisition;
    private String[] login;
    private String[] details;
    private String[] secretaryIdle1;
    private String[] secretaryIdle2;
    private String[] secretaryIdle3;
    private String[] secretaryIdle4;
    private String[] secretaryIdle5;
    private String[] secretaryIdle6;
    private String[] secretaryTouch;
    private String[] secretarySpecialTouch;
    private String[] secretaryHeadpat;
    private String[] mail;

    // voice audio urls: CN, JP, EN order
    private String[] selfIntroductionUrl;
    private String[] acquisitionUrl;
    private String[] loginUrl;
    private String[] detailsUrl;
    private String[] secretaryIdle1Url;
    private String[] secretaryIdle2Url;
    private String[] secretaryIdle3Url;
    private String[] secretaryIdle4Url;
    private String[] secretaryIdle5Url;
    private String[] secretaryIdle6Url;
    private String[] secretaryTouchUrl;
    private String[] secretarySpecialTouchUrl;
    private String[] secretaryHeadpatUrl;
    private String[] mailUrl;

    public String getName() {
        return ENClientName != null ? ENClientName : JPClientName != null ? JPClientName : CNClientName;
    }

    public String getENClientName() {
        return ENClientName;
    }

    public String getCNClientName() {
        return CNClientName;
    }

    public String getJPClientName() {
        return JPClientName;
    }

    public void setENClientName(String ENClientName) {
        this.ENClientName = ENClientName;
    }

    public void setCNClientName(String CNClientName) {
        this.CNClientName = CNClientName;
    }

    public void setJPClientName(String JPClientName) {
        this.JPClientName = JPClientName;
    }public String getChibiUrl() {
        return chibiUrl;
    }

    public void setChibiUrl(String chibiUrl) {
        this.chibiUrl = chibiUrl;
    }

    public String[] getShipDescription() {
        if (shipDescription == null)
            shipDescription = new String[3];
        return shipDescription;
    }

    public String[] getSelfIntroduction() {
        if (selfIntroduction == null)
            selfIntroduction = new String[3];;
        return selfIntroduction;
    }

    public String[] getAcquisition() {
        if (acquisition == null)
            acquisition = new String[3];
        return acquisition;
    }

    public String[] getLogin() {
        if (login == null)
            login = new String[3];
        return login;
    }

    public String[] getDetails() {
        if (details == null)
            details = new String[3];
        return details;
    }

    public String[] getSecretaryIdle1() {
        if (secretaryIdle1 == null)
            secretaryIdle1 = new String[3];
        return secretaryIdle1;
    }

    public String[] getSecretaryIdle2() {
        if (secretaryIdle2 == null)
            secretaryIdle2 = new String[3];
        return secretaryIdle2;
    }

    public String[] getSecretaryIdle3() {
        if (secretaryIdle3 == null)
            secretaryIdle3 = new String[3];
        return secretaryIdle3;
    }

    public String[] getSecretaryIdle4() {
        if (secretaryIdle4 == null)
            secretaryIdle4 = new String[3];
        return secretaryIdle4;
    }

    public String[] getSecretaryIdle5() {
        if (secretaryIdle5 == null)
            secretaryIdle5 = new String[3];
        return secretaryIdle5;
    }

    public String[] getSecretaryIdle6() {
        if (secretaryIdle6 == null)
            secretaryIdle6 = new String[3];
        return secretaryIdle6;
    }

    public String[] getSecretaryTouch() {
        if (secretaryTouch == null)
            secretaryTouch = new String[3];
        return secretaryTouch;
    }

    public String[] getSecretarySpecialTouch() {
        if (secretarySpecialTouch == null)
            secretarySpecialTouch = new String[3];
        return secretarySpecialTouch;
    }

    public String[] getSecretaryHeadpat() {
        if (secretaryHeadpat == null)
            secretaryHeadpat = new String[3];
        return secretaryHeadpat;
    }

    public String[] getMail() {
        if (mail == null)
            mail = new String[3];
        return mail;
    }

    public String[] getSelfIntroductionUrl() {
        if (selfIntroductionUrl == null)
            selfIntroductionUrl = new String[3];
        return selfIntroductionUrl;
    }

    public String[] getAcquisitionUrl() {
        if (acquisitionUrl == null)
            acquisitionUrl = new String[3];
        return acquisitionUrl;
    }

    public String[] getLoginUrl() {
        if (loginUrl == null)
            loginUrl = new String[3];
        return loginUrl;
    }

    public String[] getDetailsUrl() {
        if (detailsUrl == null)
            detailsUrl = new String[3];
        return detailsUrl;
    }

    public String[] getSecretaryIdle1Url() {
        if (secretaryIdle1Url == null)
            secretaryIdle1Url = new String[3];
        return secretaryIdle1Url;
    }

    public String[] getSecretaryIdle2Url() {
        if (secretaryIdle2Url == null)
            secretaryIdle2Url = new String[3];
        return secretaryIdle2Url;
    }

    public String[] getSecretaryIdle3Url() {
        if (secretaryIdle3Url == null)
            secretaryIdle3Url = new String[3];
        return secretaryIdle3Url;
    }

    public String[] getSecretaryIdle4Url() {
        if (secretaryIdle4Url == null)
            secretaryIdle4Url = new String[3];
        return secretaryIdle4Url;
    }

    public String[] getSecretaryIdle5Url() {
        if (secretaryIdle5Url == null)
            secretaryIdle5Url = new String[3];
        return secretaryIdle5Url;
    }

    public String[] getSecretaryIdle6Url() {
        if (secretaryIdle6Url == null)
            secretaryIdle6Url = new String[3];
        return secretaryIdle6Url;
    }

    public String[] getSecretaryTouchUrl() {
        if (secretaryTouchUrl == null)
            secretaryTouchUrl = new String[3];
        return secretaryTouchUrl;
    }

    public String[] getSecretarySpecialTouchUrl() {
        if (secretarySpecialTouchUrl == null)
            secretarySpecialTouchUrl = new String[3];
        return secretarySpecialTouchUrl;
    }

    public String[] getSecretaryHeadpatUrl() {
        if (secretaryHeadpatUrl == null)
            secretaryHeadpatUrl = new String[3];
        return secretaryHeadpatUrl;
    }

    public String[] getMailUrl() {
        if (mailUrl == null)
            mailUrl = new String[3];
        return mailUrl;
    }

    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        return gson.toJson(this);
    }
}
