package net.vbinnie.foolishthulium.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import net.vbinnie.foolishthulium.effect.ModEffects;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(MilkBucketItem.class)
public class MilkMixin {
    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    private void preventMilkEffectRemoval(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClient) {
            // Milk immune list
            List<@NotNull RegistryEntry<StatusEffect>> immuneEffects = List.of(ModEffects.CANCER, ModEffects.RADIOACTIVE);


            List<RegistryEntry<StatusEffect>> effectsToRemove = new ArrayList<>();

            for (StatusEffectInstance effect : user.getStatusEffects()) {
                if (!immuneEffects.contains(effect.getEffectType())) {
                    effectsToRemove.add(effect.getEffectType());
                }
            }

            // Remove the effects AFTER iteration
            for (RegistryEntry<StatusEffect> effect : effectsToRemove) {
                user.removeStatusEffect(effect);
            }

            // Cancel the default milk effect removal and return an empty bucket
            cir.setReturnValue(new ItemStack(Items.BUCKET));
        }
    }
}