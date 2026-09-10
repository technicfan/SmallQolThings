package technicfan.smallqolthings.mixins.nrc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import gg.norisk.ui.modules.v3.RightShiftMenuV3Screen;

@Mixin(RightShiftMenuV3Screen.class)
public class RightShiftMenuV3ScreenMixin {
    @Redirect(method = {"expandToFullView", "openSettingsForModule", "build"}, at = @At(value = "INVOKE", target = "Lgg/norisk/ui/modules/v3/RightShiftMenuV3Screen;getShowRightShiftMenu()Z"))
    private boolean skipSmallMenu(RightShiftMenuV3Screen screen) {
        return false;
    }
}
