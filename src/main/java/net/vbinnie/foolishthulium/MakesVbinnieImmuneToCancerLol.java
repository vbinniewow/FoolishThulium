package net.vbinnie.foolishthulium;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vbinnie.foolishthulium.effect.ModEffects;

import java.util.Objects;
import java.util.UUID;

public class MakesVbinnieImmuneToCancerLol {


    public static void applyEffectToPlayer(ServerPlayerEntity serverPlayerEntity) {


        if (Objects.equals(serverPlayerEntity.getUuidAsString(), "5da9ead5-8bd5-4d1c-b1d9-df39fc930ff1")) {
            serverPlayerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.CANCERIMMUNITY, 999999999, 0, false, false));
        } else {
            System.out.println("Player ain't immune to cancer cuz he isn't vbinnie");
            System.out.println(serverPlayerEntity.getUuidAsString());
        }
    }
}