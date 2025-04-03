package net.vbinnie.foolishthulium.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;
import net.vbinnie.foolishthulium.damage_type.ModDamageTypes;


public class RadioactiveEffect extends StatusEffect {

    public RadioactiveEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();

        if (!world.isClient() && entity.age % 110 == 0) {
            entity.damage(ModDamageTypes.of(world, ModDamageTypes.CANCERDAMAGE), 2.0f * amplifier);
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
