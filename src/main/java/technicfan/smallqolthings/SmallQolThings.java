package technicfan.smallqolthings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLevelEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public class SmallQolThings implements PreLaunchEntrypoint, ClientModInitializer {
    public static final String MOD_ID = "smallqolthings";
    private static final Logger LOGGER = LoggerFactory.getLogger(SmallQolThings.class);
    private static final KeyMapping.Category MOD_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, MOD_ID));
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".json").toFile();
    private static Config CONFIG = new Config();
    private static boolean hideOwnArmor = true;
    private static boolean hideOthersArmor = false;
    private static boolean hasNRC = false;
    public static List<String> inits = new ArrayList<>();
    public static List<String> modules = new ArrayList<>();
    public static String previouslySelectedEntity = null;

    @Override
    public void onPreLaunch() {
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            if (mod.getMetadata().getId().startsWith("nrc")) {
                hasNRC = true;
                loadConfig();
                break;
            }
        }
    }

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
        if (hasNRC) {
            ClientCommandRegistrationCallback.EVENT.register((d, r) -> d.register(
                ClientCommands.literal("nrcman")
                .then(ClientCommands.literal("inits").executes(c -> {
                    c.getSource().sendFeedback(Component.literal(String.join("\n", inits)));
                    return 1;
                }).then(ClientCommands.literal("allowed").executes(c -> {
                        c.getSource().sendFeedback(Component.literal(String.join("\n", CONFIG.allowedInits)));
                        return 1;
                    })).then(ClientCommands.literal("disabled").executes(c -> {
                        c.getSource().sendFeedback(Component.literal(String.join("\n", inits.stream().filter(s -> !CONFIG.allowedInits.contains(s)).toList())));
                        return 1;
                    })))
                .then(ClientCommands.literal("modules").executes(c -> {
                    c.getSource().sendFeedback(Component.literal(String.join("\n", modules)));
                    return 1;
                }).then(ClientCommands.literal("allowed").executes(c -> {
                        c.getSource().sendFeedback(Component.literal(String.join("\n", CONFIG.allowedModules)));
                        return 1;
                    })).then(ClientCommands.literal("disabled").executes(c -> {
                        c.getSource().sendFeedback(Component.literal(String.join("\n", modules.stream().filter(s -> !CONFIG.allowedModules.contains(s)).toList())));
                        return 1;
                    })))
            ));
        }
    }

    private static void loadConfig() {
		if (CONFIG_FILE.exists()) {
			try {
				try (FileReader reader = new FileReader(CONFIG_FILE)) {
					CONFIG = new Gson().fromJson(reader, Config.class);
				}
			} catch (IOException e) {
                LOGGER.warn("[QOL] Failed to load config - using default config");
            }
		}
    }

    public static boolean canInit(String name) {
        return CONFIG.allowedInits.contains(name);
    }

    public static boolean canLoad(String name) {
        return CONFIG.allowedModules.contains(name);
    }

    public static boolean hideArmor(Player player) {
        return player == Minecraft.getInstance().player ? hideOwnArmor : hideOthersArmor;
    }

    public static void info(String message) {
        LOGGER.info("[QOL] " + message);
    }
}
