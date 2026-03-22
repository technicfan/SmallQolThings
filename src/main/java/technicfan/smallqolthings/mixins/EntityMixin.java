package technicfan.smallqolthings.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.Entity;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(
        method = "updateInWaterStateAndDoWaterCurrentPushing",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;resetFallDistance()V",
            shift = At.Shift.AFTER
        )
    )
    private void dontBurnInWater(CallbackInfo ci) {
        ((Entity) (Object) this).clearFire();
    }
}
