package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.layouts.GridLayout.RowHelper;
import net.minecraft.network.chat.Component;

@Mixin (RowHelper.class)
public class RowHelperMixin {
    private static final Component msg = Component.literal("Host World");

    @Inject(method = "addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;I)Lnet/minecraft/client/gui/layouts/LayoutElement;", at = @At("HEAD"), cancellable = true)
    private void cancelHostButton(LayoutElement widget, int w, CallbackInfoReturnable<LayoutElement> cir) {
        if (widget instanceof Button button && button.getMessage().equals(msg)) {
            cir.setReturnValue(null);
        }
    }
}
