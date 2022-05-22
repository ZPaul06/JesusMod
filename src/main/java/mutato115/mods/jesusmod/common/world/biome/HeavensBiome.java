package mutato115.mods.jesusmod.common.world.biome;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.data.worldgen.SurfaceBuilders;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class HeavensBiome {

    public static Biome createHeavens() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.surfaceBuilder(SurfaceBuilders.GRASS);

        generationSettings.addFeature(GenerationStep.Decoration.LAKES, Features.LAKE_WATER);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_PLAIN);

        generationSettings.addStructureStart(StructureFeatures.VILLAGE_PLAINS);

        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.NONE)
                .depth(0.125F).scale(0.05F).temperature(0.5F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x8bc2cc).waterFogColor(0x9d9dd6)
                        .fogColor(0x9fe0ed).skyColor(0xfbff88)
                        .foliageColorOverride(0xc6ecb5).grassColorOverride(0xf8f8f8)
                        .backgroundMusic(Musics.CREDITS)
                        .ambientLoopSound(SoundEvents.AMBIENT_UNDERWATER_LOOP)
                        .build()).mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

}
