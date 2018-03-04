package org.leagueoftests.app;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class CharacterNameTest {
    @Test
    public void createTest() {
        Champions champion = new Champion();
        assertNotNull(champion);
    }
}
