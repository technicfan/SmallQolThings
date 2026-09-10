package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.compat.util.DirectoryLinkManager;

@Mixin(DirectoryLinkManager.class)
public class DirectoryLinkManagerMixin {
    @Inject(method = "setupLink", at = @At("HEAD"), cancellable = true)
    private void preventLink(CallbackInfo ci) {
        ci.cancel();
    }
}
