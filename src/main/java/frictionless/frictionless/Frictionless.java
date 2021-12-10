package frictionless.frictionless;
// getString(ctx, "string")
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
// word()
import static com.mojang.brigadier.arguments.StringArgumentType.word;
// literal("foo")
import static net.minecraft.server.command.CommandManager.literal;
// argument("bar", word())
import static net.minecraft.server.command.CommandManager.argument;
// Import everything
import static net.minecraft.server.command.CommandManager.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.server.command.CommandManager;



public class Frictionless implements ModInitializer{
    public static boolean isSlip;

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("toggleSlip").executes(context -> {
                if (isSlip) {
                    isSlip = false;
                }
                else {
                    // When else block is executed, isSlip must be false, as it isn't true
                    // so the below if statement is redundant
                    if (!isSlip)
                    {
                        isSlip = true;
                    }
                }
                return 1;
            }));
        });
    }
}
