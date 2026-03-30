package io.github.bakedlibs.dough.blocks;

import be.seeseemelk.mockbukkit.WorldMock;
import org.bukkit.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

class TestBlockPosition {

    private World newWorldOrSkip() {
        try {
            return new WorldMock();
        } catch (Throwable ex) {
            Assumptions.assumeTrue(false, "WorldMock unsupported for current Paper version: " + ex.getMessage());
            throw ex instanceof RuntimeException ? (RuntimeException) ex : new RuntimeException(ex); // unreachable
        }
    }

    @Test
    void testBlockPositions() {
        World world = newWorldOrSkip();
        int x = 57123, y = 286, z = 862;
        BlockPosition bp = new BlockPosition(world, x, y, z);

        Assertions.assertEquals(world, bp.getWorld());
        Assertions.assertEquals(x, bp.getX());
        Assertions.assertEquals(y, bp.getY());
        Assertions.assertEquals(z, bp.getZ());
    }

    @Test
    void testNegativeBlockPositions() {
        World world = newWorldOrSkip();
        int x = -57123, y = -38, z = -862;
        BlockPosition bp = new BlockPosition(world, x, y, z);

        Assertions.assertEquals(x, bp.getX());
        Assertions.assertEquals(y, bp.getY());
        Assertions.assertEquals(z, bp.getZ());
    }

}
