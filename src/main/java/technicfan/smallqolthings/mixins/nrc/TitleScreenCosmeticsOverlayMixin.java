package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.cosmetics.v2.ui.screen.TitleScreenCosmeticsOverlay;

@Mixin(TitleScreenCosmeticsOverlay.class)
public class TitleScreenCosmeticsOverlayMixin {
    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void hideOverlay(CallbackInfo ci) {
        ci.cancel();
    }
}
