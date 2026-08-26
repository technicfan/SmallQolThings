package technicfan.smallqolthings.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Mixin(HumanoidMobRenderer.class)
public abstract class HumanoidMobRendererMixin {
    @Inject(method = "getEquipmentIfRenderable", at = @At("RETURN"), cancellable = true)
    private static void hideArmor(LivingEntity entity, EquipmentSlot slot, CallbackInfoReturnable<ItemStack> cir) {
        if (entity instanceof Player && slot.isArmor() && !cir.getReturnValue().is(Items.ELYTRA)) {
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }
}
