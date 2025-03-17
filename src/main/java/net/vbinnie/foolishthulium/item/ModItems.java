package net.vbinnie.foolishthulium.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.util.PlacedFeatureIndexer;
import net.vbinnie.foolishthulium.FoolishThulium;

public class ModItems {

    public static final Item CANCEROUS_SUBSTANCE = registerItem("cancerous_substance", new Item(new Item.Settings()));

    public static final Item RAW_THULIUM = registerItem("raw_thulium", new Item(new Item.Settings()));

    public static final Item THULIUM_INGOT = registerItem("thulium_ingot", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FoolishThulium.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FoolishThulium.LOGGER.info("Registering Mod Items for " + FoolishThulium.MOD_ID);
    }
}
