package technicfan.smallqolthings.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Mixin(HumanoidMobRenderer.class)
public abstract class HumanoidMobRendererMixin {
    @Inject(method = "getEquipmentIfRenderable", at = @At("RETURN"), cancellable = true)
    private static void hideHelmet(LivingEntity entity, EquipmentSlot slot, CallbackInfoReturnable<ItemStack> cir) {
        // hide the helmet but keep the carved pumpkin with help of the max stack size
        if (EquipmentSlot.HEAD.equals(slot) && entity == Minecraft.getInstance().player && cir.getReturnValue().getMaxStackSize() == 1) {
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }
}
