package com.github.scotsguy.nowplaying;

import com.github.scotsguy.nowplaying.mixin.MusicDiscItemAccessor;
import net.minecraft.client.audio.ISound;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class Util {
    public static TextComponent getSoundName(ISound instance) {
        if (instance.getSound().getSoundLocation().toString().startsWith("minecraft:music/")) {
            String id = "now_playing.sound." + instance.getSound().getSoundLocation().toString();
            return new TranslationTextComponent(id);
        }
        return null;
    }
    public static MusicDiscItem getDiscFromSound(ISound instance) {
        //SoundEvent event = new SoundEvent(new Identifier(instance.getId().toString().replace('.', '_')));
        for (SoundEvent event : MusicDiscItemAccessor.getDiscs().keySet()) {
            if (event.getRegistryName().equals(instance.getSoundLocation())) {
                return MusicDiscItem.getBySound(event);
            }
        }
        return null;
    }
}
