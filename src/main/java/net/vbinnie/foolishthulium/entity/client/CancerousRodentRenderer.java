package net.vbinnie.foolishthulium.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vbinnie.foolishthulium.FoolishThulium;
import net.vbinnie.foolishthulium.entity.custom.CancerousRodentEntity;

public class CancerousRodentRenderer extends MobEntityRenderer<CancerousRodentEntity, CancerousRodentModel<CancerousRodentEntity>> {
    public CancerousRodentRenderer(EntityRendererFactory.Context context) {
        super(context, new CancerousRodentModel<>(context.getPart(CancerousRodentModel.CANCEROUS_RODENT)), 0.05f);
    }

    @Override
    public Identifier getTexture(CancerousRodentEntity entity) {
        return Identifier.of(FoolishThulium.MOD_ID, "textures/entity/cancerous/rodent/cancerousrodent.png");
    }

    @Override
    public void render(CancerousRodentEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(.5f, .5f, .5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
