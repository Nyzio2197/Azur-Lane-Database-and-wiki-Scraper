package com.defµ.ALDatabase.Characters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/**
 * @author Alan Xiao (axcdevelopment@gmail.com)
 */

public class Skin {

    // basic info
    private String ENClientName;
    private String ENClientAvailability;
    private String CNClientName;
    private String CNClientAvailability;
    private String JPClientName;
    private String JPClientAvailability;
    private String obtainedFrom;
    private String cost;
    private boolean l2dModel;

    // art info
    private String spriteUrl;
    private String spriteNoBackgroundUrl;
    private String spriteCNUrl;
    private String spriteCensoredUrl;
    private String spriteOldVersionUrl;
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
    private String[] task;
    private String[] taskComplete;
    private String[] mail;
    private String[] returnFromMission;
    private String[] commission;
    private String[] strengthening;
    private String[] startingMission;
    private String[] mvp;
    private String[] defeat;
    private String[] skillActivation;
    private String[] lowHP;
    private String[] affinityDisappointed;
    private String[] affinityStranger;
    private String[] affinityFriendly;
    private String[] affinityLike;
    private String[] affinityLove;
    private String[] pledge;
    private String[] additionalVoiceLine1;
    private String[] additionalVoiceLine2;
    private String[] additionalVoiceLine3;
    private String[] additionalVoiceLine4;
    private String[] additionalVoiceLine5;
    private String[] additionalVoiceLine6;
    private String[] additionalVoiceLine7;
    private String[] additionalVoiceLine8;
    private String[] additionalVoiceLine9;
    private String[] additionalVoiceLine10;
    private String[] additionalVoiceLine11;
    private String[] giftDescriptionValentines2018;
    private String[] giftDescriptionValentines2019;
    private String[] giftDescriptionValentines2020;
    private String[] giftDescriptionValentines2021;

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
    private String[] taskUrl;
    private String[] taskCompleteUrl;
    private String[] mailUrl;
    private String[] returnFromMissionUrl;
    private String[] commissionUrl;
    private String[] strengtheningUrl;
    private String[] startingMissionUrl;
    private String[] mvpUrl;
    private String[] defeatUrl;
    private String[] skillActivationUrl;
    private String[] lowHPUrl;
    private String[] affinityDisappointedUrl;
    private String[] affinityStrangerUrl;
    private String[] affinityFriendlyUrl;
    private String[] affinityLikeUrl;
    private String[] affinityLoveUrl;
    private String[] pledgeUrl;
    private String[] additionalVoiceLine1Url;
    private String[] additionalVoiceLine2Url;
    private String[] additionalVoiceLine3Url;
    private String[] additionalVoiceLine4Url;
    private String[] additionalVoiceLine5Url;
    private String[] additionalVoiceLine6Url;
    private String[] additionalVoiceLine7Url;
    private String[] additionalVoiceLine8Url;
    private String[] additionalVoiceLine9Url;
    private String[] additionalVoiceLine10Url;
    private String[] additionalVoiceLine11Url;

    public String getENClientName() {
        return ENClientName;
    }

    public void setENClientName(String ENClientName) {
        this.ENClientName = ENClientName;
    }

    public String getENClientAvailability() {
        return ENClientAvailability;
    }

    public void setENClientAvailability(String ENClientAvailability) {
        this.ENClientAvailability = ENClientAvailability;
    }

    public String getCNClientName() {
        return CNClientName;
    }

    public void setCNClientName(String CNClientName) {
        this.CNClientName = CNClientName;
    }

    public String getCNClientAvailability() {
        return CNClientAvailability;
    }

    public void setCNClientAvailability(String CNClientAvailability) {
        this.CNClientAvailability = CNClientAvailability;
    }

    public String getJPClientName() {
        return JPClientName;
    }

    public void setJPClientName(String JPClientName) {
        this.JPClientName = JPClientName;
    }

    public String getJPClientAvailability() {
        return JPClientAvailability;
    }

    public void setJPClientAvailability(String JPClientAvailability) {
        this.JPClientAvailability = JPClientAvailability;
    }

    public String getObtainedFrom() {
        return obtainedFrom;
    }

