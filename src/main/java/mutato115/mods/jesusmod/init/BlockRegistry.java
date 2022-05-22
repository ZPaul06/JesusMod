package mutato115.mods.jesusmod.init;

import com.google.common.base.Supplier;
import mutato115.mods.jesusmod.JesusMod;
import mutato115.mods.jesusmod.common.block.BlockAltar;
import mutato115.mods.jesusmod.common.block.BlockCross;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JesusMod.MODID);
    public static final DeferredRegister<Item> ITEMS = ItemRegistry.ITEMS;


    public static final RegistryObject<Block> ALTAR = register("altar",
            () -> new BlockAltar(),
            object -> () -> new BlockItem(object.get(), new Item.Properties()));

    public static final RegistryObject<Block> CROSS = register("wooden_cross",
            () -> new BlockCross(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).dynamicShape()),
            object -> () -> new BlockItem(object.get(), new Item.Properties()));


    private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> obj = registerBlock(name, block);
        ITEMS.register(name, item.apply(obj));
        return obj;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> block) {
        return BLOCKS.register(name, block);
    }

}
