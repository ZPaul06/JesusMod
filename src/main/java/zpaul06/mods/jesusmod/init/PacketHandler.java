package zpaul06.mods.jesusmod.init;

import zpaul06.mods.jesusmod.JesusMod;
import zpaul06.mods.jesusmod.network.ServerBoundWeatherChangePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public final class PacketHandler {

    public static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(JesusMod.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);


    public static void init() {
        int index = 0;
        CHANNEL.messageBuilder(ServerBoundWeatherChangePacket.class, index++, NetworkDirection.PLAY_TO_SERVER).encoder(ServerBoundWeatherChangePacket::encode).decoder(ServerBoundWeatherChangePacket::new).consumer(ServerBoundWeatherChangePacket::handle).add();
    }
}
