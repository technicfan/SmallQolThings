package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gg.norisk.ui.api.module.Module;
import gg.norisk.ui.modules.api.ModuleProvider;
import technicfan.smallqolthings.SmallQolThings;

@Mixin(ModuleProvider.Companion.class)
public class ModuleProvider$CompanionMixin {
    @Inject(method = "register(Lgg/norisk/ui/api/module/Module;)V", at = @At("HEAD"), cancellable = true)
    private void disableModule(Module module, CallbackInfo ci) {
        if (module == null) return;
        SmallQolThings.modules.add(module.getClass().getSimpleName());
        if (!SmallQolThings.canLoad(module.getClass().getSimpleName())) {
            SmallQolThings.info("Disabled " + module.getName());
            module.setEnabled(false);
            ci.cancel();
        }
    }
}
