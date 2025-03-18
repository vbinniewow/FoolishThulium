package net.vbinnie.foolishthulium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.vbinnie.foolishthulium.entity.ModEntities;
import net.vbinnie.foolishthulium.entity.client.CancerousRodentModel;
import net.vbinnie.foolishthulium.entity.client.CancerousRodentRenderer;
import net.vbinnie.foolishthulium.entity.custom.CancerousRodentEntity;
import net.vbinnie.foolishthulium.item.ModItems;

public class FoolishThuliumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        EntityModelLayerRegistry.registerModelLayer(CancerousRodentModel.CANCEROUS_RODENT, CancerousRodentModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CANCEROUS_RODENT, CancerousRodentRenderer::new);
    }
}
