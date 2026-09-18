package technicfan.smallqolthings.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import technicfan.smallqolthings.SmallQolThings;

@Mixin(ClientSuggestionProvider.class)
public class ClientSuggestionProviderMixin {
    @Redirect(method = "getSelectedEntities", at = @At(value = "INVOKE", target = "Ljava/util/Collections;emptyList()Ljava/util/List;"))
    private static List<String> suggestPreviouslySelectedEntity() {
        if (SmallQolThings.previouslySelectedEntity != null) {
            return List.of(SmallQolThings.previouslySelectedEntity);
        } else {
            return List.of();
        }
    }
}
