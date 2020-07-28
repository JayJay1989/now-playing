package com.github.scotsguy.nowplaying;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class NowPlayingToast implements Toast {
    private final Text description;
    private boolean justUpdated;
    private long startTime;

    public NowPlayingToast(Text description) {
        this.description = description;
    }

    @Override
    public Visibility draw(MatrixStack matrices, ToastManager manager, long startTime) {
        manager.getGame().getTextureManager().bindTexture(TOASTS_TEX);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        manager.drawTexture(matrices, 0, 0, 0, 32, this.method_29049(), this.method_29050());
        manager.getGame().textRenderer.draw(matrices, new TranslatableText("now_playing.toast.now_playing"), 30.0F, 7.0F, -11534256);
        manager.getGame().textRenderer.draw(matrices, this.description, 30.0F, 18.0F, -16777216);
        ItemStack itemStack = new ItemStack(Items.MUSIC_DISC_CAT);
        matrices.push();
        manager.getGame().getItemRenderer().renderInGui(itemStack, 9, 8);
        matrices.pop();
        return startTime - this.startTime >= 5000L ? Toast.Visibility.HIDE : Toast.Visibility.SHOW;
    }
}

