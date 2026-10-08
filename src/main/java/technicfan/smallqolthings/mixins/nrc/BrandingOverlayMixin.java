package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.client.v2.modules.impl.BrandingOverlay;

@Mixin(BrandingOverlay.class)
public class BrandingOverlayMixin {
    @Inject(method = {"handleScreenRendering", "handleAboveInventoryRendering"}, at = @At("HEAD"), cancellable = true)
    private static void disableBranding(CallbackInfo ci) {
        ci.cancel();
    }
}
