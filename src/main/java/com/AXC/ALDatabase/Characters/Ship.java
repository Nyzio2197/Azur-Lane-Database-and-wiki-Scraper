package com.AXC.ALDatabase.Characters;

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
        skins = new ArrayList<>();
    }

    // basic data
    private String name;
    private String rarity;
    private String ID;
    private String faction;
    private String classification;

    private boolean retrofit;

    private List<Skin> skins;

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

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
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

    public List<Skin> getSkins() {
        return skins;
    }

    public void setSkins(List<Skin> skins) {
        this.skins = skins;
    }

}
