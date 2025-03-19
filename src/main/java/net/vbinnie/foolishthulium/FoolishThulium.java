package net.vbinnie.foolishthulium;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.vbinnie.foolishthulium.block.ModBlocks;
import net.vbinnie.foolishthulium.effect.ModEffects;
import net.vbinnie.foolishthulium.entity.ModEntities;
import net.vbinnie.foolishthulium.entity.custom.CancerousRodentEntity;
import net.vbinnie.foolishthulium.item.ModItemGroups;
import net.vbinnie.foolishthulium.item.ModItems;
import net.vbinnie.foolishthulium.util.ModTags;
import net.vbinnie.foolishthulium.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoolishThulium implements ModInitializer {
	public static final String MOD_ID = "foolish-thulium";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModWorldGeneration.generateModWorldGen();
		ModEntities.registerModEntities();
		ModEffects.registerEffects();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		FabricDefaultAttributeRegistry.register(ModEntities.CANCEROUS_RODENT, CancerousRodentEntity.createAttribute());
	}
}