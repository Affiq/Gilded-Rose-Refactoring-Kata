package com.gildedrose;

import static com.gildedrose.ItemUtility.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item currentItem = items[i];

            // Increase special items, decrease regular items
            updateItemQuality(currentItem);

            // Decrement sellin for all items except sulfuras
            updateSellIn(currentItem);

            // Add additional degradation for expired items
            updateExpiredItemQuality(currentItem);

            // Add additional degradation for conjured items
            updateConjuredItemQuality(currentItem);
        }
    }

    private static void updateExpiredItemQuality(Item currentItem) {
        if (isExpired(currentItem)) {
            if (isDegradable(currentItem) && isQualityPositive(currentItem)) {
                  decrementQuality(currentItem);
            } else {
                 // safeIncrement(currentItem); // Taken out as backstage passes gets affected
            }
        }
    }

    private static void updateItemQuality(Item currentItem) {
        // We deal with degrading items with positive qualities...
        if (isDegradable(currentItem) && isQualityPositive(currentItem)) {
            decrementQuality(currentItem);
        } else if (isBackStagePasses(currentItem)) { // We deal with incrementing quality items
            handleBackStagePasses(currentItem);
        } else if (isAgedBrie(currentItem)) {
            safeIncrement(currentItem);
        } else if (isSulfuras(currentItem)) {
            // safeIncrement(currentItem);
        }
    }

    private static void handleBackStagePasses(Item currentItem) {
        if (currentItem.sellIn == 0) {
            currentItem.quality = 0;
        } else if (currentItem.sellIn > 10) {
            safeIncrement(currentItem);
        } else if (currentItem.sellIn <= 5) {
            multiSafeIncrement(currentItem, 3);
        } else if (currentItem.sellIn <= 10) {
            multiSafeIncrement(currentItem,2);
        }
    }

    public static void updateConjuredItemQuality(Item currentItem) {
        if (isConjured(currentItem)) {
            safeDecrement(currentItem);
        }
    }


}
