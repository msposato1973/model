package com.pulselive.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LeagueTableTest {

	private LeagueTable leagueTable;
	private List<Match> listMatches;
	
	@Before
    public void setup() {
        listMatches = buildListMatches();
        leagueTable = new LeagueTable(listMatches);
    }
	
	@Test
    public void createLeagueTableTest() {
		 assertNotNull(leagueTable);
    }
	
	@Test
    public void canRetrieveAGameLostShouldSucceed() {
        Match scoreHome = new Match(Team.WEST_BROM.getName(),Team.MAN_CITY.getName(), 2, 4);
        assertEquals(leagueTable.retrieveGameResult(scoreHome),"lost");
       
    }
	
	 @Test
	 public  void canRetrieveAGameWonShouldSucceed() {
	        Match scoreHome = new Match(Team.LIVERPOOL.getName(), Team.TOTTENHAM.getName(), 2, 1);
	        assertEquals(leagueTable.retrieveGameResult(scoreHome),"won");
	 }
	


    @Test
    public void canRetrieveAGameDrawnShouldSucceed() {
        Match scoreHome = new Match(Team.NEWCASTLE.getName(), Team.BRIGHTON.getName(), 2, 2);
        assertEquals(leagueTable.retrieveGameResult(scoreHome),"drawn");
    }

	
	private List<Match> buildListMatches() {
		return List.of(  
			 new Match(Team.LIVERPOOL.getName(), Team.TOTTENHAM.getName(), 2, 1),
	         new Match(Team.TOTTENHAM.getName(), Team.LIVERPOOL.getName(), 3, 3),
	         new Match(Team.LIVERPOOL.getName(), Team.WEST_BROM.getName(), 0, 2),
	         new Match(Team.WEST_BROM.getName(), Team.LIVERPOOL.getName(), 1, 0),
	         new Match(Team.TOTTENHAM.getName(), Team.ATLETICO_MADRID.getName(), 3, 0),
	         new Match(Team.ATLETICO_MADRID.getName(),Team.CHELSEA.getName(), 3, 3),
	         new Match(Team.TOTTENHAM.getName(), Team.WEST_BROM.getName(), 1, 1),
	         new Match(Team.WEST_BROM.getName(), Team.TOTTENHAM.getName(), 2, 2),
	         new Match(Team.LIVERPOOL.getName(), Team.ATLETICO_MADRID.getName(), 1, 0),
	         new Match(Team.ATLETICO_MADRID.getName(), Team.LIVERPOOL.getName(), 2, 4),
	         new Match(Team.WEST_BROM.getName(), Team.MAN_CITY.getName(), 2, 4),
	         new Match(Team.NEWCASTLE.getName(), Team.BRIGHTON.getName(), 2, 2),
	         new Match(Team.LIVERPOOL.getName(), Team.TOTTENHAM.getName(), 2, 0)
      );
	}
}
