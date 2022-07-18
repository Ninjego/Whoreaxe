package me.ninjego.whoreaxe.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.ninjego.whoreaxe.WhoreAxe;
import me.ninjego.whoreaxe.utils.MessageUtil;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile, @Nullable PlayerPublicKey publicKey) {
        super(world, profile, publicKey);
    }

    @Inject(method = "sendChatMessage(Ljava/lang/String;Lnet/minecraft/text/Text;)V", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, Text preview, CallbackInfo info) {
        if (message.startsWith(">")) {

            if(WhoreAxe.getInstance().getManagerProcessor().getCommandManager().dispatch(message.substring(1))) {
                MessageUtil.info("\2477Couldn't find \2475'" + message.substring(1) + "'\2477 as a command!");
            }

            info.cancel();
        }
    }

}
