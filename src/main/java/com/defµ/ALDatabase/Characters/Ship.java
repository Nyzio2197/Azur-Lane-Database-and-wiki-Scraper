package com.defµ.ALDatabase.Characters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Alan Xiao (axcdevelopment@gmail.com)
 */

public class Ship {

    public Ship(String name) {
        this.name = name;

        statsHashMap = new HashMap<>();
        skins = new ArrayList<>();
        retrofitNodes = new ArrayList<>();
    }

    // basic data
    private String name;
    private String constructionTime;
    private boolean[] constructionPools;
    private boolean[][] dropLocations; // 4 by 13 grid, true for locations that drop
    private String additionalNotes;
    private String rarity;
    private String shipClass;
    private String ID;
    private String faction;
    private String classification;

    private boolean retrofit;

    // artist and VA info
    private String artist;
    private String pixiv;
    private String twitter;
    private String link;
    private String voiceActor;

    // stats: 100 aff, max enhancements unless otherwise noted
    private String armor;

    private HashMap<String, int[]> statsHashMap;

    // Gear LoadOut
    // slot 1
    private String slot1Equipment;
    private String slot1Efficiency;
    // slot 2
    private String slot2Equipment;
    private String slot2Efficiency;
    // slot 3
    private String slot3Equipment;
    private String slot3Efficiency;

    // fleet tech
    private String collectionStatBonus;
    private int collectionTechPoints;
    private String maxLimitBreakStatBonus;
    private int maxLimitBreakTechPoints;
    private String level120StatBonus;
    private int level120TechPoints;

    // enhancement and retire;
    private int[] enhancementValue;
    private int[] retireValue;

    // limit breaks
    private String limitBreak1;
    private String limitBreak2;
    private String limitBreak3;

    // skills
    private String skill1Name;
    private String skill1Description;
    private String skill2Name;
    private String skill2Description;
    private String skill3Name;
    private String skill3Description;
    private String skill4Name;
    private String skill4Description;

    // PR Strengthening
    private String level5;
    private String level10;
    private String level15;
    private String level20;
    private String level25;
    private String level30;

    private List<Skin> skins;

    public static class RetrofitNode {
        private String index;
        private String project;
        private String attributes;
        private String materials;
        private int coinCost;
        private int levelRequired;
        private int limitBreakRequired;
        private int recurrence;
        private String[] requiredIndexes;

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getAttributes() {
            return attributes;
        }

        public void setAttributes(String attributes) {
            this.attributes = attributes;
        }

        public String getMaterials() {
            return materials;
        }

        public void setMaterials(String materials) {
            this.materials = materials;
        }

        public int getCoinCost() {
            return coinCost;
        }

        public void setCoinCost(int coinCost) {
            this.coinCost = coinCost;
        }

        public int getLevelRequired() {
            return levelRequired;
        }

        public void setLevelRequired(int levelRequired) {
            this.levelRequired = levelRequired;
        }

        public int getLimitBreakRequired() {
            return limitBreakRequired;
        }

        public void setLimitBreakRequired(int limitBreakRequired) {
            this.limitBreakRequired = limitBreakRequired;
        }

        public int getRecurrence() {
            return recurrence;
        }

        public void setRecurrence(int recurrence) {
            this.recurrence = recurrence;
        }

        public String[] getRequiredIndexes() {
            return requiredIndexes;
        }

