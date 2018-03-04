package org.leagueoftests.app;
 
import static org.junit.Assert.assertNotNull;

public class CharacterNameTest {
    @Test
    public void createTest() {
        Champions champion = new Champion();
        assertNotNull(champion.championName);
    }
}
