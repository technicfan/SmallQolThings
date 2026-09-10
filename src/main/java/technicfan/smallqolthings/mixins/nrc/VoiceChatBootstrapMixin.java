package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.voicechat.bootstrap.VoiceChatBootstrap;

@Mixin(VoiceChatBootstrap.class)
public class VoiceChatBootstrapMixin {
    @Inject(method = "onInit", at = @At("HEAD"), cancellable = true)
    private static void preventVoicechatInit(CallbackInfo ci) {
        ci.cancel();
    }
}
