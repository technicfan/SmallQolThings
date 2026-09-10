package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.ui.keybinds.NrcKeybindsLayer;

@Mixin(NrcKeybindsLayer.class)
public class NrcKeybindsLayerMixin {
    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private static void hideKeybindings(CallbackInfo ci) {
        ci.cancel();
    }
}
