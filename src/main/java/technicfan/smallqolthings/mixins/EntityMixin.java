package technicfan.smallqolthings.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.Entity;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private boolean wasTouchingWater;

    @Inject(method = "fireImmune", at = @At("HEAD"), cancellable = true)
    private void dontBurnInWater(CallbackInfoReturnable<Boolean> cir) {
        if (wasTouchingWater) cir.setReturnValue(true);
    }
}
