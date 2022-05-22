package mutato115.mods.jesusmod.init;

import mutato115.mods.jesusmod.JesusMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public final class TagInit {

    public static final class JBlockTags {

    }

    public static final class JItemTags {
        public static final Tag.Named<Item> SHAREABLE_FOOD = reg("shareable_food");

        private static Tag.Named<Item> reg(String path) {
            return ItemTags.bind(new ResourceLocation(JesusMod.MODID, path).toString());
        }
    }

}
