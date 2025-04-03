package net.vbinnie.foolishthulium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.vbinnie.foolishthulium.entity.ModEntities;
import net.vbinnie.foolishthulium.entity.client.CancerousRodentModel;
import net.vbinnie.foolishthulium.entity.client.CancerousRodentRenderer;
import net.vbinnie.foolishthulium.entity.client.HamsterModel;
import net.vbinnie.foolishthulium.entity.client.HamsterRenderer;
import net.vbinnie.foolishthulium.entity.custom.CancerousRodentEntity;
import net.vbinnie.foolishthulium.item.ModItems;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Objects;
import java.util.UUID;

public class FoolishThuliumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        EntityModelLayerRegistry.registerModelLayer(CancerousRodentModel.CANCEROUS_RODENT, CancerousRodentModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CANCEROUS_RODENT, CancerousRodentRenderer::new);


        EntityModelLayerRegistry.registerModelLayer(HamsterModel.HAMSTER_MODEL, HamsterModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.HAMSTER, HamsterRenderer::new);
    }
}
