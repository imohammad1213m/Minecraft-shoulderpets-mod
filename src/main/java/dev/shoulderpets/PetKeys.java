package dev.shoulderpets;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

/** All of these show up in Options > Controls > Key Binds > "Shoulder Pets", so you can rebind them. */
public final class PetKeys {
    private static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath("shoulderpets", "main"));

    private static KeyMapping toggle, nextType, nextVariant, shoulder;

    private PetKeys() {}

    private static KeyMapping make(String name, int key) {
        return KeyBindingHelper.registerKeyBinding(
                new KeyMapping("key.shoulderpets." + name, InputConstants.Type.KEYSYM, key, CATEGORY));
    }

    public static void init() {
        toggle = make("toggle", GLFW.GLFW_KEY_H);
        nextType = make("next_type", GLFW.GLFW_KEY_J);
        nextVariant = make("next_variant", GLFW.GLFW_KEY_K);
        shoulder = make("shoulder", GLFW.GLFW_KEY_N);

        ClientTickEvents.END_CLIENT_TICK.register(PetKeys::tick);
    }

    private static void tick(Minecraft mc) {
        if (mc.player == null) return;
        PetConfig cfg = PetConfig.get();
        boolean changed = false;

        while (toggle.consumeClick()) { cfg.enabled = !cfg.enabled; changed = true; }
        while (nextType.consumeClick()) { cfg.type = cfg.type.next(); cfg.variant = 0; cfg.enabled = true; changed = true; }
        while (nextVariant.consumeClick()) { cfg.variant = (cfg.variant + 1) % cfg.type.variants.length; cfg.enabled = true; changed = true; }
        while (shoulder.consumeClick()) { cfg.shoulder = cfg.shoulder.next(); changed = true; }

        if (changed) {
            cfg.save();
            String msg = cfg.enabled
                    ? "Pet: " + cfg.type.displayName + " (" + cfg.currentVariant().name() + ") - " + cfg.shoulder.name()
                    : "Pet: OFF";
            mc.player.displayClientMessage(Component.literal(msg), true);
        }
    }
}
