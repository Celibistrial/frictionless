package frictionless.frictionless.mixin;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class slippymixin {
    @Inject(method = "getSlipperiness", at = @At(value = "HEAD"), cancellable = true)
    private void slippyFix(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(1F);
    }
    @Inject(method = "getVelocityMultiplier", at = @At(value = "HEAD"), cancellable = true)
    private void velFix(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(1.05F);
    }
}
