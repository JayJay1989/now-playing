package com.github.scotsguy.nowplaying;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class NowPlayingConfig{

    public static class Common{
        public static ForgeConfigSpec.EnumValue<Style> musicStyle;
        public static ForgeConfigSpec.EnumValue<Style> jukeboxStyle;

        private static final ForgeConfigSpec.Builder COMMON = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec common_config;

        static {
            Common.init(COMMON);
            common_config = COMMON.build();
            NowPlaying.LOGGER.debug("Config for server has been initialized");
        }

        public static void init(ForgeConfigSpec.Builder builder)
        {
            builder.push("Notification Style");
            musicStyle = builder
                    .comment("How the 'Now Playing' notification should be displayed when music plays normally.")
                    .defineEnum("Music", Style.Toast);

            jukeboxStyle = builder
                    .comment("How the 'Now Playing' notification should be displayed when music plays from a jukebox.")
                    .defineEnum("Jukebox", Style.Hotbar);
            builder.pop();
        }

        public static void loadConfig(ForgeConfigSpec config, Path path) {
            NowPlaying.LOGGER.debug("Loading server config");
            final CommentedFileConfig file = CommentedFileConfig
                    .builder(path)
                    .sync()
                    .autosave()
                    .writingMode(WritingMode.REPLACE)
                    .build();
            NowPlaying.LOGGER.debug("Built server config");
            file.load();
            NowPlaying.LOGGER.debug("Loaded server config");
            config.setConfig(file);
        }
    }

    public enum Style {
        Hotbar,
        Toast,
        Disabled
    }
}
