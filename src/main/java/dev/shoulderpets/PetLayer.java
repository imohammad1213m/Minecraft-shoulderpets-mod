package dev.shoulderpets;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.animal.feline.CatModel;
import net.minecraft.client.model.animal.fox.FoxModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.model.monster.warden.WardenModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.CatRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.FoxRenderState;
import net.minecraft.client.renderer.entity.state.WardenRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

/**
 * Draws the pet on the local player's shoulder.
 * NOTE: this is the most version-sensitive file (render API changed a lot in 1.21.x).
 * If imports/signatures don't compile, fix them here only.
 */
public class PetLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
    private final FoxModel foxModel;
    private final CatModel catModel;
    private final WardenModel wardenModel;

    private final FoxRenderState foxState = new FoxRenderState();
    private final CatRenderState catState = new CatRenderState();
    private final WardenRenderState wardenState = new WardenRenderState();

    public PetLayer(RenderLayerParent<AvatarRenderState, PlayerModel> parent, EntityModelSet models) {
        super(parent);
        this.foxModel = new FoxModel(models.bakeLayer(ModelLayers.FOX));
        this.catModel = new CatModel(models.bakeLayer(ModelLayers.CAT));
        this.wardenModel = new WardenModel(models.bakeLayer(ModelLayers.WARDEN));
        this.foxState.isSitting = true;
        this.catState.isSitting = true;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight,
                       AvatarRenderState state, float yRot, float xRot) {
        PetConfig cfg = PetConfig.get();
        if (!cfg.enabled || state.isInvisible) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || state.id != mc.player.getId()) return; // only your own player

        boolean slim = state.skin != null && "SLIM".equals(state.skin.model().name());

        if (cfg.shoulder == PetConfig.Shoulder.LEFT || cfg.shoulder == PetConfig.Shoulder.BOTH) {
            drawPet(poseStack, collector, packedLight, state, cfg, true, slim);
        }
        if (cfg.shoulder == PetConfig.Shoulder.RIGHT || cfg.shoulder == PetConfig.Shoulder.BOTH) {
            drawPet(poseStack, collector, packedLight, state, cfg, false, slim);
        }
    }

    private void drawPet(PoseStack ps, SubmitNodeCollector collector, int light, AvatarRenderState st,
                         PetConfig cfg, boolean left, boolean slim) {
        PetType type = cfg.type;
        Identifier texture = cfg.currentVariant().texture();
        float s = type.scale;

        // Shoulder position (same coordinate space as vanilla's parrot-on-shoulder layer).
        // Slim arms are 3px wide instead of 4px, so the pet sits slightly closer to the body.
        float x = (slim ? 0.34f : 0.38f) * (left ? 1f : -1f);
        float y = (st.isCrouching ? -1.3f : -1.5f) + 1.5f * (1f - s); // keep the pet's feet at shoulder height

        ps.pushPose();
        ps.translate(x, y, 0f);
        ps.scale(s, s, s);

        switch (type) {
            case FOX -> {
                foxState.ageInTicks = st.ageInTicks;
                foxState.isSitting = true;
                draw(foxModel, foxState, ps, collector, light, st, texture);
            }
            case CAT -> {
                catState.ageInTicks = st.ageInTicks;
                catState.isSitting = true;
                draw(catModel, catState, ps, collector, light, st, texture);
            }
            case WARDEN -> {
                wardenState.ageInTicks = st.ageInTicks;
                draw(wardenModel, wardenState, ps, collector, light, st, texture);
            }
        }
        ps.popPose();
    }

    private static <S extends EntityRenderState> void draw(EntityModel<S> model, S modelState, PoseStack ps,
                                                           SubmitNodeCollector collector, int light,
                                                           AvatarRenderState owner, Identifier texture) {
        model.setupAnim(modelState);
        collector.submitModel(model, modelState, ps, model.renderType(texture), light,
                OverlayTexture.NO_OVERLAY, owner.outlineColor, null);
    }
}
