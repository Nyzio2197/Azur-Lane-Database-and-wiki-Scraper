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
    private String shipDescription;
    private String selfIntroduction;
    private String acquisition;
    private String login;
    private String details;
    private String secretaryIdle1;
    private String secretaryIdle2;
    private String secretaryIdle3;
    private String secretaryIdle4;
    private String secretaryIdle5;
    private String secretaryIdle6;
    private String secretaryTouch;
    private String secretarySpecialTouch;
    private String secretaryHeadpat;
    private String mail;

    // voice audio urls: CN, JP, EN order
    private String selfIntroductionUrl;
    private String acquisitionUrl;
    private String loginUrl;
    private String detailsUrl;
    private String secretaryIdle1Url;
    private String secretaryIdle2Url;
    private String secretaryIdle3Url;
    private String secretaryIdle4Url;
    private String secretaryIdle5Url;
    private String secretaryIdle6Url;
    private String secretaryTouchUrl;
    private String secretarySpecialTouchUrl;
    private String secretaryHeadpatUrl;
    private String mailUrl;

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
    }

    public String getChibiUrl() {
        return chibiUrl;
    }

    public void setChibiUrl(String chibiUrl) {
        this.chibiUrl = chibiUrl;
    }

    public String getShipDescription() {
        return shipDescription;
    }

    public void setShipDescription(String shipDescription) {
        this.shipDescription = shipDescription;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public String getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(String acquisition) {
        this.acquisition = acquisition;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSecretaryIdle1() {
        return secretaryIdle1;
    }

    public void setSecretaryIdle1(String secretaryIdle1) {
        this.secretaryIdle1 = secretaryIdle1;
    }

    public String getSecretaryIdle2() {
        return secretaryIdle2;
    }

    public void setSecretaryIdle2(String secretaryIdle2) {
        this.secretaryIdle2 = secretaryIdle2;
    }

    public String getSecretaryIdle3() {
        return secretaryIdle3;
    }

    public void setSecretaryIdle3(String secretaryIdle3) {
        this.secretaryIdle3 = secretaryIdle3;
    }

    public String getSecretaryIdle4() {
        return secretaryIdle4;
    }

    public void setSecretaryIdle4(String secretaryIdle4) {
        this.secretaryIdle4 = secretaryIdle4;
    }

    public String getSecretaryIdle5() {
        return secretaryIdle5;
    }

    public void setSecretaryIdle5(String secretaryIdle5) {
        this.secretaryIdle5 = secretaryIdle5;
    }

    public String getSecretaryIdle6() {
        return secretaryIdle6;
    }

    public void setSecretaryIdle6(String secretaryIdle6) {
        this.secretaryIdle6 = secretaryIdle6;
    }

    public String getSecretaryTouch() {
        return secretaryTouch;
    }

    public void setSecretaryTouch(String secretaryTouch) {
        this.secretaryTouch = secretaryTouch;
    }

    public String getSecretarySpecialTouch() {
        return secretarySpecialTouch;
    }

    public void setSecretarySpecialTouch(String secretarySpecialTouch) {
        this.secretarySpecialTouch = secretarySpecialTouch;
    }

    public String getSecretaryHeadpat() {
        return secretaryHeadpat;
    }

    public void setSecretaryHeadpat(String secretaryHeadpat) {
        this.secretaryHeadpat = secretaryHeadpat;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSelfIntroductionUrl() {
        return selfIntroductionUrl;
    }

    public void setSelfIntroductionUrl(String selfIntroductionUrl) {
        this.selfIntroductionUrl = selfIntroductionUrl;
    }

    public String getAcquisitionUrl() {
        return acquisitionUrl;
    }

    public void setAcquisitionUrl(String acquisitionUrl) {
        this.acquisitionUrl = acquisitionUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public String getSecretaryIdle1Url() {
        return secretaryIdle1Url;
    }

    public void setSecretaryIdle1Url(String secretaryIdle1Url) {
        this.secretaryIdle1Url = secretaryIdle1Url;
    }

    public String getSecretaryIdle2Url() {
        return secretaryIdle2Url;
    }

    public void setSecretaryIdle2Url(String secretaryIdle2Url) {
        this.secretaryIdle2Url = secretaryIdle2Url;
    }

    public String getSecretaryIdle3Url() {
        return secretaryIdle3Url;
    }

    public void setSecretaryIdle3Url(String secretaryIdle3Url) {
        this.secretaryIdle3Url = secretaryIdle3Url;
    }

    public String getSecretaryIdle4Url() {
        return secretaryIdle4Url;
    }

    public void setSecretaryIdle4Url(String secretaryIdle4Url) {
        this.secretaryIdle4Url = secretaryIdle4Url;
    }

    public String getSecretaryIdle5Url() {
        return secretaryIdle5Url;
    }

    public void setSecretaryIdle5Url(String secretaryIdle5Url) {
        this.secretaryIdle5Url = secretaryIdle5Url;
    }

    public String getSecretaryIdle6Url() {
        return secretaryIdle6Url;
    }

    public void setSecretaryIdle6Url(String secretaryIdle6Url) {
        this.secretaryIdle6Url = secretaryIdle6Url;
    }

    public String getSecretaryTouchUrl() {
        return secretaryTouchUrl;
    }

    public void setSecretaryTouchUrl(String secretaryTouchUrl) {
        this.secretaryTouchUrl = secretaryTouchUrl;
    }

    public String getSecretarySpecialTouchUrl() {
        return secretarySpecialTouchUrl;
    }

    public void setSecretarySpecialTouchUrl(String secretarySpecialTouchUrl) {
        this.secretarySpecialTouchUrl = secretarySpecialTouchUrl;
    }

    public String getSecretaryHeadpatUrl() {
        return secretaryHeadpatUrl;
    }

    public void setSecretaryHeadpatUrl(String secretaryHeadpatUrl) {
        this.secretaryHeadpatUrl = secretaryHeadpatUrl;
    }

    public String getMailUrl() {
        return mailUrl;
    }

    public void setMailUrl(String mailUrl) {
        this.mailUrl = mailUrl;
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
