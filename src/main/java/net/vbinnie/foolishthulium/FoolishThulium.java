package net.vbinnie.foolishthulium;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.vbinnie.foolishthulium.block.ModBlocks;
import net.vbinnie.foolishthulium.effect.ModEffects;
import net.vbinnie.foolishthulium.entity.ModEntities;
import net.vbinnie.foolishthulium.entity.custom.CancerousRodentEntity;
import net.vbinnie.foolishthulium.entity.custom.HamsterEntity;
import net.vbinnie.foolishthulium.item.ModItemGroups;
import net.vbinnie.foolishthulium.item.ModItems;
import net.vbinnie.foolishthulium.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.UUID;

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


		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayerEntity player = handler.getPlayer();

			 MakesVbinnieImmuneToCancerLol.applyEffectToPlayer(Objects.requireNonNull(player));
		});

		ServerPlayerEvents.AFTER_RESPAWN.register((player, sender, alive) -> {

			MakesVbinnieImmuneToCancerLol.applyEffectToPlayer(Objects.requireNonNull(player));
		});



		LOGGER.info("Hello Fabric world!");

		FabricDefaultAttributeRegistry.register(ModEntities.CANCEROUS_RODENT, CancerousRodentEntity.createAttribute());
		FabricDefaultAttributeRegistry.register(ModEntities.HAMSTER, HamsterEntity.createAttribute());
	}
}