        public void setRequiredIndexes(String[] requiredIndexes) {
            this.requiredIndexes = requiredIndexes;
        }
    }
    private List<RetrofitNode> retrofitNodes;

    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        return gson.toJson(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstructionTime() {
        return constructionTime;
    }

    public void setConstructionTime(String constructionTime) {
        this.constructionTime = constructionTime;
    }

    public boolean[] getConstructionPools() {
        return constructionPools;
    }

    public void setConstructionPools(boolean[] constructionPools) {
        this.constructionPools = constructionPools;
    }

    public boolean[][] getDropLocations() {
        return dropLocations;
    }

    public void setDropLocations(boolean[][] dropLocations) {
        this.dropLocations = dropLocations;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getShipClass() {
        return shipClass;
    }

    public void setShipClass(String shipClass) {
        this.shipClass = shipClass;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public boolean isRetrofit() {
        return retrofit;
    }

    public void setRetrofit(boolean retrofit) {
        this.retrofit = retrofit;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPixiv() {
        return pixiv;
    }

    public void setPixiv(String pixiv) {
        this.pixiv = pixiv;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVoiceActor() {
        return voiceActor;
    }

    public void setVoiceActor(String voiceActor) {
        this.voiceActor = voiceActor;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public HashMap<String, int[]> getStatsHashMap() {
        return statsHashMap;
    }

    public void setStatsHashMap(HashMap<String, int[]> statsHashMap) {
        this.statsHashMap = statsHashMap;
    }

    public String getSlot1Equipment() {
        return slot1Equipment;
    }

    public void setSlot1Equipment(String slot1Equipment) {
        this.slot1Equipment = slot1Equipment;
    }

    public String getSlot1Efficiency() {
        return slot1Efficiency;
    }

    public void setSlot1Efficiency(String slot1Efficiency) {
        this.slot1Efficiency = slot1Efficiency;
    }

    public String getSlot2Equipment() {
        return slot2Equipment;
    }

    public void setSlot2Equipment(String slot2Equipment) {
        this.slot2Equipment = slot2Equipment;
    }

    public String getSlot2Efficiency() {
        return slot2Efficiency;
    }

    public void setSlot2Efficiency(String slot2Efficiency) {
        this.slot2Efficiency = slot2Efficiency;
    }

    public String getSlot3Equipment() {
        return slot3Equipment;
    }

    public void setSlot3Equipment(String slot3Equipment) {
        this.slot3Equipment = slot3Equipment;
    }

    public String getSlot3Efficiency() {
        return slot3Efficiency;
    }

    public void setSlot3Efficiency(String slot3Efficiency) {
        this.slot3Efficiency = slot3Efficiency;
    }

    public String getCollectionStatBonus() {
        return collectionStatBonus;
    }

    public void setCollectionStatBonus(String collectionStatBonus) {
        this.collectionStatBonus = collectionStatBonus;
    }

    public int getCollectionTechPoints() {
        return collectionTechPoints;
    }

    public void setCollectionTechPoints(int collectionTechPoints) {
        this.collectionTechPoints = collectionTechPoints;
    }

    public String getMaxLimitBreakStatBonus() {
        return maxLimitBreakStatBonus;
    }

    public void setMaxLimitBreakStatBonus(String maxLimitBreakStatBonus) {
        this.maxLimitBreakStatBonus = maxLimitBreakStatBonus;
    }

    public int getMaxLimitBreakTechPoints() {
        return maxLimitBreakTechPoints;
    }

    public void setMaxLimitBreakTechPoints(int maxLimitBreakTechPoints) {
        this.maxLimitBreakTechPoints = maxLimitBreakTechPoints;
    }

    public String getLevel120StatBonus() {
        return level120StatBonus;
    }

    public void setLevel120StatBonus(String level120StatBonus) {
        this.level120StatBonus = level120StatBonus;
    }

    public int getLevel120TechPoints() {
        return level120TechPoints;
    }

    public void setLevel120TechPoints(int level120TechPoints) {
        this.level120TechPoints = level120TechPoints;
    }

    public int[] getEnhancementValue() {
        return enhancementValue;
    }

    public void setEnhancementValue(int[] enhancementValue) {
        this.enhancementValue = enhancementValue;
    }

    public int[] getRetireValue() {
        return retireValue;
    }

    public void setRetireValue(int[] retireValue) {
        this.retireValue = retireValue;
    }

    public String getLimitBreak1() {
        return limitBreak1;
    }

    public void setLimitBreak1(String limitBreak1) {
        this.limitBreak1 = limitBreak1;
    }

    public String getLimitBreak2() {
        return limitBreak2;
    }

    public void setLimitBreak2(String limitBreak2) {
        this.limitBreak2 = limitBreak2;
    }

    public String getLimitBreak3() {
        return limitBreak3;
    }

    public void setLimitBreak3(String limitBreak3) {
        this.limitBreak3 = limitBreak3;
    }

    public String getSkill1Name() {
        return skill1Name;
    }

    public void setSkill1Name(String skill1Name) {
        this.skill1Name = skill1Name;
    }

    public String getSkill1Description() {
        return skill1Description;
    }

    public void setSkill1Description(String skill1Description) {
        this.skill1Description = skill1Description;
    }

    public String getSkill2Name() {
        return skill2Name;
    }

    public void setSkill2Name(String skill2Name) {
        this.skill2Name = skill2Name;
    }

    public String getSkill2Description() {
        return skill2Description;
    }

    public void setSkill2Description(String skill2Description) {
        this.skill2Description = skill2Description;
    }

    public String getSkill3Name() {
        return skill3Name;
    }

    public void setSkill3Name(String skill3Name) {
        this.skill3Name = skill3Name;
    }

    public String getSkill3Description() {
        return skill3Description;
    }

    public void setSkill3Description(String skill3Description) {
        this.skill3Description = skill3Description;
    }

    public String getSkill4Name() {
        return skill4Name;
    }

    public void setSkill4Name(String skill4Name) {
        this.skill4Name = skill4Name;
    }

    public String getSkill4Description() {
        return skill4Description;
    }

    public void setSkill4Description(String skill4Description) {
        this.skill4Description = skill4Description;
    }

    public String getLevel5() {
        return level5;
    }

    public void setLevel5(String level5) {
        this.level5 = level5;
    }

    public String getLevel10() {
        return level10;
    }

    public void setLevel10(String level10) {
        this.level10 = level10;
    }

    public String getLevel15() {
        return level15;
    }

    public void setLevel15(String level15) {
        this.level15 = level15;
    }

    public String getLevel20() {
        return level20;
    }

    public void setLevel20(String level20) {
        this.level20 = level20;
    }

    public String getLevel25() {
        return level25;
    }

    public void setLevel25(String level25) {
        this.level25 = level25;
    }

    public String getLevel30() {
        return level30;
    }

    public void setLevel30(String level30) {
        this.level30 = level30;
    }

    public List<Skin> getSkins() {
        return skins;
    }

    public void setSkins(List<Skin> skins) {
        this.skins = skins;
    }

    public List<RetrofitNode> getRetrofitNodes() {
        return retrofitNodes;
    }

    public void setRetrofitNodes(List<RetrofitNode> retrofitNodes) {
        this.retrofitNodes = retrofitNodes;
    }

}
