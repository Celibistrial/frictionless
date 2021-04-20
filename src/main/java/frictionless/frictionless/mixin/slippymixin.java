package frictionless.frictionless.mixin;

import frictionless.frictionless.Frictionless;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import frictionless.frictionless.Frictionless;
import static net.minecraft.server.command.CommandManager.literal;


@Mixin(Block.class)
public class slippymixin {


   public float slip = 1F;
   //def is 0.6
   public float vel = 1.05F;
   //def is 1



    @Inject(method = "getSlipperiness", at = @At(value = "HEAD"), cancellable = true)
    private void slippyFix(CallbackInfoReturnable<Float> cir) {
        if(Frictionless.isSlip){
            slip = 1F;

        }else if(!Frictionless.isSlip){
            slip = 0.6F;
        }
        cir.setReturnValue(slip);

    }



    @Inject(method = "getVelocityMultiplier", at = @At(value = "HEAD"), cancellable = true)
    private void velFix(CallbackInfoReturnable<Float> cir) {
        if(Frictionless.isSlip){
            vel = 1.05F;

        }else if(!Frictionless.isSlip){
            vel =1.001F;
        }
        cir.setReturnValue(vel);
    }



}


