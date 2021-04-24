package frictionless.frictionless.client;

import frictionless.frictionless.Frictionless;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class FrictionlessClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBinding messageKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("ToggleSlippery", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "SlippyMod"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (messageKey.wasPressed()) {
                if (Frictionless.isSlip) {
                    Frictionless.isSlip = false;
                    client.player.sendMessage(new LiteralText("Friction Added"), false);
                }
                else {
                    // When else block is executed, isSlip must be false, as it isn't true
                    // so the below if statement is redundant
                    if (!Frictionless.isSlip)
                    {
                        Frictionless.isSlip = true;
                        client.player.sendMessage(new LiteralText("Friction Removed"), false);
                    }
                }
                assert client.player != null;

            }
        });
    }
}
