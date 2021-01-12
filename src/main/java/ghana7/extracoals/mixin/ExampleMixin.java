package ghana7.extracoals.mixin;

import net.minecraft.client.gui.screen.MainMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenuScreen.class)
public class ExampleMixin {
    @Inject(at=@At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        System.out.println("ExtraCoals mixins are set up!");
    }

}
