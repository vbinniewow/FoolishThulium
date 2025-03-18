package net.vbinnie.foolishthulium.entity.client;


import com.ibm.icu.text.CaseMap;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.vbinnie.foolishthulium.FoolishThulium;
import net.vbinnie.foolishthulium.entity.custom.CancerousRodentEntity;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class CancerousRodentModel<T extends CancerousRodentEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer CANCEROUS_RODENT = new EntityModelLayer(Identifier.of(FoolishThulium.MOD_ID, "cancerous_rodent"), "main");
    private final ModelPart Body;

    private final ModelPart Head;

    public CancerousRodentModel(ModelPart root) {
        this.Body = root.getChild("Body");

        this.Head = this.Body.getChild("Head");

    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, -3.25F, -5.0F, 5.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 22.7F, 1.25F));

        ModelPartData TailBone1 = Body.addChild("TailBone1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -0.25F, 2.5F));

        ModelPartData Tail_r1 = TailBone1.addChild("Tail_r1", ModelPartBuilder.create().uv(12, 10).cuboid(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.1189F, 0.5842F, -1.1781F, 0.0F, 0.0F));

        ModelPartData Head = Body.addChild("Head", ModelPartBuilder.create().uv(0, 10).cuboid(-1.5F, -2.25F, -3.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(3, 24).cuboid(-0.5F, -0.25F, -3.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -0.75F, -5.0F));

        ModelPartData BackPaw1BONE = Body.addChild("BackPaw1BONE", ModelPartBuilder.create(), ModelTransform.pivot(0.6739F, 1.15F, 2.3827F));

        ModelPartData BackPaw1_r1 = BackPaw1BONE.addChild("BackPaw1_r1", ModelPartBuilder.create().uv(0, 20).cuboid(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.9761F, -0.85F, -1.6327F, 0.0F, -0.3927F, 0.0F));

        ModelPartData BackPaw2BONE = Body.addChild("BackPaw2BONE", ModelPartBuilder.create(), ModelTransform.pivot(-1.5761F, 1.15F, 2.3673F));

        ModelPartData BackPaw2_r1 = BackPaw2BONE.addChild("BackPaw2_r1", ModelPartBuilder.create().uv(12, 15).cuboid(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.8239F, -0.85F, -1.3673F, 0.0F, 0.3927F, 0.0F));

        ModelPartData FrontPaw2BONE = Body.addChild("FrontPaw2BONE", ModelPartBuilder.create(), ModelTransform.pivot(-1.8103F, 1.15F, -2.092F));

        ModelPartData BackPaw2_r2 = FrontPaw2BONE.addChild("BackPaw2_r2", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.4397F, -0.85F, -2.158F, 0.0F, 0.3927F, 0.0F));

        ModelPartData FrontPaw1BONE = Body.addChild("FrontPaw1BONE", ModelPartBuilder.create(), ModelTransform.pivot(0.6897F, 1.15F, -2.158F));

        ModelPartData FrontPaw1_r1 = FrontPaw1BONE.addChild("FrontPaw1_r1", ModelPartBuilder.create().uv(10, 19).cuboid(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.8103F, -0.85F, -2.342F, 0.0F, -0.3927F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(CancerousRodentEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(CancerousRodentAnimations.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, CancerousRodentAnimations.idle, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.Head.yaw = headYaw * 0.017453292F;
        this.Head.pitch = headPitch * 0.017453292F;
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        Body.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return Body;
    }

}