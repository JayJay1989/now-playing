package com.github.scotsguy.nowplaying;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.ToastGui;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class NowPlayingToast implements IToast {
    private final ITextComponent description;
    private final ItemStack itemStack;
    private boolean justUpdated;
    private long startTime;

    public NowPlayingToast(ITextComponent description) {
        this(description, new ItemStack(Items.MUSIC_DISC_CAT));
    }

    public NowPlayingToast(ITextComponent description, ItemStack itemStack) {
        this.description = description;
        this.itemStack = itemStack;
    }

    @Override
    public Visibility func_230444_a_(MatrixStack matrices, ToastGui manager, long startTime) {
        manager.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        manager.blit(matrices, 0, 0, 0, 32, this.func_230445_a_(), this.func_238540_d_());
        manager.getMinecraft().fontRenderer.func_238422_b_(matrices, new TranslationTextComponent("now_playing.toast.now_playing"), 30.0F, 7.0F, -11534256);
        manager.getMinecraft().fontRenderer.func_238422_b_(matrices, this.description, 30.0F, 18.0F, -16777216);
        matrices.push();
        manager.getMinecraft().getItemRenderer().renderItemIntoGUI(itemStack, 9, 8);
        matrices.pop();
        return startTime - this.startTime >= 5000L ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;
    }
}

