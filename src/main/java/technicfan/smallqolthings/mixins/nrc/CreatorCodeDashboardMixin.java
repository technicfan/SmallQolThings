package technicfan.smallqolthings.mixins.nrc;

import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gg.norisk.compat.http.ApiErrorThrowable;
import gg.norisk.cosmetics.v2.creatorcode.CreatorCodeDashboard;

@Mixin(CreatorCodeDashboard.class)
public class CreatorCodeDashboardMixin {
private boolean noCreator = false;

@Inject(method = "fetchInfo", at = @At("HEAD"), cancellable = true)
private void cancel(CallbackInfoReturnable<Object> ci) {
    if (noCreator) {
        ci.setReturnValue(null);
    }
}

@Redirect(method = "fetchInfo", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V"))
private void preventErrorPrint(Logger logger, String msg, Throwable throwable) {
    logger.error(msg);
    if (throwable instanceof ApiErrorThrowable err && err.getHttpStatus() == 403) {
        noCreator = true;
    }
    }
}
