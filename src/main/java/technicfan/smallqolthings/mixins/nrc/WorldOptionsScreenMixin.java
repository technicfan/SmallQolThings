package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import gg.norisk.friends.ui.screen.HostedWorldScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout.RowHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.WorldOptionsScreen;
import net.minecraft.network.chat.Component;

@Mixin(WorldOptionsScreen.class)
public class WorldOptionsScreenMixin extends Screen {
    WorldOptionsScreenMixin() {
        super(Component.empty());
    }

    @Inject(method = "multiplayerOptions", at = @At("TAIL"))
    private void addHostWorldButton(CallbackInfo ci, @Local RowHelper helper) {
        helper.addChild(Button.builder(Component.literal("NRC Host World"), button -> this.minecraft.gui.setScreen(new HostedWorldScreen())).build(), 2);
    }
}
