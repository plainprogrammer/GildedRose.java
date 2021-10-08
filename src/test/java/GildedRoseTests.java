import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class GildedRoseTests {
    GildedRose store;

    @BeforeEach
    void createNewStore() {
        store = new GildedRose();
    }

    @Nested
    @DisplayName("when updating quality")
    class UpdatingQuality {
        @Nested
        @DisplayName("of a single")
        class SingleItem {
            GildedRose.Item[] items = new GildedRose.Item[1];

            @Nested
            @DisplayName("normal item")
            class NormalItem {
                @BeforeEach
                void setupAndUpdateItems() {
                    items[0] = new GildedRose.Item("Normal Item", 5, 10);
                    store.updateQuality(items);
                }

                @Test
                void decrementsSellInByOne() {
                    assertEquals(4, items[0].sellIn);
                }

                @Nested
                @DisplayName("before sell date")
                class BeforeSellDate {
                    @Test
                    void decrementsQualityByOne() {
                        assertEquals(9, items[0].quality);
                    }
                }

                @Nested
                @DisplayName("on sell date")
                class OnSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Normal Item", 0, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void decrementsSellInByTwo() {
                        assertEquals(8, items[0].quality);
                    }
                }

                @Nested
                @DisplayName("after sell date")
                class AfterSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Normal Item", -10, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void decrementsSellInByTwo() {
                        assertEquals(8, items[0].quality);
                    }
                }

                @Nested
                @DisplayName("of zero quality")
                class OfZeroQuality {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Normal Item", 5, 0);
                        store.updateQuality(items);
                    }

                    @Test
                    void decrementsSellInByTwo() {
                        assertEquals(0, items[0].quality);
                    }
                }
            }

            @Nested
            @DisplayName("Aged Brie")
            class AgedBrie {
                @BeforeEach
                void setupAndUpdateItems() {
                    items[0] = new GildedRose.Item("Aged Brie", 5, 10);
                    store.updateQuality(items);
                }

                @Test
                void decrementsSellInByOne() {
                    assertEquals(4, items[0].sellIn);
                }

                @Nested
                @DisplayName("before sell date")
                class BeforeSellDate {
                    @Test
                    void incrementsQualityByOne() {
                        assertEquals(11, items[0].quality);
                    }

                    @Nested
                    @DisplayName("with maximum quality")
                    class WithMaximumQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Aged Brie", 5, 50);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(50, items[0].quality);
                        }
                    }
                }

                @Nested
                @DisplayName("on sell date")
                class OnSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Aged Brie", 0, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void incrementsQualityByTwo() {
                        assertEquals(12, items[0].quality);
                    }

                    @Nested
                    @DisplayName("near maximum quality")
                    class NearMaximumQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Aged Brie", 0, 49);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(50, items[0].quality);
                        }
                    }

                    @Nested
                    @DisplayName("with maximum quality")
                    class WithMaximumQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Aged Brie", 0, 50);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(50, items[0].quality);
                        }
                    }
                }

                @Nested
                @DisplayName("after sell date")
                class AfterSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Aged Brie", -10, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void decrementsSellInByTwo() {
                        assertEquals(12, items[0].quality);
                    }

                    @Nested
                    @DisplayName("with maximum quality")
                    class WithMaximumQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Aged Brie", -10, 50);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(50, items[0].quality);
                        }
                    }
                }
            }

            @Nested
            @DisplayName("Sulfuras")
            class Sulfuras {
                @BeforeEach
                void setupAndUpdateItems() {
                    items[0] = new GildedRose.Item("Sulfuras, Hand of Ragnaros", 5, 80);
                    store.updateQuality(items);
                }

                @Test
                void doesNotChangeSellIn() {
                    assertEquals(5, items[0].sellIn);
                }

                @Nested
                @DisplayName("before sell date")
                class BeforeSellDate {
                    @Test
                    void doesNotChangeQuality() {
                        assertEquals(80, items[0].quality);
                    }
                }

                @Nested
                @DisplayName("on sell date")
                class OnSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Sulfuras, Hand of Ragnaros", 0, 80);
                        store.updateQuality(items);
                    }

                    @Test
                    void doesNotChangeQuality() {
                        assertEquals(80, items[0].quality);
                    }
                }

                @Nested
                @DisplayName("after sell date")
                class AfterSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Sulfuras, Hand of Ragnaros", -10, 80);
                        store.updateQuality(items);
                    }

                    @Test
                    void doesNotChangeQuality() {
                        assertEquals(80, items[0].quality);
                    }
                }
            }

            @Nested
            @DisplayName("Backstage Pass")
            class BackstagePass {
                @BeforeEach
                void setupAndUpdateItems() {
                    items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 5, 80);
                    store.updateQuality(items);
                }

                @Test
                void decrementsSellInByOne() {
                    assertEquals(4, items[0].sellIn);
                }

                @Nested
                @DisplayName("long before sell date")
                class LongBeforeSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 11, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void incrementsQualityByOne() {
                        assertEquals(11, items[0].quality);
                    }

                    @Nested
                    @DisplayName("with maximum quality")
                    class WithMaximumQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 11, 50);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(50, items[0].quality);
                        }
                    }
                }

                @Nested
                @DisplayName("medium close to sell date (upper bound)")
                class MediumBeforeSellDateUpperBound {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void incrementsQualityByOne() {
                        assertEquals(12, items[0].quality);
                    }

                    @Nested
                    @DisplayName("with maximum quality")
                    class WithMaximumQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 10, 50);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(50, items[0].quality);
                        }
                    }
                }

                @Nested
                @DisplayName("medium close to sell date (lower bound)")
                class MediumBeforeSellDateLowerBound {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 6, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void incrementsQualityByOne() {
                        assertEquals(12, items[0].quality);
                    }

                    @Nested
                    @DisplayName("with maximum quality")
                    class WithMaximumQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 6, 50);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(50, items[0].quality);
                        }
                    }
                }

                @Nested
                @DisplayName("very close to sell date (upper bound)")
                class VeryCloseBeforeSellDateUpperBound {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 5, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void incrementsQualityByOne() {
                        assertEquals(13, items[0].quality);
                    }

                    @Nested
                    @DisplayName("with maximum quality")
                    class WithMaximumQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 5, 50);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(50, items[0].quality);
                        }
                    }
                }

                @Nested
                @DisplayName("very close to sell date (lower bound)")
                class VeryCloseBeforeSellDateLowerBound {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 1, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void incrementsQualityByOne() {
                        assertEquals(13, items[0].quality);
                    }

                    @Nested
                    @DisplayName("with maximum quality")
                    class WithMaximumQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 1, 50);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(50, items[0].quality);
                        }
                    }
                }

                @Nested
                @DisplayName("on sell date")
                class OnSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", 0, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void incrementsQualityByOne() {
                        assertEquals(0, items[0].quality);
                    }
                }

                @Nested
                @DisplayName("after sell date")
                class AfterSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Backstage passes to a TAFKAL80ETC concert", -10, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void incrementsQualityByOne() {
                        assertEquals(0, items[0].quality);
                    }
                }
            }

            @Nested
            @DisplayName("Conjured Item")
            @Disabled("Until Refactored")
            class ConjuredItem {
                @BeforeEach
                void setupAndUpdateItems() {
                    items[0] = new GildedRose.Item("Conjured Mana Cake", 5, 10);
                    store.updateQuality(items);
                }

                @Test
                void decrementsSellInByOne() {
                    assertEquals(4, items[0].sellIn);
                }

                @Nested
                @DisplayName("before sell date")
                class BeforeSellDate {
                    @Test
                    void decrementsQualityByTwo() {
                        assertEquals(8, items[0].quality);
                    }

                    @Nested
                    @DisplayName("at zero quality")
                    class AtZeroQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Conjured Mana Cake", 5, 0);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(0, items[0].quality);
                        }
                    }
                }

                @Nested
                @DisplayName("on sell date")
                class OnSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Conjured Mana Cake", 0, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void decrementsQualityByFour() {
                        assertEquals(6, items[0].quality);
                    }

                    @Nested
                    @DisplayName("at zero quality")
                    class AtZeroQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Conjured Mana Cake", 0, 0);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(0, items[0].quality);
                        }
                    }
                }

                @Nested
                @DisplayName("after sell date")
                class AfterSellDate {
                    @BeforeEach
                    void setupAndUpdateItems() {
                        items[0] = new GildedRose.Item("Conjured Mana Cake", -10, 10);
                        store.updateQuality(items);
                    }

                    @Test
                    void decrementsQualityByFour() {
                        assertEquals(6, items[0].quality);
                    }

                    @Nested
                    @DisplayName("at zero quality")
                    class AtZeroQuality {
                        @BeforeEach
                        void setupAndUpdateItems() {
                            items[0] = new GildedRose.Item("Conjured Mana Cake", -10, 0);
                            store.updateQuality(items);
                        }

                        @Test
                        void doesNotChangeQuality() {
                            assertEquals(0, items[0].quality);
                        }
                    }
                }
            }
        }

        @Nested
        @DisplayName("of multiple items")
        class MultipleItems {
            GildedRose.Item[] items = new GildedRose.Item[2];

            @BeforeEach
            void setupAndUpdateItems() {
                items[0] = new GildedRose.Item("NORMAL ITEM", 5, 10);
                items[1] = new GildedRose.Item("Aged Brie", 3, 10);

                store.updateQuality(items);
            }

            @Test
            void normalItemQualityChangesByOne() {
                assertEquals(9, items[0].quality);
            }

            @Test
            void normalItemSellInChangesByOne() {
                assertEquals(4, items[0].sellIn);
            }

            @Test
            void agedBrieQualityChangesByOne() {
                assertEquals(11, items[1].quality);
            }

            @Test
            void agedBrieSellInChangesByOne() {
                assertEquals(2, items[1].sellIn);
            }
        }
    }
}
