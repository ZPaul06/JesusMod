package zpaul06.mods.jesusmod.init;

import com.mojang.blaze3d.platform.InputConstants;
import zpaul06.mods.jesusmod.JesusMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.fmlclient.registry.ClientRegistry;

public class KeyInit {

    public static KeyMapping CHANGE_WEATHER;

    private KeyInit() {}

    public static void init() {
        CHANGE_WEATHER = register("change_weather", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_C);
    }

    private static KeyMapping register(String name, String category, int keycode) {
        final KeyMapping map = new KeyMapping("key." + JesusMod.MODID + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(map);
        return map;
    }

}
