package technicfan.smallqolthings;

import java.util.ArrayList;
import java.util.List;

public class Config {
    protected List<String> allowedInits;
    protected List<String> allowedModules;

    Config() {
        this.allowedInits = new ArrayList<>(List.of(
            "gg.norisk.client.v2.screenshot.ScreenshotModule",
            "gg.norisk.client.v2.testingloop.TestingLoopBootstrap"
        ));
        this.allowedModules = new ArrayList<>(List.of(
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
            "PingsModule",
            "TetrisModule",
            "PongModule"
        ));
    }
}
