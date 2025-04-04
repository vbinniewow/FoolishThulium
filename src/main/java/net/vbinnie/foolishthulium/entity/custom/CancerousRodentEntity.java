package net.vbinnie.foolishthulium.entity.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.entity.passive.AnimalEntity;

import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.vbinnie.foolishthulium.effect.ModEffects;
import net.vbinnie.foolishthulium.entity.ModEntities;

import net.vbinnie.foolishthulium.goal.FollowPlayerGoal;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class CancerousRodentEntity extends TameableEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public CancerousRodentEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new AnimalMateGoal(this, 0.5));

        this.goalSelector.add(2, new TemptGoal(this, 0.25, Ingredient.ofItems(Items.ROTTEN_FLESH), false));



        this.goalSelector.add(3, new GoToVillageGoal(this, 200));
        this.goalSelector.add(3, new FollowParentGoal(this, 0.25));

        this.targetSelector.add(3, new FollowPlayerGoal(this, 0.5, 30));
        this.goalSelector.add(4, new EatGrassGoal(this));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.25));

        this.goalSelector.add(5, new FollowOwnerGoal(this, 0.5, 2, 30));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4));

        this.goalSelector.add(6, new LookAroundGoal(this));
    }


    public static DefaultAttributeContainer.Builder createAttribute() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10)
                .add(EntityAttributes.GENERIC_JUMP_STRENGTH, 0.5)
                .add(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, 256)
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {

            this.setupAnimationStates();
        }
        else {
            applyEffectsToNearbyEntities();
            this.applyCureToCancerRealNotFake();

        }
    }



    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.isTamed()) {
            if (this.isOwner(player)) {
                if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
                    if (!this.getWorld().isClient()) {
                        this.eat(player, hand, itemStack);
                        FoodComponent foodComponent = (FoodComponent)itemStack.get(DataComponentTypes.FOOD);
                        this.heal(foodComponent != null ? (float)foodComponent.nutrition() : 1.0F);
                    }

                    return ActionResult.success(this.getWorld().isClient());
                }

                ActionResult actionResult = super.interactMob(player, hand);
                if (!actionResult.isAccepted()) {
                    this.setSitting(!this.isSitting());
                    return ActionResult.success(this.getWorld().isClient());
                }

                return actionResult;
            }
        } else if (this.isBreedingItem(itemStack)) {
            if (!this.getWorld().isClient()) {
                this.eat(player, hand, itemStack);
                this.tryTame(player);
                this.setPersistent();
            }

            return ActionResult.success(this.getWorld().isClient());
        }

        ActionResult actionResult = super.interactMob(player, hand);
        if (actionResult.isAccepted()) {
            this.setPersistent();
        }

        return actionResult;
    }

    private void tryTame(PlayerEntity player) {
        if (this.random.nextInt(3) == 0) {
            this.setOwner(player);
            this.setSitting(true);
            this.getWorld().sendEntityStatus(this, (byte)7);
        } else {
            this.getWorld().sendEntityStatus(this, (byte)6);
        }

    }



    private void applyCureToCancerRealNotFake() {
        addStatusEffect(new StatusEffectInstance(ModEffects.CANCERIMMUNITY, 9999, 0, false, false));
    }

    private void applyEffectsToNearbyEntities() {
        double range = 10.0;
        Box effectArea = new Box(this.getX() - range, this.getY() - range, this.getZ() - range,
                this.getX() + range, this.getY() + range, this.getZ() + range);


        List<LivingEntity> nearbyEntities = this.getWorld().getEntitiesByClass(LivingEntity.class, effectArea,
                entity -> entity != this && (entity instanceof LivingEntity livingEntity && !livingEntity.hasStatusEffect(ModEffects.CANCER) & !livingEntity.hasStatusEffect(ModEffects.CANCERIMMUNITY))
        );
        for (LivingEntity entity : nearbyEntities) {
            entity.addStatusEffect(new StatusEffectInstance(ModEffects.CANCER, 600, 3));
        }

        nearbyEntities = this.getWorld().getEntitiesByClass(LivingEntity.class, effectArea,
                entity -> entity != this && (entity instanceof LivingEntity livingEntity && !livingEntity.hasStatusEffect(ModEffects.RADIOACTIVE) & !livingEntity.hasStatusEffect(ModEffects.CANCERIMMUNITY))
        );
        for (LivingEntity entity : nearbyEntities) {
            entity.addStatusEffect(new StatusEffectInstance(ModEffects.RADIOACTIVE, 600, 2));
        }
    }




    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.ROTTEN_FLESH);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.CANCEROUS_RODENT.create(world);
    }
}
