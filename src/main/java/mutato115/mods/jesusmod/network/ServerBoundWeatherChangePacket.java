package mutato115.mods.jesusmod.network;

import mutato115.mods.jesusmod.JesusMod;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ServerBoundWeatherChangePacket {

    public ServerBoundWeatherChangePacket() {}

    public ServerBoundWeatherChangePacket(FriendlyByteBuf buf) {}

    public void encode(FriendlyByteBuf buf) {}

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
           ServerLevel world =  ctx.get().getSender().getLevel();
           if (world.isRaining()) {
               world.setWeatherParameters(6000, 0, false, false);
               success.set(true);

               ServerPlayer player = ctx.get().getSender();
               Advancement adv = player.server.getAdvancements().getAdvancement(new ResourceLocation(JesusMod.MODID, "storm"));
               AdvancementProgress advancementprogress = player.getAdvancements().getOrStartProgress(adv);
               if (!advancementprogress.isDone()) {
                   for(String s : advancementprogress.getRemainingCriteria()) {
                       player.getAdvancements().award(adv, s);
                   }
               }
           }
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }

}
