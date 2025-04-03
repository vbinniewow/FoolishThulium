package net.vbinnie.foolishthulium.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.EnumSet;
import java.util.List;

public class FollowPlayerGoal extends Goal {
    private final PathAwareEntity entity;
    private ServerPlayerEntity targetPlayer;
    private final double speed;
    private final float followDistance;

    public FollowPlayerGoal(PathAwareEntity entity, double speed, float followDistance) {
        this.entity = entity;
        this.speed = speed;
        this.followDistance = followDistance;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        List<ServerPlayerEntity> players = entity.getWorld().getEntitiesByClass(ServerPlayerEntity.class,
                entity.getBoundingBox().expand(10), player -> true);

        if (!players.isEmpty()) {
            targetPlayer = players.getFirst();
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        return targetPlayer != null && targetPlayer.isAlive() && entity.squaredDistanceTo(targetPlayer) > followDistance * followDistance;
    }

    @Override
    public void tick() {
        if (targetPlayer != null) {
            entity.getNavigation().startMovingTo(targetPlayer, speed);
        }
    }

    @Override
    public void stop() {
        targetPlayer = null;
    }
}