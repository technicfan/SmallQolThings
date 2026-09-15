package technicfan.smallqolthings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class SmallQolThings implements ClientModInitializer {
    public static final String MOD_ID = "smallqolthings";
    private static final Logger LOGGER = LoggerFactory.getLogger(SmallQolThings.class);
    private static final KeyMapping.Category MOD_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, MOD_ID));
    private static boolean showArmor = true;

    @Override
    public void onInitializeClient() {
        KeyMapping armorToggleBinding = KeyMappingHelper.registerKeyMapping(new KeyMapping("smallqolthings.key.toggle_armor", InputConstants.KEY_Y, MOD_CATEGORY));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (armorToggleBinding.consumeClick()) {
                showArmor = !showArmor;
            }
        });
    }

    public static boolean getShowArmor() {
        return showArmor;
    }

    public static void info(String message) {
        LOGGER.info("[QOL] " + message);
    }
}
