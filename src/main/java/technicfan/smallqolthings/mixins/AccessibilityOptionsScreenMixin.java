package technicfan.smallqolthings.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.gui.screens.options.AccessibilityOptionsScreen;

@Mixin(AccessibilityOptionsScreen.class)
public class AccessibilityOptionsScreenMixin {
    @Inject(method = "isMinecartOptionEnabled", at = @At("HEAD"), cancellable = true)
    // show the minecart rotation button even if the minecart experiment is not active
    private void alwaysShowMinecartButton(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
