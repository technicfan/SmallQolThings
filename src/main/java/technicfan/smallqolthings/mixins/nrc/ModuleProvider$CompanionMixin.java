package technicfan.smallqolthings.mixins.nrc;

import java.util.HashSet;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.ui.api.module.Module;
import gg.norisk.ui.modules.api.ModuleProvider;

@Mixin(ModuleProvider.Companion.class)
public class ModuleProvider$CompanionMixin {
    private static final HashSet<String> allowedModules = new HashSet<>(List.of(
        "ThemeModule",
        "NameTagsModule",
        "IconModule",
        "NRCPlusModule",
        "ScreenshotModule",
        "CosmeticsModule",
        "EmoteWheelModule",
        "McRealModule",
        "FriendsModule",
        "ProfilesModule",
        "WheelModule",
        "PingsModule"
    ));

    @Inject(method = "register(Lgg/norisk/ui/api/module/Module;)V", at = @At("HEAD"), cancellable = true)
    private void disableModule(Module module, CallbackInfo ci) {
        if (module == null || !allowedModules.contains(module.getClass().getSimpleName())) {
            module.setEnabled(false);
            ci.cancel();
        }
    }
}
