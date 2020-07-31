package com.github.scotsguy.nowplaying.mixin;

import com.github.scotsguy.nowplaying.NowPlayingConfig;

import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.text.ITextComponent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Redirect(method = "playRecord(Lnet/minecraft/util/SoundEvent;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/MusicDiscItem;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/IngameGui;func_238451_a_(Lnet/minecraft/util/text/ITextComponent;)V"))
    private void modifyRecordPlayingOverlay(IngameGui ingameGUI, ITextComponent text) {
        if (NowPlayingConfig.Common.jukeboxStyle.get() == NowPlayingConfig.Style.Hotbar) {
            ingameGUI.func_238451_a_(text);
        }
    }
}
