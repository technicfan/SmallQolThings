package technicfan.smallqolthings.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;

@Mixin(Minecart.class)
public abstract class MinecartMixin extends AbstractMinecart {
    private boolean useExperimentalMovement;
    private boolean potentialWrongDir;
    private boolean corrected;

    private MinecartMixin() {
        super(null, null);
    }

    @Redirect(
        method = "positionRider",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/vehicle/Minecart;useExperimentalMovement(Lnet/minecraft/world/level/Level;)Z"
        )
    )
    // rotate the camera even if the minecart experiment is not active
    private boolean alwaysRotateCamera(Level level) {
        this.useExperimentalMovement = Minecart.useExperimentalMovement(level);
        return true;
    }

    @Redirect(
        method = "positionRider",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;setYRot(F)V"
        )
    )
    private void setYRot(Player player, float yRot) {
        if (!useExperimentalMovement) {
            // for some reason it's `+` instead of `-`
            // with the old movement
            yRot = 2 * player.getYRot() - yRot;
            if (!corrected) {
                // with the old movement all minecarts have an initial y rotation of 0
                if (this.getYRot() == 0) {
                    // that changes when the minecart moves
                    BlockState blockState = this.level().getBlockState(this.blockPosition());
                    if (blockState.getBlock() instanceof BaseRailBlock rail) {
                        // when it's on the north south axis it changes to 90 or -90
                        // depending on the direction
                        if ((RailShape) blockState.getValue(rail.getShapeProperty()) == RailShape.NORTH_SOUTH) {
                            // i picked -90 as a guess ^^
                            this.setYRot(-90);
                            potentialWrongDir = true;
                        }
                        // if it's on the east west axis it stays 0 so everything is fine
                        corrected = true;
                    }
                } else {
                    corrected = true;
                }
            }
        }
        player.setYRot(yRot);
    }

    @Redirect(
        method = "tick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/vehicle/Minecart;getYRot()F"
        )
    )
    private float getYRot(Minecart minecart) {
        float yRot = minecart.getYRot();
        // when my guess was wrong and the real y rotation is 90
        // it would rotate the camera once
        if (potentialWrongDir && yRot < -90) {
            // it seems to get smaller until it reaches -270 so I wait for this to happen
            // (idk which ordinal is which direction but this somehow
            // works to only apply this to the correct direction lol)
            if (minecart.getDirection().ordinal() % 2 == 0) {
                if (yRot == -270) potentialWrongDir = false;
                yRot = -90;
            } else {
                potentialWrongDir = false;
            }
        }
        return yRot;
    }
}
