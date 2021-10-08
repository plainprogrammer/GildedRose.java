import java.util.Objects;

public class GildedRose {
    public void updateQuality(Item[] items) {
        for (Item item : items) {
            if (!Objects.equals(item.name, "Aged Brie") && !Objects.equals(item.name, "Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!Objects.equals(item.name, "Sulfuras, Hand of Ragnaros")) {
                        item.quality -= 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality += 1;
                    if (Objects.equals(item.name, "Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality += 1;
                            }
                        }
                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality += 1;
                            }
                        }
                    }
                }
            }
            if (!Objects.equals(item.name, "Sulfuras, Hand of Ragnaros")) {
                item.sellIn -= 1;
            }
            if (item.sellIn < 0) {
                if (!Objects.equals(item.name, "Aged Brie")) {
                    if (!Objects.equals(item.name, "Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            if (!Objects.equals(item.name, "Sulfuras, Hand of Ragnaros")) {
                                item.quality -= 1;
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality += 1;
                    }
                }
            }
        }
    }

    public static class Item {
        String name;
        int sellIn;
        int quality;

        public Item(String name, int sellIn, int initialQuality) {
            this.name    = name;
            this.sellIn  = sellIn;
            this.quality = initialQuality;
        }
    };
}
