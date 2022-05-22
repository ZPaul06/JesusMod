package mutato115.mods.jesusmod.init;

import mutato115.mods.jesusmod.JesusMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class DimensionRegistry {

    public static final ResourceKey<Level> HEAVEN = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(JesusMod.MODID, "heaven"));

}
