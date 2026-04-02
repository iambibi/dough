package io.github.bakedlibs.dough.skins.nms;

import com.destroystokyo.paper.profile.PlayerProfile;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;

import javax.annotation.ParametersAreNonnullByDefault;

public class PlayerHeadAdapterPaper implements PlayerHeadAdapter {

    @Override
    @ParametersAreNonnullByDefault
    public void setPlayerProfile(Block block, PlayerProfile profile, boolean sendBlockUpdate) {
        BlockState state = block.getState();
        if (!(state instanceof Skull skull)) return;

        skull.setProfile(ResolvableProfile.resolvableProfile(profile));

        skull.update(true, sendBlockUpdate);
    }
}