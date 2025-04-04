package net.vbinnie.foolishthulium.item;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.util.PlacedFeatureIndexer;
import net.vbinnie.foolishthulium.FoolishThulium;
import net.vbinnie.foolishthulium.entity.ModEntities;

public class ModItems {

    public static final Item CANCEROUS_SUBSTANCE = registerItem("cancerous_substance", new Item(new Item.Settings()));

    public static final Item RAW_THULIUM = registerItem("raw_thulium", new Item(new Item.Settings()));

    public static final Item THULIUM_INGOT = registerItem("thulium_ingot", new Item(new Item.Settings()));

    public static final Item CANCEROUS_RODENT_SPAWN_EGG = registerItem("cancerous_rodent_spawn_egg",
            new SpawnEggItem(ModEntities.CANCEROUS_RODENT, 0x88ee68, 0x4dc228, new Item.Settings().maxCount(1)));

    public static final Item HAMSTER_SPAWN_EGG = registerItem("hamster_spawn_egg",
            new SpawnEggItem(ModEntities.HAMSTER, 0xffc8f3, 0xfcc0ef, new Item.Settings().maxCount(64)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FoolishThulium.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FoolishThulium.LOGGER.info("Registering Mod Items for " + FoolishThulium.MOD_ID);
    }
}
