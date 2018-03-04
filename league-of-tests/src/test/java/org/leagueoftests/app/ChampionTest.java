package org.leagueoftests.app;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class ChampionTest {
    @Test
    public void createTest() {
        Champions champion = new Champions();
        assertNotNull(champion);
    }
}
