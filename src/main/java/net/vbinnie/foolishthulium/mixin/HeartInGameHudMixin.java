package net.vbinnie.foolishthulium.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.particle.EmotionParticle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.vbinnie.foolishthulium.FoolishThulium;
import net.vbinnie.foolishthulium.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class HeartInGameHudMixin {
    @Unique
    private static final Identifier CANCER_HEARTS = Identifier.of(FoolishThulium.MOD_ID, "textures/gui/cancer_hearts.png");

    @Inject(method = "drawHeart", at = @At("HEAD"), cancellable = true)
    private void thulium$drawCustomHeart(DrawContext context, InGameHud.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, CallbackInfo ci) {
        if (!blinking && type == InGameHud.HeartType.NORMAL && MinecraftClient.getInstance().cameraEntity instanceof PlayerEntity player && (player.hasStatusEffect(ModEffects.CANCER))) {
            Identifier textureId;
            if (player.hasStatusEffect(ModEffects.CANCER)) {
                textureId = CANCER_HEARTS;
            } else {
                return;
            }
            context.drawTexture(textureId, x, y, half ? 9 : 0, 0, 9, 9);
            ci.cancel();
        }
    }
}