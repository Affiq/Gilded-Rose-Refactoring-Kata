package com.gildedrose;

public class ItemUtility {

    public static void incrementQuality(Item item) {
        item.quality = item.quality + 1;
    }

    public static void decrementQuality(Item item) {
        item.quality = item.quality - 1;
    }

    public static void decrementSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    public static boolean isQualityPositive(Item item) {
        return (item.quality > 0);
    }

    public static void safeIncrement(Item item) {
        if (checkMaxQuality(item)) {
            incrementQuality(item);
        }
    }

    public static void safeDecrement(Item item) {
        if (checkMinQuality(item)) {
            decrementQuality(item);
        }
    }

    public static void multiSafeIncrement(Item item, int increment) {
        for (int i=0; i < increment ;i++) {
            safeIncrement(item);
        }
    }

    public static void updateSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            decrementSellIn(item);
        }
    }

    public static boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    public static boolean checkMaxQuality(Item item) {
        return item.quality < 50;
    }

    public static boolean checkMinQuality(Item item) {
        return item.quality <= 0;
    }

    public static boolean isBackStagePasses(Item item) {
        return item.name.contains("Backstage passes");
    }

    public static boolean isSulfuras(Item item) {
        return item.name.contains("Sulfuras");
    }

    public static boolean isAgedBrie(Item item) {
        return item.name.contains("Aged Brie");
    }

    public static boolean isDegradable(Item item) {
        return !(isAgedBrie(item) || isSulfuras(item) || isBackStagePasses(item));
    }

    public static boolean isConjured(Item item) {
        return item.name.contains("Conjured");
    }

}
