package zpaul06.mods.jesusmod.client.event;

import zpaul06.mods.jesusmod.JesusMod;
import zpaul06.mods.jesusmod.init.KeyInit;
import zpaul06.mods.jesusmod.init.PacketHandler;
import zpaul06.mods.jesusmod.network.ServerBoundWeatherChangePacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JesusMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEvents {

    private ClientForgeEvents() {}

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (KeyInit.CHANGE_WEATHER.isDown()) {
            PacketHandler.CHANNEL.sendToServer(new ServerBoundWeatherChangePacket());

            /*PlayerRenderer renderer = (PlayerRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(Minecraft.getInstance().player);
            PlayerModel model = renderer.getModel();

            model.rightArmPose = HumanoidModel.ArmPose.SPYGLASS;
            model.leftArmPose = HumanoidModel.ArmPose.SPYGLASS;

            MultiBufferSource buffer = Minecraft.getInstance().gameRenderer.renderBuffers.bufferSource();
            PoseStack ps = new PoseStack();
            AbstractClientPlayer player = Minecraft.getInstance().player;
            int unknownI = Minecraft.getInstance().getEntityRenderDispatcher().getPackedLightCoords(player, 1.0F);

            model.rightArm.render(ps, buffer.getBuffer(RenderType.entitySolid(player.getSkinTextureLocation())), unknownI, OverlayTexture.NO_OVERLAY);
            model.rightSleeve.render(ps, buffer.getBuffer(RenderType.entityTranslucent(player.getSkinTextureLocation())), unknownI, OverlayTexture.NO_OVERLAY);
            model.leftArm.render(ps, buffer.getBuffer(RenderType.entitySolid(player.getSkinTextureLocation())), unknownI, OverlayTexture.NO_OVERLAY);
            model.leftSleeve.render(ps, buffer.getBuffer(RenderType.entityTranslucent(player.getSkinTextureLocation())), unknownI, OverlayTexture.NO_OVERLAY);*/
        }
    }

}
