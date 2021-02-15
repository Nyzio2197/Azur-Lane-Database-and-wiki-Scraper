package com.AXC.ALDatabase.Scraper;

import com.AXC.ALDatabase.Characters.Skin;
import com.AXC.ALDatabase.Characters.Ship;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan Xiao (axcdevelopment@gmail.com)
 */

public class AzurLaneScraper {

    public static final String azurLaneKoumakanJp = "https://azurlane.koumakan.jp";

    /**
     * scrape https://azurlane.koumakan.jp/List_of_Ships for ship list and convert all ship data into .json-s
     */
    public static void scrape() {
        try {
            Document document = Jsoup.connect(azurLaneKoumakanJp + "/List_of_Ships")
                    .userAgent("Mozilla")
                    .get();
            Elements rows = new Elements();
            Elements standardShips = document.select("table").get(0).select("tr");
            standardShips.remove(0);
            Elements PRShips = document.select("table").get(1).select("tr");
            PRShips.remove(0);
            Elements collabShips = document.select("table").get(2).select("tr");
            collabShips.remove(0);
            rows.addAll(standardShips);
            rows.addAll(PRShips);
            rows.addAll(collabShips);
            for (Element row : rows) {
                if (row.select("td").get(2).ownText().replace("\\\"", "\"").toLowerCase().contains("unreleased"))
                    continue;
                Elements ship = row.select("td").get(1).select("a");
                int n = 0;
                while (!addShip(ship) && n < 5) {
                    System.out.println("Failed to add ship: " + ship.attr("title"));
                    System.out.println("Trying again: " + (n++ + 1));
                    Thread.sleep(10 * 1000);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean addShip(Elements elements) {
        try {
            Document document = Jsoup.connect(azurLaneKoumakanJp + elements.attr("href"))
                    .data("query", "java")
                    .userAgent("Mozilla")
                    .get();
            // basic ship info
            Ship ship = new Ship(elements.attr("title"));
            if (new File("database/" + ship.getName() + ".json").exists()) {
                // System.out.println(ship.getName() + " already exists.");
                // return true;
            }
            Element table = document.select("table").get(0);
            ship.setRarity(table.select("tr").get(1).select("td").get(0).select("a").get(0).attr("title"));

            table = document.select("table").get(1);
            ship.setID(table.select("tr").get(0).select("td").get(0).text());
            ship.setFaction(table.select("tr").get(1).select("td").get(0).select("a").get(0).attr("title"));
            ship.setClassification(table.select("tr").get(2).select("td").get(0).select("a").get(0).attr("title"));

            addSkins(ship);

            System.out.println(ship);
            FileWriter fileWriter = new FileWriter("database/" + ship.getName() + ".json", false);
            fileWriter.write(ship.toString());
            fileWriter.close();
            // System.exit(0);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addSkins(Ship ship) {
        try {
            Document document = Jsoup.connect(azurLaneKoumakanJp + "/" + ship.getName() + "/Gallery")
                    .data("query", "java")
                    .userAgent("Mozilla")
                    .get();
            List<Skin> skinList = new ArrayList<>();
            for (Element div : document.select("div")) {
                if (div.attr("class").equalsIgnoreCase("tabbertab") &&
                        !div.attr("title").toLowerCase().contains("normal") &&
                        !div.attr("title").toLowerCase().contains("without background") &&
                        !div.attr("title").toLowerCase().contains("censored") &&
                        !div.attr("title").toLowerCase().contains("old version") &&
                        !div.attr("title").contains("CN") &&
                        !div.attr("title").contains("EN")) {
                    Skin skin = new Skin();
                    for (Element img : div.select("img")) {
                        if (img.attr("src").contains("CN") || img.attr("src").contains("EN"))
                            continue;
                        if (img.attr("src").toLowerCase().contains(ship.getName().toLowerCase().replace(" ", "_")) &&
                                img.attr("src").toLowerCase().contains("chibi")) {
                            skin.setChibiUrl(azurLaneKoumakanJp + img.attr("src").substring(0, img.attr("src").indexOf(".png") + 4).replace("thumb/", ""));
                        }
                    }
                    Element table = div.select("table").get(0);
                    for (Element row : table.select("tr")) {
                        String header = row.select("th").get(0).ownText().toLowerCase();
                        if (header.contains("obtained") || header.contains("client")) {
                            if (row.select("td").get(0).text().equalsIgnoreCase("default")) {
                                skin.setENClientName("Default");
                            } else if (row.select("td").get(0).text().equalsIgnoreCase("retrofit")) {
                                skin.setENClientName("Retrofit");
                            } else if (header.contains("en client") && row.select("td").size() == 2) {
                                skin.setENClientName(row.select("td").get(0).ownText());
                            } else if (header.contains("jp client") && row.select("td").size() == 2) {
                                skin.setJPClientName(row.select("td").get(0).ownText());
                            } else if (header.contains("cn client") && row.select("td").size() == 2) {
                                skin.setCNClientName(row.select("td").get(0).ownText());
                            }
                        }
                    }
                    skinList.add(skin);
                }
            }
            document = Jsoup.connect(azurLaneKoumakanJp + "/" + ship.getName() + "/Quotes")
                    .data("query", "java")
                    .userAgent("Mozilla")
                    .get();
            for (Element div : document.select("div")) {
                if (div.attr("class").equalsIgnoreCase("tabbertab")) {
                    if (!div.attr("title").toLowerCase().contains("english")) {
                        continue;
                    }
                    for (int i = 0; i < div.select("table").size(); i++) {
                        String skinName = div.select("h3").get(i).text().replace(" Skin", "");
                        // special unaccounted for cases
                        if (skinName.equalsIgnoreCase("Canoe Training")) // this requires cross comparison with the other tabbertabs, which is simply too much work for a skin that isn't on EN anyways;
                            skinName = "Seaside Training";
                        Skin skin;
                        if (skinName.contains("EXTRA: Post-Oath")) {
                            skin = new Skin();
                            Skin def = getSkin("Default", skinList);
                            skin.setENClientName(skinName);
                            skin.setChibiUrl(def.getChibiUrl());
                            skin.setShipDescription(def.getShipDescription());
                            skin.setSelfIntroduction(def.getSelfIntroduction());
                            skin.setAcquisition(def.getAcquisition());
                            skin.setSelfIntroductionUrl(def.getSelfIntroductionUrl());
                            skin.setAcquisitionUrl(def.getAcquisitionUrl());
                        } else {
                            if (skinName.contains(" / ")) {
                                skin = getSkin(skinName.substring(0, skinName.indexOf(" / ")), skinList);
                                if (skin == null)
                                    skin = getSkin(skinName.substring(skinName.indexOf(" / ") + 3), skinList);
                            } else
                                skin = getSkin(skinName, skinList);
                        }
                        Element table = div.select("table").get(i);
                        int index = 2;
                        if (table.select("tr").get(0).select("th").get(2).ownText().equalsIgnoreCase("cn"))
                            index++;
                        for (int rowN = 1; rowN < table.select("tr").size(); rowN++) {
                            Element row = table.select("tr").get(rowN);
                            if (row.select("td").get(0).ownText().equalsIgnoreCase("Ship Description")) {
                                skin.setShipDescription(row.select("td").get(index).ownText().replace("\\\"", "\""));
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Self Introduction")) {
                                skin.setSelfIntroduction(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSelfIntroductionUrl(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Acquisition")) {
                                skin.setAcquisition(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setAcquisitionUrl(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Login")) {
                                skin.setLogin(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setLoginUrl(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Details")) {
                                skin.setDetails(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setDetailsUrl(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Self Introduction")) {
                                skin.setSelfIntroduction(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSelfIntroductionUrl(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 1")) {
                                skin.setSecretaryIdle1(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSecretaryIdle1Url(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 2")) {
                                skin.setSecretaryIdle2(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSecretaryIdle2Url(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            }  else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 3")) {
                                skin.setSecretaryIdle3(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSecretaryIdle3Url(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 4")) {
                                skin.setSecretaryIdle4(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSecretaryIdle4Url(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 5")) {
                                skin.setSecretaryIdle5(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSecretaryIdle5Url(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 6")) {
                                skin.setSecretaryIdle6(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSecretaryIdle6Url(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Touch)")) {
                                skin.setSecretaryTouch(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSecretaryTouchUrl(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Special Touch)")) {
                                skin.setSecretarySpecialTouch(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSecretarySpecialTouchUrl(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Headpat)")) {
                                skin.setSecretaryHeadpat(row.select("td").get(index).ownText().replace("\\\"", "\""));
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.setSecretaryHeadpatUrl(row.select("td").get(1).select("a").get(0).attr("href"));
                                }
                            }
                        }
                    }
                }
            }
            ship.setSkins(skinList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Skin getSkin(String skinName, List<Skin> skinList) {
        if (skinName.toLowerCase().contains("extra")) {
            Skin skin = new Skin();
            skin.setENClientName(skinName);
            return skin;
        }
        for (Skin skin : skinList) {
            if ((skin.getCNClientName() != null && skin.getCNClientName().equalsIgnoreCase(skinName)) ||
                    (skin.getJPClientName() != null && skin.getJPClientName().equalsIgnoreCase(skinName)) ||
                    (skin.getENClientName() != null && skin.getENClientName().replaceAll("[^a-zA-Z]", "").equalsIgnoreCase(skinName.replaceAll("[^a-zA-Z]", "")))) {
                return skin;
            }
        }
        return null;
    }

}
