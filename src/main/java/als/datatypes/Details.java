package als.datatypes;

public class Details {

    private String name;
    private String rarity;
    private String ID;
    private String faction;
    private String classification;

    private boolean retrofit;

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

}
