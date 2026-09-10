package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gg.norisk.client.v2.serverstyling.PromotedServerVisibility;

@Mixin(PromotedServerVisibility.class)
public class PromotedServerVisibilityMixin {
    @Inject(method = "isHidden", at = @At("HEAD"), cancellable = true)
    private static void hideHostAd(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
