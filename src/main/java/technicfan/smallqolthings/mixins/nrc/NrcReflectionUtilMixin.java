package technicfan.smallqolthings.mixins.nrc;

import java.util.List;
import java.util.HashSet;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.compat.reflection.NrcReflectionUtil;

@Mixin(NrcReflectionUtil.class)
public class NrcReflectionUtilMixin {
    private static final HashSet<String> allowedInits = new HashSet<>(List.of(
        "gg.norisk.client.v2.screenshot.ScreenshotModule",
        "gg.norisk.client.v2.testingloop.TestingLoopBootstrap"
    ));

    @Inject(method = "tryInvokeNoArg", at = @At("HEAD"), cancellable = true)
    private static void cancelInit(String clazz, String method, CallbackInfo ci) {
        if (method != null && method.startsWith("init")) {
            if (!allowedInits.contains(clazz)) {
                ci.cancel();
            }
        }
    }
}
