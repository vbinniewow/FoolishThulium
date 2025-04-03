package net.vbinnie.foolishthulium.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.world.World;

import java.util.List;

public class CancerImmunityEffect extends StatusEffect {

    public CancerImmunityEffect(StatusEffectCategory category, int color) {
        super(category, color);

    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();

        entity.getStatusEffect(ModEffects.CANCER); {
            entity.removeStatusEffect(ModEffects.CANCER);
        }
        entity.getStatusEffect(ModEffects.RADIOACTIVE); {
            entity.removeStatusEffect(ModEffects.RADIOACTIVE);
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}