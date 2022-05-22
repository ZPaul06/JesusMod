package zpaul06.mods.jesusmod.init;

import zpaul06.mods.jesusmod.JesusMod;
import zpaul06.mods.jesusmod.common.entity.EntityFollower;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, JesusMod.MODID);

    public static final RegistryObject<EntityType<EntityFollower>> FOLLOWER = ENTITIES.register("follower",
            () -> EntityType.Builder.of(EntityFollower::new, MobCategory.CREATURE).setTrackingRange(22).sized(0.6F, 1.95F)
                    .build(new ResourceLocation(JesusMod.MODID, "follower").toString()));

}
