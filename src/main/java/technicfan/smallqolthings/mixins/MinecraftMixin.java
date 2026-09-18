package technicfan.smallqolthings.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import technicfan.smallqolthings.SmallQolThings;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    public Entity crosshairPickEntity;

    @Inject(method = "pick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/EntityHitResult;getEntity()Lnet/minecraft/world/entity/Entity;", shift = At.Shift.AFTER))
    private void captureEntity(CallbackInfo ci) {
        if (crosshairPickEntity != null)
            SmallQolThings.previouslySelectedEntity = crosshairPickEntity.getStringUUID();
    }
}
