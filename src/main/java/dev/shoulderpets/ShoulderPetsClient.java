package dev.shoulderpets;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.model.player.PlayerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoulderPetsClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("shoulderpets");

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void onInitializeClient() {
        PetConfig.load();
        PetKeys.init();

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, renderer, helper, context) -> {
            if (renderer instanceof AvatarRenderer) {
                helper.register(new PetLayer(
                        (RenderLayerParent<AvatarRenderState, PlayerModel>) renderer,
                        context.getModelSet()));
            }
        });
    }
}
