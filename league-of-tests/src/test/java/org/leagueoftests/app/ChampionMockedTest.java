package org.leagueoftests.app;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.leagueoftests.repository.ChampionsRepository;
import org.leagueoftests.repository.ChampionsRepositoryFactory;
import org.leagueoftests.repository.ChampionsRepositoryImplementation;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.*;

import java.sql.SQLException;

import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.*;

import org.junit.Before;
@RunWith(MockitoJUnitRunner.class)
public class ChampionMockedTest {
    ChampionsRepository championsRepository;

    @Mock
    Connection connectionMock;

    @Mock
    PreparedStatement insertStatementMock;

    @Mock
    PreparedStatement selectByIDStatementMock;

    @Mock
    PreparedStatement selectAllStatementMock;


    @Mock
    PreparedStatement updateStatementMock;
    @Mock
    PreparedStatement deleteStatementMock;

  
    @Before
    public void init() throws SQLException{
        when(connectionMock.prepareStatement("INSERT INTO Champion (name, priceBE, priceRP) VALUES (?, ?, ?)")).thenReturn(insertStatementMock);
        when(connectionMock.prepareStatement("SELECT id, name, priceBE, priceRP FROM Champion")).thenReturn(selectAllStatementMock);
        when(connectionMock.prepareStatement("DELETE FROM Champion WHERE id = ?")).thenReturn(deleteStatementMock);
        when(connectionMock.prepareStatement("SELECT id, name, priceBE, priceRP FROM Champion WHERE id = ?")).thenReturn(selectByIDStatementMock);
        when(connectionMock.prepareStatement("UPDATE Champion SET name = ?, priceBE = ?, priceRP = ? WHERE id = ?")).thenReturn(updateStatementMock);
        championsRepository =new ChampionsRepositoryImplementation();
        championsRepository.setConnection(connectionMock);
        verify(connectionMock).prepareStatement("INSERT INTO Champion (name, priceBE, priceRP) VALUES (?, ?, ?)");
        verify(connectionMock).prepareStatement("SELECT id, name, priceBE, priceRP FROM Champion");
        verify(connectionMock).prepareStatement("DELETE FROM Champion WHERE id = ?");
        verify(connectionMock).prepareStatement("SELECT id, name, priceBE, priceRP FROM Champion WHERE id = ?");
        verify(connectionMock).prepareStatement("UPDATE Champion SET name = ?, priceBE = ?, priceRP = ? WHERE id = ?");
    }

    @Test
    public void InsertChampion() throws SQLException{
        when(insertStatementMock.executeUpdate()).thenReturn(1);
        Champions champion1=new Champions();
        champion1.setChampionName("Ahri");
        champion1.setChampionPriceBE(4800);
        champion1.setChampionPriceRP(750);
        assertEquals(1, championsRepository.add(champion1));
        verify(insertStatementMock,times(1)).setString(1, "Ahri");
        verify(insertStatementMock,times(1)).setInt(2, 4800);
        verify(insertStatementMock,times(1)).setInt(3, 750);
        verify(insertStatementMock).executeUpdate();
    }

    @Test
    public void GetChampionById() throws SQLException{
        AbstractResultSet mockedTestResult= mock(AbstractResultSet.class);
        when(mockedTestResult.next()).thenCallRealMethod();
        when(mockedTestResult.getInt("id")).thenCallRealMethod();
        when(mockedTestResult.getString("name")).thenCallRealMethod();
        when(mockedTestResult.getInt("priceBE")).thenCallRealMethod();
        when(mockedTestResult.getInt("priceRP")).thenCallRealMethod();
        when(selectByIDStatementMock.executeQuery()).thenReturn(mockedTestResult);

        assertNotNull(championsRepository.getById(1));
        verify(selectByIDStatementMock,times(1)).executeQuery();
        verify(mockedTestResult,times(1)).getInt("id");
        verify(mockedTestResult,times(1)).getString("name");
        verify(mockedTestResult,times(1)).getInt("priceBE");
        verify(mockedTestResult,times(1)).getInt("priceRP");
        verify(mockedTestResult,times(2)).next();
    }

    @Test
    public void GetAllChampions() throws SQLException{
        AbstractResultSet mockedTestResult= mock(AbstractResultSet.class);
        when(mockedTestResult.next()).thenCallRealMethod();
        when(mockedTestResult.getInt("id")).thenCallRealMethod();
        when(mockedTestResult.getString("name")).thenCallRealMethod();
        when(mockedTestResult.getInt("priceBE")).thenCallRealMethod();
        when(mockedTestResult.getInt("priceRP")).thenCallRealMethod();
        when(selectAllStatementMock.executeQuery()).thenReturn(mockedTestResult);

        assertEquals(1,championsRepository.getAll().size());
        verify(selectAllStatementMock,times(1)).executeQuery();
        verify(mockedTestResult,times(1)).getInt("id");
        verify(mockedTestResult,times(1)).getString("name");
        verify(mockedTestResult,times(1)).getInt("priceBE");
        verify(mockedTestResult,times(1)).getInt("priceRP");
        verify(mockedTestResult,times(2)).next();
    }

    @Test
    public void UpdateChampion() throws SQLException{
        when(updateStatementMock.executeUpdate()).thenReturn(1);

        Champions champion1=new Champions();
        champion1.setId(1);
        champion1.setChampionName("Ahri");
        champion1.setChampionPriceBE(4800);
        champion1.setChampionPriceRP(750);
        Champions champion2=new Champions();
        champion2.setId(1);
        
        assertEquals(1,championsRepository.update(champion2, champion1));
        verify(updateStatementMock).executeUpdate();
    }

    @Test
    public void DeleteChampion() throws SQLException{
        when(deleteStatementMock.executeUpdate()).thenReturn(1);
        Champions champion1=new Champions();
        champion1.setId(1);
        champion1.setChampionName("Ahri");
        champion1.setChampionPriceBE(4800);
        champion1.setChampionPriceRP(750);
        assertEquals(1, championsRepository.delete(champion1));
        verify(deleteStatementMock,times(1)).setInt(1, champion1.getId());
        verify(deleteStatementMock).executeUpdate();
    }
    abstract class AbstractResultSet implements ResultSet{
        int i = 0;

        @Override
        public boolean next() throws SQLException{
            if(i==1){
                return false;
            }
            i++;
            return true;
        }
        @Override
        public int getInt(String s)throws SQLException{
            return 1;
        }
        @Override
        public String getString(String s)throws SQLException{
            return "Ahri";
        }
    } 
    
}
