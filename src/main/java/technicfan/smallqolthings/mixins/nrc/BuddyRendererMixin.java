package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import gg.norisk.cosmetics.v2.buddy.BuddyRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;

@Mixin(BuddyRenderer.class)
public class BuddyRendererMixin {
    @Inject(method = "submit", at = @At("HEAD"), cancellable = true)
    private static void cancel(CallbackInfo ci, @Local CameraRenderState camera) {
        if (camera.isFirstPerson) {
            ci.cancel();
        }
    }
}
