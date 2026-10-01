package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.cosmetics.v2.render.overlay.impl.creeper.CreeperOverlayRenderer;
import gg.norisk.cosmetics.v2.render.overlay.impl.custom.CustomOverlayRenderer;
import gg.norisk.cosmetics.v2.render.overlay.impl.drinkingbird.DrinkingBirdOverlayRenderer;
import gg.norisk.cosmetics.v2.render.overlay.impl.enchantglint.EnchantGlintOverlayRenderer;
import gg.norisk.cosmetics.v2.render.overlay.impl.slime.SlimeOverlayRenderer;
import gg.norisk.cosmetics.v2.render.overlay.impl.witherarmor.WitherArmorOverlayRenderer;

@Mixin({
    CustomOverlayRenderer.class,
    DrinkingBirdOverlayRenderer.class,
    WitherArmorOverlayRenderer.class,
    EnchantGlintOverlayRenderer.class,
    SlimeOverlayRenderer.class,
    CreeperOverlayRenderer.class
})
public class NrcOverlayRendererMixin {
    @Inject(method = "renderFirstPerson", at = @At("HEAD"), cancellable = true)
    private void cancelFirstPersonOverlays(CallbackInfo ci) {
        ci.cancel();
    }
}
