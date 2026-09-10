package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.client.bootstrap.ClientBootstrap;

@Mixin(ClientBootstrap.Companion.class)
public class ClientBootstrap$CompanionMixin {
    @Inject(method = "initBootstrap", at = @At(value = "INVOKE", target = "Lgg/norisk/client/v2/update/StandaloneUpdateChecker;init()V", shift = At.Shift.AFTER), cancellable = true)
    private void preventOptionChange(CallbackInfo ci) {
        ci.cancel();
    }
}