    public void setObtainedFrom(String obtainedFrom) {
        this.obtainedFrom = obtainedFrom;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isL2dModel() {
        return l2dModel;
    }

    public void setL2dModel(boolean l2dModel) {
        this.l2dModel = l2dModel;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public String getSpriteNoBackgroundUrl() {
        return spriteNoBackgroundUrl;
    }

    public void setSpriteNoBackgroundUrl(String spriteNoBackgroundUrl) {
        this.spriteNoBackgroundUrl = spriteNoBackgroundUrl;
    }

    public String getSpriteCNUrl() {
        return spriteCNUrl;
    }

    public void setSpriteCNUrl(String spriteCNUrl) {
        this.spriteCNUrl = spriteCNUrl;
    }

    public String getSpriteCensoredUrl() {
        return spriteCensoredUrl;
    }

    public void setSpriteCensoredUrl(String spriteCensoredUrl) {
        this.spriteCensoredUrl = spriteCensoredUrl;
    }

    public String getSpriteOldVersionUrl() {
        return spriteOldVersionUrl;
    }

    public void setSpriteOldVersionUrl(String spriteOldVersionUrl) {
        this.spriteOldVersionUrl = spriteOldVersionUrl;
    }

    public String getChibiUrl() {
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

    public String[] getTask() {
        if (task == null)
            task = new String[3];
        return task;
    }

    public String[] getTaskComplete() {
        if (taskComplete == null)
            taskComplete = new String[3];
        return taskComplete;
    }

    public String[] getMail() {
        if (mail == null)
            mail = new String[3];
        return mail;
    }

    public String[] getReturnFromMission() {
        if (returnFromMission == null)
            returnFromMission = new String[3];
        return returnFromMission;
    }

    public String[] getCommission() {
        if (commission == null)
            commission = new String[3];
        return commission;
    }

    public String[] getStrengthening() {
        if (strengthening == null)
            strengthening = new String[3];
        return strengthening;
    }

    public String[] getStartingMission() {
        if (startingMission == null)
            startingMission = new String[3];
        return startingMission;
    }

    public String[] getMvp() {
        if (mvp == null)
            mvp = new String[3];
        return mvp;
    }

    public String[] getDefeat() {
        if (defeat == null)
            defeat = new String[3];
        return defeat;
    }

    public String[] getSkillActivation() {
        if (skillActivation == null)
            skillActivation = new String[3];
        return skillActivation;
    }

    public String[] getLowHP() {
        if (lowHP == null)
            lowHP = new String[3];
        return lowHP;
    }

    public String[] getAffinityDisappointed() {
        if (affinityDisappointed == null)
            affinityDisappointed = new String[3];
        return affinityDisappointed;
    }

    public String[] getAffinityStranger() {
        if (affinityStranger == null)
            affinityStranger = new String[3];
        return affinityStranger;
    }

    public String[] getAffinityFriendly() {
        if (affinityFriendly == null)
            affinityFriendly = new String[3];
        return affinityFriendly;
    }

    public String[] getAffinityLike() {
        if (affinityLike == null)
            affinityLike = new String[3];
        return affinityLike;
    }

    public String[] getAffinityLove() {
        if (affinityLove == null)
            affinityLove = new String[3];
        return affinityLove;
    }

    public String[] getPledge() {
        if (pledge == null)
            pledge = new String[3];
        return pledge;
    }

    public String[] getAdditionalVoiceLine1() {
        if (additionalVoiceLine1 == null)
            additionalVoiceLine1 = new String[3];
        return additionalVoiceLine1;
    }

    public String[] getAdditionalVoiceLine2() {
        if (additionalVoiceLine2 == null)
            additionalVoiceLine2 = new String[3];
        return additionalVoiceLine2;
    }

    public String[] getAdditionalVoiceLine3() {
        if (additionalVoiceLine3 == null)
            additionalVoiceLine3 = new String[3];
        return additionalVoiceLine3;
    }

    public String[] getAdditionalVoiceLine4() {
        if (additionalVoiceLine4 == null)
            additionalVoiceLine4 = new String[3];
        return additionalVoiceLine4;
    }

    public String[] getAdditionalVoiceLine5() {
        if (additionalVoiceLine5 == null)
            additionalVoiceLine5 = new String[3];
        return additionalVoiceLine5;
    }

    public String[] getAdditionalVoiceLine6() {
        if (additionalVoiceLine6 == null)
            additionalVoiceLine6 = new String[3];
        return additionalVoiceLine6;
    }

    public String[] getAdditionalVoiceLine7() {
        if (additionalVoiceLine7 == null)
            additionalVoiceLine7 = new String[3];
        return additionalVoiceLine7;
    }

    public String[] getAdditionalVoiceLine8() {
        if (additionalVoiceLine8 == null)
            additionalVoiceLine8 = new String[3];
        return additionalVoiceLine8;
    }

    public String[] getAdditionalVoiceLine9() {
        if (additionalVoiceLine9 == null)
            additionalVoiceLine9 = new String[3];
        return additionalVoiceLine9;
    }

    public String[] getAdditionalVoiceLine10() {
        if (additionalVoiceLine10 == null)
            additionalVoiceLine10 = new String[3];
        return additionalVoiceLine10;
    }

    public String[] getAdditionalVoiceLine11() {
        if (additionalVoiceLine11 == null)
            additionalVoiceLine11 = new String[3];
        return additionalVoiceLine11;
    }

    public String[] getGiftDescriptionValentines2018() {
        if (giftDescriptionValentines2018 == null)
            giftDescriptionValentines2018 = new String[3];
        return giftDescriptionValentines2018;
    }

    public String[] getGiftDescriptionValentines2019() {
        if (giftDescriptionValentines2019 == null)
            giftDescriptionValentines2019 = new String[3];
        return giftDescriptionValentines2019;
    }

    public String[] getGiftDescriptionValentines2020() {
        if (giftDescriptionValentines2020 == null)
            giftDescriptionValentines2020 = new String[3];
        return giftDescriptionValentines2020;
    }

    public String[] getGiftDescriptionValentines2021() {
        if (giftDescriptionValentines2021 == null)
            giftDescriptionValentines2021 = new String[3];
        return giftDescriptionValentines2021;
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

    public String[] getTaskUrl() {
        if (taskUrl == null)
            taskUrl = new String[3];
        return taskUrl;
    }

    public String[] getTaskCompleteUrl() {
        if (taskCompleteUrl == null)
            taskCompleteUrl = new String[3];
        return taskCompleteUrl;
    }

    public String[] getMailUrl() {
        if (mailUrl == null)
            mailUrl = new String[3];
        return mailUrl;
    }

    public String[] getReturnFromMissionUrl() {
        if (returnFromMissionUrl == null)
            returnFromMissionUrl = new String[3];
        return returnFromMissionUrl;
    }

    public String[] getCommissionUrl() {
        if (commissionUrl == null)
            commissionUrl = new String[3];
        return commissionUrl;
    }

    public String[] getStrengtheningUrl() {
        if (strengtheningUrl == null)
            strengtheningUrl = new String[3];
        return strengtheningUrl;
    }

    public String[] getStartingMissionUrl() {
        if (startingMissionUrl == null)
            startingMissionUrl = new String[3];
        return startingMissionUrl;
    }

    public String[] getMvpUrl() {
        if (mvpUrl == null)
            mvpUrl = new String[3];
        return mvpUrl;
    }

    public String[] getDefeatUrl() {
        if (defeatUrl == null)
            defeatUrl = new String[3];
        return defeatUrl;
    }

    public String[] getSkillActivationUrl() {
        if (skillActivationUrl == null)
            skillActivationUrl = new String[3];
        return skillActivationUrl;
    }

    public String[] getLowHPUrl() {
        if (lowHPUrl == null)
            lowHPUrl = new String[3];
        return lowHPUrl;
    }

    public String[] getAffinityDisappointedUrl() {
        if (affinityDisappointedUrl == null)
            affinityDisappointedUrl = new String[3];
        return affinityDisappointedUrl;
    }

    public String[] getAffinityStrangerUrl() {
        if (affinityStrangerUrl == null)
            affinityStrangerUrl = new String[3];
        return affinityStrangerUrl;
    }

    public String[] getAffinityFriendlyUrl() {
        if (affinityFriendlyUrl == null)
            affinityFriendlyUrl = new String[3];
        return affinityFriendlyUrl;
    }

    public String[] getAffinityLikeUrl() {
        if (affinityLikeUrl == null)
            affinityLikeUrl = new String[3];
        return affinityLikeUrl;
    }

    public String[] getAffinityLoveUrl() {
        if (affinityLoveUrl == null)
            affinityLoveUrl = new String[3];
        return affinityLoveUrl;
    }

    public String[] getPledgeUrl() {
        if (pledgeUrl == null)
            pledgeUrl = new String[3];
        return pledgeUrl;
    }

    public String[] getAdditionalVoiceLine1Url() {
        if (additionalVoiceLine1Url == null)
            additionalVoiceLine1Url = new String[3];
        return additionalVoiceLine1Url;
    }

    public String[] getAdditionalVoiceLine2Url() {
        if (additionalVoiceLine2Url == null)
            additionalVoiceLine2Url = new String[3];
        return additionalVoiceLine2Url;
    }

    public String[] getAdditionalVoiceLine3Url() {
        if (additionalVoiceLine3Url == null)
            additionalVoiceLine3Url = new String[3];
        return additionalVoiceLine3Url;
    }

    public String[] getAdditionalVoiceLine4Url() {
        if (additionalVoiceLine4Url == null)
            additionalVoiceLine4Url = new String[3];
        return additionalVoiceLine4Url;
    }

    public String[] getAdditionalVoiceLine5Url() {
        if (additionalVoiceLine5Url == null)
            additionalVoiceLine5Url = new String[3];
        return additionalVoiceLine5Url;
    }

    public String[] getAdditionalVoiceLine6Url() {
        if (additionalVoiceLine6Url == null)
            additionalVoiceLine6Url = new String[3];
        return additionalVoiceLine6Url;
    }

    public String[] getAdditionalVoiceLine7Url() {
        if (additionalVoiceLine7Url == null)
            additionalVoiceLine7Url = new String[3];
        return additionalVoiceLine7Url;
    }

    public String[] getAdditionalVoiceLine8Url() {
        if (additionalVoiceLine8Url == null)
            additionalVoiceLine8Url = new String[3];
        return additionalVoiceLine8Url;
    }

    public String[] getAdditionalVoiceLine9Url() {
        if (additionalVoiceLine9Url == null)
            additionalVoiceLine9Url = new String[3];
        return additionalVoiceLine9Url;
    }

    public String[] getAdditionalVoiceLine10Url() {
        if (additionalVoiceLine10Url == null)
            additionalVoiceLine10Url = new String[3];
        return additionalVoiceLine10Url;
    }

    public String[] getAdditionalVoiceLine11Url() {
        if (additionalVoiceLine11Url == null)
            additionalVoiceLine11Url = new String[3];
        return additionalVoiceLine11Url;
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
