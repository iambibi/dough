package io.github.bakedlibs.dough.skins;

import java.net.URL;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.destroystokyo.paper.profile.PlayerProfile;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

import io.github.bakedlibs.dough.versions.MinecraftVersion;
import io.github.bakedlibs.dough.versions.UnknownServerVersionException;

public final class CustomGameProfile {

    /**
     * The player name for this profile.
     * "CS-CoreLib" for historical reasons and backwards compatibility.
     */
    private static final String PLAYER_NAME = "CS-CoreLib";

    /**
     * The skin's property key.
     */
    private final PlayerProfile playerProfile;
    private final String texture;

    CustomGameProfile(@Nonnull UUID uuid, @Nullable String texture, @Nonnull URL url) {
        this.texture = texture;
        PlayerProfile playerProfile = Bukkit.createProfile(uuid, PLAYER_NAME);
        PlayerTextures playerTextures = playerProfile.getTextures();
        playerTextures.setSkin(url);
        playerProfile.setTextures(playerTextures);
        this.playerProfile = playerProfile;
    }

    ItemStack apply(@Nonnull ItemStack item) throws UnknownServerVersionException {
        if (MinecraftVersion.get().isAtLeast(MinecraftVersion.parse("1.21.3"))) {
            item.setData(DataComponentTypes.PROFILE, ResolvableProfile.resolvableProfile(this.playerProfile));
        }

        return item;
    }

    @Nonnull
    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    @Nonnull
    public UUID getId() {
        return playerProfile.getId();
    }

    @Nullable
    public String getName() {
        return playerProfile.getName();
    }

    /**
     * Get the base64 encoded texture from the underline GameProfile.
     *
     * @return the base64 encoded texture.
     */
    @Nullable
    public String getBase64Texture() {
        return this.texture;
    }
}
