package com.github.scotsguy.nowplaying;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("nowplaying")
public class NowPlaying{
    public static final Logger LOGGER = LogManager.getLogger();

    public NowPlaying(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, NowPlayingConfig.Common.common_config);
    }
}
