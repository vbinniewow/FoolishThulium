package net.vbinnie.foolishthulium.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.vbinnie.foolishthulium.FoolishThulium;
import net.vbinnie.foolishthulium.entity.custom.CancerousRodentEntity;
import net.vbinnie.foolishthulium.entity.custom.HamsterEntity;

public class ModEntities {
    public static final EntityType<CancerousRodentEntity> CANCEROUS_RODENT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FoolishThulium.MOD_ID, "cancerous_rodent"),
            EntityType.Builder.create(CancerousRodentEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.75f, 0.25f).build());

    public static final EntityType<HamsterEntity> HAMSTER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FoolishThulium.MOD_ID, "hamster"),
            EntityType.Builder.create(HamsterEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.75f, 0.25f).build());

    public static void registerModEntities() {
        FoolishThulium.LOGGER.info("Registering Mod Entities for " + FoolishThulium.MOD_ID);
    }
}
