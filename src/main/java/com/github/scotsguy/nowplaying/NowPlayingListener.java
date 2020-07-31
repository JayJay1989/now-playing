package com.github.scotsguy.nowplaying;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventListener;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NowPlayingListener implements ISoundEventListener {

    @Override
    public void onSoundPlayed(ISound sound, SoundEventAccessor accessor) {
        if (sound.getCategory() == SoundCategory.MUSIC) {
            ITextComponent name = Util.getSoundName(sound);
            if (name == null) return;
            if (NowPlayingConfig.Common.musicStyle.get() == NowPlayingConfig.Style.Toast) {
                Minecraft.getInstance().getToastManager().add(new NowPlayingToast(name));
            } else if (NowPlayingConfig.Common.musicStyle.get() == NowPlayingConfig.Style.Hotbar) {
                Minecraft.getInstance().inGameHud.setOverlayMessage(new TranslationTextComponent("record.nowPlaying", name), true);
            }
        } else if (sound.getCategory() == SoundCategory.RECORDS) {
            MusicDiscItem disc = Util.getDiscFromSound(sound);
            if (disc == null) return;
            if (NowPlayingConfig.Common.jukeboxStyle.get() != NowPlayingConfig.Style.Toast) return;
            Minecraft.getInstance().getToastManager().add(new NowPlayingToast(disc.getDescription(), new ItemStack(disc)));
        }
    }
}
