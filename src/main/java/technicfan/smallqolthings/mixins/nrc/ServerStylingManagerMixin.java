package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.client.v2.serverstyling.ServerStylingManager;

@Mixin(ServerStylingManager.class)
public class ServerStylingManagerMixin {
    @Inject(method = "loadManifest", at = @At("HEAD"), cancellable = true)
    private void disableStyling(CallbackInfo ci) {
        ci.cancel();
    }
}
