package mutato115.mods.jesusmod.init;

import com.google.common.base.Supplier;
import mutato115.mods.jesusmod.JesusMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JesusMod.MODID);


    public static final RegistryObject<Item> TORAH = register("torah", () -> new Item(new Item.Properties().stacksTo(8)));


    private static <T extends Item>RegistryObject<T> register(final String name, final Supplier<T> item) {
        return ITEMS.register(name, item);
    }

}
