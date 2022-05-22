package mutato115.mods.jesusmod.common.event;

import mutato115.mods.jesusmod.JesusMod;
import mutato115.mods.jesusmod.common.entity.EntityFollower;
import mutato115.mods.jesusmod.init.EntityRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JesusMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.FOLLOWER.get(), EntityFollower.createAttributes().build());
    }

}
