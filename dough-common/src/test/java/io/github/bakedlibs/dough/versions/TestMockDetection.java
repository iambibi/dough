package io.github.bakedlibs.dough.versions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bukkit.Server;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import be.seeseemelk.mockbukkit.MockBukkit;

class TestMockDetection {

    @Test
    void testMockBukkit() {
        try {
            MockBukkit.mock();
        } catch (RuntimeException | LinkageError ex) {
            Assumptions.assumeTrue(false, "MockBukkit failed to initialize: " + ex);
            return;
        }

        try {
            assertTrue(MinecraftVersion.isMocked());
        } finally {
            MockBukkit.unmock();
        }
    }

    @Test
    void testNotMockBukkit() {
        /*
         * Technically also mocked.
         * But not MockBukkit ¯\_(ツ)_/¯
         */
        Server server = Mockito.mock(Server.class);

        assertFalse(MinecraftVersion.isMocked(server));
    }

}
