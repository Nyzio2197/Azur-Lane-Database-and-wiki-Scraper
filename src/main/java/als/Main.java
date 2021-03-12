package als;

import als.datatypes.Data;
import als.datatypes.Details;
import com.AXC.ALDatabase.Characters.Ship;
import com.AXC.ALDatabase.Characters.Skin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        File dir = new File("/home/ubuntu/programs/database");
        String path = "/home/ubuntu/data";
        assert dir.isDirectory();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        for (File file : dir.listFiles()) {
            try {
                Ship s = gson.fromJson(new JsonReader(new FileReader(file)), Ship.class);
                Details details = new Details();
                details.setID(s.getID());
                details.setClassification(s.getClassification());
                details.setFaction(s.getFaction());
                details.setRarity(s.getRarity());
                details.setClassification(s.getClassification());
                details.setRetrofit(s.isRetrofit());
                details.setName(s.getName());
                s.setName(s.getName().replace("µ", "(muse)").replaceAll("[^a-zA-Z0-9()]", ""));
                new File(path + s.getName()).mkdir();
                System.out.println(s.getName());
                FileWriter fileWriter = new FileWriter(path + s.getName() + "/details.json", false);
                fileWriter.write(gson.toJson(details));
                fileWriter.close();
                for (Skin skin : s.getSkins()) {
                    Data data = new Data();
                    data.setName(skin.getName());
                    skin.setENClientName(skin.getName().replaceAll("[^a-zA-Z]", ""));
                    System.out.println(skin.getName());
                    System.out.println(new File(path + s.getName() + "/" + skin.getName()).mkdir());
                    data.setShipDescription(skin.getShipDescription());
                    data.setSelfIntroduction(skin.getSelfIntroduction());
                    data.setAcquisition(skin.getAcquisition());
                    data.setLogin(skin.getLogin());
                    data.setDetails(skin.getDetails());
                    data.setSecretaryIdle1(skin.getSecretaryIdle1());
                    data.setSecretaryIdle2(skin.getSecretaryIdle2());
                    data.setSecretaryIdle3(skin.getSecretaryIdle3());
                    data.setSecretaryIdle4(skin.getSecretaryIdle4());
                    data.setSecretaryIdle5(skin.getSecretaryIdle5());
                    data.setSecretaryIdle6(skin.getSecretaryIdle6());
                    data.setSecretaryTouch(skin.getSecretaryTouch());
                    data.setSecretarySpecialTouch(skin.getSecretarySpecialTouch());
                    data.setSecretaryHeadpat(skin.getSecretaryHeadpat());
                    data.setMail(skin.getMail());
                    fileWriter = new FileWriter(path + s.getName() + "/" + skin.getName() + "/data.json", false);
                    fileWriter.write(gson.toJson(data));
                    fileWriter.close();
                    download(skin.getChibiUrl(), path + s.getName() + "/" + skin.getName() + "/chibi.png");
                    ArrayList<String> dQueue = new ArrayList<>();
                    if (skin.getSelfIntroductionUrl() != null)
                        dQueue.add(skin.getSelfIntroductionUrl());
                    if (skin.getAcquisitionUrl() != null)
                        dQueue.add(skin.getAcquisitionUrl());
                    if (skin.getLoginUrl() != null)
                        dQueue.add(skin.getLoginUrl());
                    if (skin.getDetailsUrl() != null)
                        dQueue.add(skin.getDetailsUrl());
                    if (skin.getSecretaryIdle1Url() != null)
                        dQueue.add(skin.getSecretaryIdle1Url());
                    if (skin.getSecretaryIdle2Url() != null)
                        dQueue.add(skin.getSecretaryIdle2Url());
                    if (skin.getSecretaryIdle3Url() != null)
                        dQueue.add(skin.getSecretaryIdle3Url());
                    if (skin.getSecretaryIdle4Url() != null)
                        dQueue.add(skin.getSecretaryIdle4Url());
                    if (skin.getSecretaryIdle5Url() != null)
                        dQueue.add(skin.getSecretaryIdle5Url());
                    if (skin.getSecretaryIdle6Url() != null)
                        dQueue.add(skin.getSecretaryIdle6Url());
                    if (skin.getSecretaryTouchUrl() != null)
                        dQueue.add(skin.getSecretaryTouchUrl());
                    if (skin.getSecretarySpecialTouchUrl() != null)
                        dQueue.add(skin.getSecretarySpecialTouchUrl());
                    if (skin.getSecretaryHeadpatUrl() != null)
                        dQueue.add(skin.getSecretaryHeadpatUrl());
                    if (skin.getMailUrl() != null)
                        dQueue.add(skin.getMailUrl());
                    if (!dQueue.isEmpty()) {
                        new File(path + s.getName() + "/" + skin.getName() + "/audio").mkdir();
                        for (String link : dQueue) {
                            download(link, path + s.getName() + "/" + skin.getName() + "/audio/" + link.substring(link.lastIndexOf("/") + 1));
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void download(String link, String path) {
        try {
            URL url = new URL(link);
            InputStream in = new BufferedInputStream(url.openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(path));

            for ( int i; (i = in.read()) != -1; ) {
                out.write(i);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
