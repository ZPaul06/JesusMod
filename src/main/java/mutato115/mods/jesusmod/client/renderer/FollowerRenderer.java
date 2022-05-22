package mutato115.mods.jesusmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import mutato115.mods.jesusmod.JesusMod;
import mutato115.mods.jesusmod.common.entity.EntityFollower;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FollowerRenderer extends MobRenderer<EntityFollower, VillagerModel<EntityFollower>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/villager/type/desert.png");
    public static final ModelLayerLocation MLL = register("follower");

    public FollowerRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new VillagerModel<>(ctx.bakeLayer(FollowerRenderer.MLL)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, ctx.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFollower mob) {
        return TEXTURE;
    }

    public static LayerDefinition createModelLayer() {
        return LayerDefinition.create(VillagerModel.createBodyModel(), 64, 64);
    }

    protected void scale(WanderingTrader p_116375_, PoseStack p_116376_, float p_116377_) {
        float f = 0.9375F;
        p_116376_.scale(0.9375F, 0.9375F, 0.9375F);
    }

    private static ModelLayerLocation register(String name) {
        ModelLayerLocation modellayerlocation = new ModelLayerLocation(new ResourceLocation(JesusMod.MODID, name), "main");;
        if (!ModelLayers.ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }
}
