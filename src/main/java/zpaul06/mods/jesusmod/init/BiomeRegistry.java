package zpaul06.mods.jesusmod.init;

import zpaul06.mods.jesusmod.JesusMod;
import zpaul06.mods.jesusmod.common.world.biome.HeavensBiome;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeRegistry {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, JesusMod.MODID);

    public static final RegistryObject<Biome> HEAVENS = BIOMES.register("heavens", HeavensBiome::createHeavens);

}
