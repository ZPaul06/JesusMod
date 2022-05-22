package mutato115.mods.jesusmod.client.event;

import com.google.common.collect.ImmutableMap;
import mutato115.mods.jesusmod.JesusMod;
import mutato115.mods.jesusmod.client.renderer.FollowerRenderer;
import mutato115.mods.jesusmod.init.BlockRegistry;
import mutato115.mods.jesusmod.init.EntityRegistry;
import mutato115.mods.jesusmod.init.KeyInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = JesusMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {

    private ClientModEvents() {}

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CROSS.get(), RenderType.cutout());
        KeyInit.init();
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> builder = ImmutableMap.builder();
        builder.putAll(Minecraft.getInstance().getEntityRenderDispatcher().entityModels.roots);
        builder.put(FollowerRenderer.MLL, LayerDefinition.create(VillagerModel.createBodyModel(), 64, 64));

        Minecraft.getInstance().getEntityRenderDispatcher().entityModels.roots = builder.build();

        event.registerLayerDefinition(FollowerRenderer.MLL, FollowerRenderer::createModelLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.FOLLOWER.get(), FollowerRenderer::new);
    }

}
