package frictionless.frictionless.mixin;

import frictionless.frictionless.Frictionless;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import frictionless.frictionless.Frictionless;
import static net.minecraft.server.command.CommandManager.literal;


@Mixin(Block.class)
public abstract class slippymixin extends AbstractBlock implements ItemConvertible {


   public float slip = 1F;
   //def is 0.6
   public float vel = 1.05F;

    public slippymixin(Settings settings) {
        super(settings);
    }
    //def is 1



    @Inject(method = "getSlipperiness", at = @At(value = "HEAD"), cancellable = true)
    private void slippyFix(CallbackInfoReturnable<Float> cir) {
        if(Frictionless.isSlip){
            slip = 1F;
            cir.setReturnValue(slip);


        }else if(!Frictionless.isSlip){
            slip = this.slipperiness;

        }


    }



    @Inject(method = "getVelocityMultiplier", at = @At(value = "HEAD"), cancellable = true)
    private void velFix(CallbackInfoReturnable<Float> cir) {
        if(Frictionless.isSlip){
            vel = 1.05F;
            cir.setReturnValue(vel);

        }else if(!Frictionless.isSlip){
            vel = this.velocityMultiplier;
        }

    }



}


