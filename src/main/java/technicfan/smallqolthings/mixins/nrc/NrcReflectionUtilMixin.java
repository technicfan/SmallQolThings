package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.compat.reflection.NrcReflectionUtil;
import technicfan.smallqolthings.SmallQolThings;

@Mixin(NrcReflectionUtil.class)
public class NrcReflectionUtilMixin {
    @Inject(method = "tryInvokeNoArg", at = @At("HEAD"), cancellable = true)
    private static void cancelInit(String clazz, String method, CallbackInfo ci) {
        if (method != null && method.startsWith("init")) {
            SmallQolThings.inits.add(clazz);
            if (!SmallQolThings.canInit(clazz)) {
                SmallQolThings.info("Prevented " + method + "() of " + clazz.replaceAll(".*\\.", ""));
                ci.cancel();
            }
        }
    }
}
