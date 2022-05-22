package zpaul06.mods.jesusmod.init;

import zpaul06.mods.jesusmod.JesusMod;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CfgStructureRegistry {

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_VILLAGER_MEETING = StructureRegistry.VILLAGER_MEETING.get().configured(NoneFeatureConfiguration.INSTANCE);

    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(JesusMod.MODID, "configured_villager_meeting"), CONFIGURED_VILLAGER_MEETING);
    }
}