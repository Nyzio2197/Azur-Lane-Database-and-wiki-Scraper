package com.defµ.ALDatabase.Scraper;

import com.defµ.ALDatabase.Characters.Ship;
import com.defµ.ALDatabase.Characters.Skin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

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
                    .data("query", "java")
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
            Element table = document.select("table").get(0);
            ship.setConstructionTime(table.select("tr").get(0).select("td").get(0).select("a").get(0).ownText());
            ship.setRarity(table.select("tr").get(1).select("td").get(0).select("a").get(0).attr("title"));
            ship.setShipClass(table.select("tr").get(2).select("td").get(0).select("a").get(0).ownText());

            table = document.select("table").get(1);
            ship.setID(table.select("tr").get(0).select("td").get(0).text());
            ship.setFaction(table.select("tr").get(1).select("td").get(0).select("a").get(0).attr("title"));
            ship.setClassification(table.select("tr").get(2).select("td").get(0).select("a").get(0).attr("title"));

            // artist and VA info
            table = document.select("table").get(2);
            if (table.select("tr").get(1).select("td").get(1).select("a").size() > 0) {
                ship.setArtist(table.select("tr").get(1).select("td").get(1).select("a").get(0).ownText());
            }
            if (table.select("tr").get(2).select("td").get(1).select("a").size() > 0) {
                ship.setPixiv(table.select("tr").get(2).select("td").get(1).select("a").get(0).attr("href"));
            }
            if (table.select("tr").get(3).select("td").get(1).select("a").size() > 0) {
                ship.setTwitter(table.select("tr").get(3).select("td").get(1).select("a").get(0).attr("href"));
            }
            if (table.select("tr").get(4).select("td").get(1).select("a").size() > 0) {
                ship.setLink(table.select("tr").get(4).select("td").get(1).select("a").get(0).attr("href"));
            }
            if (table.select("tr").get(5).select("td").get(1).select("a").size() > 0) {
                ship.setVoiceActor(table.select("tr").get(5).select("td").get(1).select("a").get(0).attr("href"));
            }

            // ship stats
            for (Element element : document.select("div")) {
                if (element.attr("title").toLowerCase().contains("level") || element.attr("title").toLowerCase().contains("base")) {
                    if (element.attr("title").toLowerCase().contains("retrofit"))
                        ship.setRetrofit(true);
                    int[] intArray = new int[14];
                    table = element.select("table").get(0);
                    if (!table.attr("class").equalsIgnoreCase("wikitable"))
                        continue;
                    try {intArray[0] = Integer.parseInt(table.select("tr").get(0).select("td").get(0).ownText());} catch (NumberFormatException ignore) {}
                    ship.setArmor(table.select("tr").get(0).select("td").get(1).ownText());
                    try {
                        intArray[1] = Integer.parseInt(table.select("tr").get(0).select("td").get(2).ownText().replace("\\\"", "\""));
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[2] = Integer.parseInt(table.select("tr").get(0).select("td").get(3).ownText());
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[3] = Integer.parseInt(table.select("tr").get(1).select("td").get(0).ownText());
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[4] = Integer.parseInt(table.select("tr").get(1).select("td").get(1).ownText());
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[5] = Integer.parseInt(table.select("tr").get(1).select("td").get(2).ownText().replace("\\\"", "\""));
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[6] = Integer.parseInt(table.select("tr").get(1).select("td").get(3).ownText());
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[7] = Integer.parseInt(table.select("tr").get(2).select("td").get(0).ownText());
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[8] = Integer.parseInt(table.select("tr").get(2).select("td").get(1).ownText());
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[9] = Integer.parseInt(table.select("tr").get(2).select("td").get(2).ownText().replace("\\\"", "\""));
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[10] = Integer.parseInt(table.select("tr").get(2).select("td").get(3).ownText());
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[11] = Integer.parseInt(table.select("tr").get(3).select("td").get(0).ownText());
                    } catch (NumberFormatException ignore) {}
                    try {
                        intArray[12] = Integer.parseInt(table.select("tr").get(3).select("td").get(1).ownText());
                        intArray[13] = Integer.parseInt(table.select("tr").get(3).select("td").get(2).ownText().replace("\\\"", "\""));
                    } catch (NumberFormatException ignore) {}
                    ship.getStatsHashMap().put(element.attr("title"), intArray);
                }
            }

            // gear info
            for (Element element : document.select("table")) {
                if (element.select("th").size() > 0 && element.select("th").get(0).ownText().equalsIgnoreCase("gear")) {
                    ship.setSlot1Efficiency(element.select("tr").get(2).select("td").get(1).text());
                    ship.setSlot1Equipment(element.select("tr").get(2).select("td").get(2).select("a").get(0).ownText());
                    ship.setSlot2Efficiency(element.select("tr").get(3).select("td").get(1).text());
                    ship.setSlot2Equipment(element.select("tr").get(3).select("td").get(2).select("a").get(0).ownText());
                    ship.setSlot3Efficiency(element.select("tr").get(4).select("td").get(1).text());
                    ship.setSlot3Equipment(element.select("tr").get(4).select("td").get(2).select("a").get(0).ownText());
                    break;
                }
            }

            // fleet tech
            for (Element element : document.select("table")) {
                if (element.select("th").size() > 0 && element.select("th").get(0).ownText().equalsIgnoreCase("Unlock Conditions")) {
                    // collection bonus
                    StringBuilder collectionStatBonus = new StringBuilder();
                    for (Element e : element.select("tr").get(1).select("td").get(0).children()) {
                        collectionStatBonus.append(e.attr("title")).append(" ");
                    }
                    String collectionStatBonusTemp = collectionStatBonus.toString().strip().trim() + element.select("tr").get(1).select("td").get(0).ownText();
                    if (collectionStatBonusTemp.length() > 2) {
                        try {
                            String last = collectionStatBonusTemp.substring(collectionStatBonusTemp.length() - 1);
                            Integer.parseInt(last);
                            ship.setCollectionStatBonus(collectionStatBonusTemp);
                        } catch (NumberFormatException ignore) {}
                    }
                    try {
                        ship.setCollectionTechPoints(Integer.parseInt(element.select("tr").get(1).select("td").get(1).ownText()));
                    } catch (NumberFormatException ignore) {}

                    // MLB bonus
                    StringBuilder maxLimitBreak = new StringBuilder();
                    for (Element e : element.select("tr").get(2).select("td").get(0).children()) {
                        maxLimitBreak.append(e.attr("title")).append(" ");
                    }
                    String maxLimitBreakTemp = maxLimitBreak.toString().strip() + element.select("tr").get(2).select("td").get(0).ownText();
                    if (maxLimitBreakTemp.length() > 2) {
                        try {
                            String last = maxLimitBreakTemp.substring(maxLimitBreakTemp.length() - 1);
                            Integer.parseInt(last);
                            ship.setMaxLimitBreakStatBonus(maxLimitBreakTemp);
                        } catch (NumberFormatException ignore) {}
                    }
                    try {
                        ship.setMaxLimitBreakTechPoints(Integer.parseInt(element.select("tr").get(2).select("td").get(1).ownText()));
                    } catch (NumberFormatException ignore) {}

                    // level 120 bonus
                    StringBuilder level120Bonus = new StringBuilder();
                    for (Element e : element.select("tr").get(3).select("td").get(0).children()) {
                        level120Bonus.append(e.attr("title")).append(" ");
                    }
                    String level120BonusTemp = level120Bonus.toString().strip() + element.select("tr").get(3).select("td").get(0).ownText();
                    if (level120BonusTemp.length() > 2) {
                        try {
                            String last = level120BonusTemp.substring(level120BonusTemp.length() - 1);
                            Integer.parseInt(last);
                            ship.setLevel120StatBonus(level120BonusTemp);
                        } catch (NumberFormatException ignore) {}
                    }
                    try {
                        ship.setLevel120TechPoints(Integer.parseInt(element.select("tr").get(3).select("td").get(1).ownText()));
                    } catch (NumberFormatException ignore) {}
                    break;
                }
            }

            // enhance and retire values
            for (Element element : document.select("table")) {
                if (element.select("th").size() > 0 && element.select("th").get(0).ownText().equalsIgnoreCase("Enhance Value")) {
                    // enhance values
                    String[] temp = element.select("tr").get(1).select("td").get(0).text().split(" ");
                    if (temp.length < 3)
                        continue;
                    int[] enhanceValue = new int[4];
                    for (int n = 0; n < 4; n++) {
                        try {
                            enhanceValue[n] = Integer.parseInt(temp[n]);
                        } catch (NumberFormatException ignore) {}
                    }
                    ship.setEnhancementValue(enhanceValue);

                    // retire values
                    temp = element.select("tr").get(1).select("td").get(1).text().split(" ");
                    if (temp.length < 3)
                        continue;
                    int[] retireValue = new int[4];
                    for (int n = 0; n < temp.length; n++) {
                        try {
                            if (temp.length == 3) {
                                retireValue[n + 1] = Integer.parseInt(temp[n]);
                            } else {
                                retireValue[n] = Integer.parseInt(temp[n]);
                            }
                        } catch (NumberFormatException ignore) {}
                    }
                    ship.setRetireValue(retireValue);
                }
            }

            // limit break ranks
            for (Element element : document.select("table")) {
                if (element.select("th").size() > 0 && element.select("th").get(0).ownText().toLowerCase().contains("limit break ranks")) {
                    ship.setLimitBreak1(element.select("tr").get(1).select("td").get(0).ownText());
                    ship.setLimitBreak2(element.select("tr").get(2).select("td").get(0).ownText());
                    ship.setLimitBreak3(element.select("tr").get(3).select("td").get(0).ownText());
                }
            }

            // skills
            for (Element element : document.select("table")) {
                if (element.select("th").size() > 1 && element.select("th").get(1).ownText().toLowerCase().contains("skills")) {
                    for (int n = 1; n < element.select("tr").size(); n++) {
                        Element row = element.select("tr").get(n);
                        if (n == 1) {
                            ship.setSkill1Name(row.select("td").get(1).text().replace("&nbsp;", " ").replace(" CN", "; CN"));
                            ship.setSkill1Description(row.select("td").get(2).ownText().replace("\\\"", "\""));
                        } else if (n == 2) {
                            ship.setSkill2Name(row.select("td").get(1).text().replace("&nbsp;", " ").replace(" CN", "; CN"));
                            ship.setSkill2Description(row.select("td").get(2).ownText().replace("\\\"", "\""));
                        } else if (n == 3) {
                            ship.setSkill3Name(row.select("td").get(1).text().replace("&nbsp;", " ").replace(" CN", "; CN"));
                            ship.setSkill3Description(row.select("td").get(2).ownText().replace("\\\"", "\""));
                        } else if (n == 4) {
                            ship.setSkill4Name(row.select("td").get(1).text().replace("&nbsp;", " ").replace(" CN", "; CN"));
                            ship.setSkill4Description(row.select("td").get(2).ownText().replace("\\\"", "\""));
                        }
                    }
                }
            }

            // PR Strengthening level
            if (ship.getID().toLowerCase().contains("plan")) {
                for (Element element : document.select("table")) {
                    if (element.select("th").size() > 0 && element.select("th").get(0).ownText().toLowerCase().contains("strengthen")) {
                        Element level5Row = element.select("tr").get(1).select("td").get(0);
                        StringBuilder level5 = new StringBuilder();
                        for (Element bullet : level5Row.select("li")) {
                            for (Node content : bullet.childNodes()) {
                                if (content.hasAttr("title")) {
                                    level5.append(content.attr("title")).append(" ");
                                } else {
                                    level5.append(content);
                                }
                            }
                            level5.append("\n");
                        }
                        ship.setLevel5(level5.toString());

                        Element level10Row = element.select("tr").get(2).select("td").get(0);
                        StringBuilder level10 = new StringBuilder();
                        for (Element bullet : level10Row.select("li")) {
                            for (Node content : bullet.childNodes()) {
                                if (content.hasAttr("title")) {
                                    level10.append(content.attr("title")).append(" ");
                                } else {
                                    level10.append(content);
                                }
                            }
                            level10.append("\n");
                        }
                        ship.setLevel10(level10.toString());

                        Element level15Row = element.select("tr").get(3).select("td").get(0);
                        StringBuilder level15 = new StringBuilder();
                        for (Element bullet : level15Row.select("li")) {
                            for (Node content : bullet.childNodes()) {
                                if (content.hasAttr("title")) {
                                    level15.append(content.attr("title")).append(" ");
                                } else {
                                    level15.append(content);
                                }
                            }
                            level15.append("\n");
                        }
                        ship.setLevel15(level15.toString());

                        Element level20Row = element.select("tr").get(4).select("td").get(0);
                        StringBuilder level20 = new StringBuilder();
                        for (Element bullet : level20Row.select("li")) {
                            for (Node content : bullet.childNodes()) {
                                if (content.hasAttr("title")) {
                                    level20.append(content.attr("title")).append(" ");
                                } else {
                                    level20.append(content);
                                }
                            }
                            level20.append("\n");
                        }
                        ship.setLevel20(level20.toString());

                        Element level25Row = element.select("tr").get(5).select("td").get(0);
                        StringBuilder level25 = new StringBuilder();
                        for (Element bullet : level25Row.select("li")) {
                            for (Node content : bullet.childNodes()) {
                                if (content.hasAttr("title")) {
                                    level25.append(content.attr("title")).append(" ");
                                } else {
                                    level25.append(content);
                                }
                            }
                            level25.append("\n");
                        }
                        ship.setLevel25(level25.toString());

                        Element level30Row = element.select("tr").get(6).select("td").get(0);
                        StringBuilder level30 = new StringBuilder();
                        for (Element bullet : level30Row.select("li")) {
                            for (Node content : bullet.childNodes()) {
                                if (content.hasAttr("title")) {
                                    level30.append(content.attr("title")).append(" ");
                                } else {
                                    level30.append(content);
                                }
                            }
                            level30.append("\n");
                        }
                        ship.setLevel30(level30.toString());
                    }
                }
            }

            // construction / drop locations
            if (!ship.getID().toLowerCase().contains("plan")) {
                for (Element element : document.select("table")) {
                    if (element.select("th").size() > 0 &&
                            element.select("th").get(0).select("a").size() > 0 &&
                            element.select("th").get(0).select("a").get(0).ownText().toLowerCase().contains("construction")) {
                        boolean[] pools = new boolean[5];
                        for (int n = 0; n < 5; n++) {
                            if (element.select("tr").get(3).select("td")
                                            .get(n).attr("style").toLowerCase().contains("lemon") ||
                                    element.select("tr").get(3).select("td")
                                            .get(n).attr("style").toLowerCase().contains("green")) {
                                pools[n] = true;
                            }
                        }
                        ship.setConstructionPools(pools);

                        boolean[][] drops = new boolean[4][13];
                        for (int rowN = 1; rowN <= 4; rowN++) {
                            Element row = element.select("tr").get(rowN);
                            boolean begin = false;
                            int colN = 0;
                            for (Element col : row.children()) {
                                if (begin) {
                                    if (col.attr("style").toLowerCase().contains("green")) {
                                        drops[rowN - 1][colN] = true;
                                    }
                                    colN++;
                                } else if (!col.ownText().contains(":") &&
                                        col.ownText().contains("1") ||
                                        col.ownText().contains("2") ||
                                        col.ownText().contains("3") ||
                                        col.ownText().contains("4")) {
                                    begin = true;
                                }
                            }
                        }
                        ship.setDropLocations(drops);

                        if (element.select("tr").size() == 5) {
                            StringBuilder additionalNotes = new StringBuilder();
                            for (Node text : element.select("tr").get(4).select("td").get(0).childNodes()) {
                                if (text.hasAttr("title")) {
                                    additionalNotes.append(text.attr("title"));
                                } else {
                                    additionalNotes.append(text);
                                }
                            }
                            ship.setAdditionalNotes(additionalNotes.toString());
                        }
                    }
                }
            }

            // retrofit nodes
            if (ship.isRetrofit()) {
                for (Element element : document.select("table")) {
                    if (element.select("b").size() > 0 && element.select("b").get(0).ownText().toLowerCase().contains("retrofit table")) {
                        for (int n = 1; n < element.select("tr").size(); n++) {
                            Element row = element.select("tr").get(n);
                            Ship.RetrofitNode retrofitNode = new Ship.RetrofitNode();
                            retrofitNode.setIndex(row.select("td").get(0).ownText());
                            retrofitNode.setProject(row.select("td").get(1).ownText());
                            StringBuilder attributes = new StringBuilder();
                            for (Node node : row.select("td").get(2).childNodes()) {
                                if (node.hasAttr("title")) {
                                    attributes.append(node.attr("title"));
                                } else {
                                    attributes.append(node);
                                }
                            }
                            retrofitNode.setAttributes(attributes.toString());
                            StringBuilder materials = new StringBuilder();
                            for (Node node : row.select("td").get(3).childNodes()) {
                                if (node instanceof Element &&
                                        ((Element) node).select("a").size() == 1 &&
                                        ((Element) node).select("a").get(0).select("img").size() == 1) {
                                    materials.append(((Element) node).select("a").get(0)
                                            .select("img").get(0).attr("alt"), 0, ((Element) node).select("a").get(0)
                                                    .select("img").get(0).attr("alt").length() - 4);
                                } else {
                                    materials.append(node);
                                }
                            }
                            retrofitNode.setMaterials(materials.toString().replace("Icon", ""));
                            try {
                                retrofitNode.setCoinCost(Integer.parseInt(row.select("td").get(4).ownText()));
                            } catch (NumberFormatException ignore) {}
                            try {
                                retrofitNode.setLevelRequired(Integer.parseInt(row.select("td").get(5).ownText()));
                            } catch (NumberFormatException ignore) {}
                            try {
                                retrofitNode.setLimitBreakRequired(Integer.parseInt(row.select("td").get(6).ownText().substring(0, 1)));
                            } catch (NumberFormatException ignore) {}
                            try {
                                retrofitNode.setRecurrence(Integer.parseInt(row.select("td").get(7).ownText()));
                            } catch (NumberFormatException ignore) {}
                            retrofitNode.setRequiredIndexes(row.select("td").get(8).ownText().split(", "));
                            ship.getRetrofitNodes().add(retrofitNode);
                        }
                        break;
                    }
                }
            }

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
                        !div.attr("title").contains("CN")) {
                    Skin skin = new Skin();
                    for (Element img : div.select("img")) {
                        if (img.attr("src").contains("CN"))
                            continue;
                        if (img.attr("src").toLowerCase().contains(ship.getName().toLowerCase().replace(" ", "_")) &&
                                img.attr("src").toLowerCase().contains("chibi")) {
                            skin.setChibiUrl(azurLaneKoumakanJp + img.attr("src").substring(0, img.attr("src").indexOf(".png") + 4));
                        } else if (img.attr("src").toLowerCase().contains(ship.getName().toLowerCase().replace(" ", "_")) &&
                                img.attr("src").toLowerCase().contains("withoutbg")) {
                            skin.setSpriteNoBackgroundUrl(azurLaneKoumakanJp + img.attr("src").substring(0, img.attr("src").indexOf(".png") + 4));
                        } else if (img.attr("src").toLowerCase().contains(ship.getName().toLowerCase().replace(" ", "_")) &&
                                img.attr("src").contains("CN")) {
                            skin.setSpriteCNUrl(azurLaneKoumakanJp + img.attr("src").substring(0, img.attr("src").indexOf(".png") + 4));
                        } else if (img.attr("src").toLowerCase().contains(ship.getName().toLowerCase().replace(" ", "_")) &&
                                img.attr("src").toLowerCase().contains("censored")) {
                            skin.setSpriteCensoredUrl(azurLaneKoumakanJp + img.attr("src").substring(0, img.attr("src").indexOf(".png") + 4));
                        } else if (img.attr("src").toLowerCase().contains(ship.getName().toLowerCase().replace(" ", "_"))) {
                            skin.setSpriteUrl(azurLaneKoumakanJp + img.attr("src").substring(0, img.attr("src").indexOf(".png") + 4));
                        }
                    }
                    Element table = div.select("table").get(0);
                    for (Element row : table.select("tr")) {
                        String header = row.select("th").get(0).ownText().toLowerCase();
                        if (header.contains("obtained")) {
                            if (row.select("td").get(0).text().equalsIgnoreCase("default")) {
                                skin.setENClientName("Default");
                            } else if (row.select("td").get(0).text().equalsIgnoreCase("retrofit")) {
                                skin.setENClientName("Retrofit");
                            }
                            skin.setObtainedFrom(row.select("td").get(0).text());
                        } else if (header.contains("en client") && row.select("td").size() == 2) {
                            skin.setENClientName(row.select("td").get(0).ownText());
                            skin.setENClientAvailability(row.select("td").get(1).ownText());
                        } else if (header.contains("jp client") && row.select("td").size() == 2) {
                            skin.setJPClientName(row.select("td").get(0).ownText());
                            skin.setJPClientAvailability(row.select("td").get(1).ownText());
                        } else if (header.contains("cn client") && row.select("td").size() == 2) {
                            skin.setCNClientName(row.select("td").get(0).ownText());
                            skin.setCNClientAvailability(row.select("td").get(1).ownText());
                        } else if (header.contains("cost")) {
                            StringBuilder cost = new StringBuilder();
                            for (Node node : row.select("td").get(0).childNodes()) {
                                if (node.hasAttr("title")) {
                                    cost.append(node.attr("title"));
                                } else {
                                    cost.append(node);
                                }
                            }
                            skin.setCost(cost.toString());
                        } else if (header.contains("2d")) {
                            if (row.select("td").get(0).ownText().equalsIgnoreCase("yes")) {
                                skin.setL2dModel(true);
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
                    int n = -1;
                    if (div.attr("title").toLowerCase().contains("chinese")) {
                        n = 0;
                    } else if (div.attr("title").toLowerCase().contains("japanese")) {
                        n = 1;
                    } else if (div.attr("title").toLowerCase().contains("english")) {
                        n = 2;
                    }
                    for (int i = 0; i < div.select("table").size(); i++) {
                        String skinName = div.select("h3").get(i).text().replace(" Skin", "");
                        Skin skin;
                        if (skinName.contains(" / ")) {
                            skin = getSkin(skinName.substring(0, skinName.indexOf(" / ")), skinList);
                            if (skin == null)
                                skin = getSkin(skinName.substring(skinName.indexOf(" / ") + 3), skinList);
                        } else
                            skin = getSkin(skinName, skinList);
                        // special unaccounted for cases
                        if (skinName.equalsIgnoreCase("Beauty of White Jade")) // this requires cross comparison with the other tabbertabs, which is simply too much work for a skin that isn't on EN anyways;
                            continue;
                        Element table = div.select("table").get(i);
                        for (int rowN = 1; rowN < table.select("tr").size(); rowN++) {
                            Element row = table.select("tr").get(rowN);
                            if (row.select("td").get(0).ownText().equalsIgnoreCase("Ship Description")) {
                                skin.getShipDescription()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Self Introduction")) {
                                skin.getSelfIntroduction()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSelfIntroductionUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Acquisition")) {
                                skin.getAcquisition()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAcquisitionUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Login")) {
                                skin.getLogin()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getLoginUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Details")) {
                                skin.getDetails()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getDetailsUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Self Introduction")) {
                                skin.getSelfIntroduction()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSelfIntroductionUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 1")) {
                                skin.getSecretaryIdle1()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle1Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 2")) {
                                skin.getSecretaryIdle2()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle2Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            }  else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 3")) {
                                skin.getSecretaryIdle3()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle3Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 4")) {
                                skin.getSecretaryIdle4()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle4Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 5")) {
                                skin.getSecretaryIdle5()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle5Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 6")) {
                                skin.getSecretaryIdle6()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle6Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Touch)")) {
                                skin.getSecretaryTouch()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryTouchUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Special Touch)")) {
                                skin.getSecretarySpecialTouch()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretarySpecialTouchUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Headpat)")) {
                                skin.getSecretaryHeadpat()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryHeadpatUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Task")) {
                                skin.getTask()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getTaskUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Task Complete")) {
                                skin.getTaskComplete()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getTaskCompleteUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Mail")) {
                                skin.getMail()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getMailUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Return From Mission")) {
                                skin.getReturnFromMission()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getReturnFromMissionUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Commission")) {
                                skin.getCommission()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getCommissionUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Strengthening")) {
                                skin.getStrengthening()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getStrengtheningUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Starting Mission")) {
                                skin.getStartingMission()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getStartingMissionUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("MVP")) {
                                skin.getMvp()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getMvpUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Defeat")) {
                                skin.getDefeat()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getDefeatUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Skill Activation")) {
                                skin.getSkillActivation()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSkillActivationUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Low HP")) {
                                skin.getLowHP()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getLowHPUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Affinity (Disappointed)")) {
                                skin.getAffinityDisappointed()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAffinityDisappointedUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Affinity (Stranger)")) {
                                skin.getAffinityStranger()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAffinityStrangerUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Affinity (Friendly)")) {
                                skin.getAffinityFriendly()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAffinityFriendlyUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Affinity (Like)")) {
                                skin.getAffinityLike()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAffinityLikeUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Affinity (Love)")) {
                                skin.getAffinityLove()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAffinityLoveUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Pledge")) {
                                skin.getPledge()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getPledgeUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 1")) {
                                skin.getAdditionalVoiceLine1()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine1Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 2")) {
                                skin.getAdditionalVoiceLine2()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine2Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 3")) {
                                skin.getAdditionalVoiceLine3()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine3Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 4")) {
                                skin.getAdditionalVoiceLine4()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine4Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 5")) {
                                skin.getAdditionalVoiceLine5()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine5Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 6")) {
                                skin.getAdditionalVoiceLine6()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine6Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 7")) {
                                skin.getAdditionalVoiceLine7()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine7Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 8")) {
                                skin.getAdditionalVoiceLine8()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine8Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 9")) {
                                skin.getAdditionalVoiceLine9()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine9Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 10")) {
                                skin.getAdditionalVoiceLine10()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine10Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Additional Voice Line 11")) {
                                skin.getAdditionalVoiceLine11()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAdditionalVoiceLine11Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Gift Description Valentines 2018")) {
                                skin.getGiftDescriptionValentines2018()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Gift Description Valentines 2019")) {
                                skin.getGiftDescriptionValentines2019()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Gift Description Valentines 2020")) {
                                skin.getGiftDescriptionValentines2020()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Gift Description Valentines 2021")) {
                                skin.getGiftDescriptionValentines2021()[n] = row.select("td").get(2).ownText().replace("\\\"", "\"");
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
