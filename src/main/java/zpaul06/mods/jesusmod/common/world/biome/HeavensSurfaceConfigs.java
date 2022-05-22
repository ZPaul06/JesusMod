package zpaul06.mods.jesusmod.common.world.biome;

import zpaul06.mods.jesusmod.JesusMod;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class HeavensSurfaceConfigs {

    private static final SurfaceBuilderBaseConfiguration sbcHeavens = new SurfaceBuilderBaseConfiguration(Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> HEAVENS_SURFACE_BUILDER = register("heavens_surface", SurfaceBuilder.DEFAULT.configured(sbcHeavens));

    private static <T extends SurfaceBuilderBaseConfiguration> ConfiguredSurfaceBuilder<T> register(String name, ConfiguredSurfaceBuilder<T> surfaceBuilder) {
        return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(JesusMod.MODID, name), surfaceBuilder);
    }

}
