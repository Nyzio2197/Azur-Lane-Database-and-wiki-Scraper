package com.AXC.ALDatabase.Scraper;

import com.AXC.ALDatabase.Characters.Skin;
import com.AXC.ALDatabase.Characters.Ship;
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
                        !div.attr("title").contains("CN")) {
                    Skin skin = new Skin();
                    for (Element img : div.select("img")) {
                        if (img.attr("src").contains("CN"))
                            continue;
                        if (img.attr("src").toLowerCase().contains(ship.getName().toLowerCase().replace(" ", "_")) &&
                                img.attr("src").toLowerCase().contains("chibi")) {
                            skin.setChibiUrl(azurLaneKoumakanJp + img.attr("src").substring(0, img.attr("src").indexOf(".png") + 4).replace("thumb/", ""));
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
                        System.out.println(skin);
                        Element table = div.select("table").get(i);
                        int index = 2;
                        if (table.select("tr").get(0).select("th").get(2).ownText().equalsIgnoreCase("cn"))
                            index++;
                        for (int rowN = 1; rowN < table.select("tr").size(); rowN++) {
                            Element row = table.select("tr").get(rowN);
                            if (row.select("td").get(0).ownText().equalsIgnoreCase("Ship Description")) {
                                skin.getShipDescription()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Self Introduction")) {
                                skin.getSelfIntroduction()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSelfIntroductionUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Acquisition")) {
                                skin.getAcquisition()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getAcquisitionUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Login")) {
                                skin.getLogin()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getLoginUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Details")) {
                                skin.getDetails()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getDetailsUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Self Introduction")) {
                                skin.getSelfIntroduction()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSelfIntroductionUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 1")) {
                                skin.getSecretaryIdle1()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle1Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 2")) {
                                skin.getSecretaryIdle2()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle2Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            }  else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 3")) {
                                skin.getSecretaryIdle3()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle3Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 4")) {
                                skin.getSecretaryIdle4()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle4Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 5")) {
                                skin.getSecretaryIdle5()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle5Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Idle) 6")) {
                                skin.getSecretaryIdle6()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryIdle6Url()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Touch)")) {
                                skin.getSecretaryTouch()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryTouchUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Special Touch)")) {
                                skin.getSecretarySpecialTouch()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretarySpecialTouchUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
                                }
                            } else if (row.select("td").get(0).ownText().equalsIgnoreCase("Secretary (Headpat)")) {
                                skin.getSecretaryHeadpat()[n] = row.select("td").get(index).ownText().replace("\\\"", "\"");
                                if (row.select("td").get(1).select("a").size() == 1) {
                                    skin.getSecretaryHeadpatUrl()[n] = row.select("td").get(1).select("a").get(0).attr("href");
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
            if ((skin.getName() != null && skin.getName().replaceAll("[^a-zA-Z]", "")
                    .equalsIgnoreCase(skinName.replaceAll("[^a-zA-Z]", "")))) {
                return skin;
            }
        }
        return null;
    }

}
