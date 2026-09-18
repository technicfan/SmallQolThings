package technicfan.smallqolthings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLevelEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public class SmallQolThings implements ClientModInitializer {
    public static final String MOD_ID = "smallqolthings";
    private static final Logger LOGGER = LoggerFactory.getLogger(SmallQolThings.class);
    private static final KeyMapping.Category MOD_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, MOD_ID));
    private static boolean hideOwnArmor = true;
    private static boolean hideOthersArmor = false;
    public static String previouslySelectedEntity = null;

    @Override
    public void onInitializeClient() {
        KeyMapping ownArmorToggleBinding = KeyMappingHelper.registerKeyMapping(new KeyMapping("smallqolthings.key.toggle_armor.own", InputConstants.UNKNOWN.getValue(), MOD_CATEGORY));
        KeyMapping othersArmorToggleBinding = KeyMappingHelper.registerKeyMapping(new KeyMapping("smallqolthings.key.toggle_armor.others", InputConstants.KEY_Y, MOD_CATEGORY));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ownArmorToggleBinding.consumeClick()) {
                hideOwnArmor = !hideOwnArmor;
            }
            while (othersArmorToggleBinding.consumeClick()) {
                hideOthersArmor = !hideOthersArmor;
            }
        });
        ClientLevelEvents.AFTER_CLIENT_LEVEL_CHANGE.register((minecraft, level) -> {
            previouslySelectedEntity = null;
        });
    }

    public static boolean hideArmor(Player player) {
        return player == Minecraft.getInstance().player ? hideOwnArmor : hideOthersArmor;
    }

    public static void info(String message) {
        LOGGER.info("[QOL] " + message);
    }
}
