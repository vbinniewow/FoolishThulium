package net.vbinnie.foolishthulium.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.vbinnie.foolishthulium.FoolishThulium;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> CANCER = registerStatusEffect("cancer",
            new CancerEffect(StatusEffectCategory.HARMFUL, 0x3ac02a));

    public static final RegistryEntry<StatusEffect> RADIOACTIVE = registerStatusEffect("radioactive",
            new RadioactiveEffect(StatusEffectCategory.HARMFUL, 0x49eb36));

    public static final RegistryEntry<StatusEffect> CANCERIMMUNITY = registerStatusEffect("cancerimmunity",
            new CancerImmunityEffect(StatusEffectCategory.BENEFICIAL, 0x00ff1b));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(FoolishThulium.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        FoolishThulium.LOGGER.info("Registering Mod Effects for " + FoolishThulium.MOD_ID);
    }
}
