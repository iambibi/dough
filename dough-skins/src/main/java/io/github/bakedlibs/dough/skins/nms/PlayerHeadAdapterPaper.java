package io.github.bakedlibs.dough.skins.nms;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import io.github.bakedlibs.dough.versions.MinecraftVersion;
import io.github.bakedlibs.dough.versions.UnknownServerVersionException;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.InvocationTargetException;

public class PlayerHeadAdapterPaper implements PlayerHeadAdapter {

    @Override
    @ParametersAreNonnullByDefault
    public void setGameProfile(Block block, GameProfile profile, boolean sendBlockUpdate) throws InvocationTargetException, IllegalAccessException, UnknownServerVersionException {
        BlockState state = block.getState();
        if (!(state instanceof Skull skull)) return;

        Property property = profile.properties().get("textures").stream().findFirst().orElse(null);
        if (property == null) return;

        PlayerProfile paperPlayerProfile = Bukkit.createProfile(profile.id(), profile.name());
        paperPlayerProfile.setProperty(new ProfileProperty(property.name(), property.value(), property.signature()));

        skull.setProfile(ResolvableProfile.resolvableProfile(paperPlayerProfile));

        if (sendBlockUpdate) {
            skull.update(true, false);
        }
    }
}
