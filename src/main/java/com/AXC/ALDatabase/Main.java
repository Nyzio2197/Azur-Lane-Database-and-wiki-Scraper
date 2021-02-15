package com.AXC.ALDatabase;

import com.AXC.ALDatabase.Characters.Ship;
import com.AXC.ALDatabase.Scraper.AzurLaneScraper;

/**
 * @author Alan Xiao (axcdevelopment@gmail.com)
 */

public class Main {

    public static void main(String[] args) {
        // AzurLaneScraper.scrape();
        Ship s = new Ship("Princeton");
        AzurLaneScraper.addSkins(s);
        System.out.println(s);
    }

}
