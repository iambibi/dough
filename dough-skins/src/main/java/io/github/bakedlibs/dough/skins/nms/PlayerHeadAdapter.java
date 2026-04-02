package io.github.bakedlibs.dough.skins.nms;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.destroystokyo.paper.profile.PlayerProfile;
import io.github.bakedlibs.dough.versions.UnknownServerVersionException;
import org.bukkit.block.Block;

import com.mojang.authlib.GameProfile;

import io.github.bakedlibs.dough.common.DoughLogger;

public interface PlayerHeadAdapter {

    @ParametersAreNonnullByDefault
    void setPlayerProfile(Block block, PlayerProfile profile, boolean sendBlockUpdate) throws IllegalAccessException, InvocationTargetException, InstantiationException, UnknownServerVersionException;

    public static @Nullable PlayerHeadAdapter get() {
        try {
            return new PlayerHeadAdapterPaper();
        } catch (Exception x) {
            DoughLogger logger = new DoughLogger("skins");
            logger.log(Level.SEVERE, "Failed to detect skull nbt methods", x);
            return null;
        }

    }
}
