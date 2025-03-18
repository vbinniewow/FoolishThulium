package net.vbinnie.foolishthulium.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.vbinnie.foolishthulium.FoolishThulium;
import net.vbinnie.foolishthulium.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup FOOLISH_THULIUM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FoolishThulium.MOD_ID, "foolish_thulium_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.THULIUM_INGOT))
                    .displayName(Text.translatable("itemgroup.foolish-thulium.foolish_thulium_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.THULIUM_INGOT);
                        entries.add(ModItems.RAW_THULIUM);
                        entries.add(ModItems.CANCEROUS_SUBSTANCE);
                        entries.add(ModBlocks.THULIUM_BLOCK);
                        entries.add(ModBlocks.RAW_THULIUM_BLOCK);
                        entries.add(ModBlocks.DEEPSLATE_THULIUM_ORE);
                        entries.add(ModItems.CANCEROUS_RODENT_SPAWN_EGG);
                    }).build());


    public static void registerItemGroups() {
        FoolishThulium.LOGGER.info("Registering Item Groups for " + FoolishThulium.MOD_ID);
    }
}