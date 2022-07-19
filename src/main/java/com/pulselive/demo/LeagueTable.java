package com.pulselive.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.pulselive.constant.ResultConstant;

public class LeagueTable {

	private final List<Match> matches;
	private List<LeagueTableEntry> tableEntryList = new ArrayList<>();
	 
    public LeagueTable(final List<Match> matches) {
    	 this.matches = matches;
    	 createLeagueTable();
    }

	public List<LeagueTableEntry> getTableEntries() {
		Comparator<LeagueTableEntry> entryComparator = Comparator.comparing(LeagueTableEntry::getPoints)
                .thenComparing(LeagueTableEntry::getGoalDifference)
                .thenComparing(LeagueTableEntry::getGoalsFor)
                .thenComparing(LeagueTableEntry::getTeamName);

		List<LeagueTableEntry> tableEntries = new ArrayList<>(tableEntryList);
		
		tableEntries.sort(entryComparator.reversed());
		
		return tableEntryList;

    }
	
	private void createLeagueTable() {
		matches.forEach(match -> {
            String gameResult = retrieveGameResult(match);
            if(!isTeamExistsInTable(match.getHomeTeam())){
                tableEntryList.add(
                		createLeagueEntry(match, gameResult)
                );
            } else {
                createLeagueEntry(match, gameResult);
            }
        });
		
	}

	private LeagueTableEntry createLeagueEntry(Match match, String gameResult) {
		int matchesPlayed = 1;
        int matchesWon = 0;
        int matchesLost = 0;
        int matchesDrawn = 0;
        int matchPoints = 0;

        if(gameResult.equals(ResultConstant.WON)){
            matchesWon = ResultConstant.DRAWN_POINTS;
            matchPoints = ResultConstant.W0N_POINTS;
        } else if(gameResult.equals(ResultConstant.LOST)){
            matchesLost = 1;
        } else{
            matchesDrawn = ResultConstant.W0N_POINTS;
            matchPoints = ResultConstant.W0N_POINTS;
        }

        return new LeagueTableEntry(match.getHomeTeam(), matchesPlayed, 
        		matchesWon, matchesDrawn,  matchesLost,  
        		match.getHomeScore(), match.getAwayScore(), 
        		(match.getHomeScore() - match.getAwayScore()),  
        		matchPoints
        );
    }

	private boolean isTeamExistsInTable(String homeTeam) {
		 return tableEntryList.stream().anyMatch(leagueTableEntry -> leagueTableEntry.getTeamName().equalsIgnoreCase(homeTeam));
	}


	public String retrieveGameResult(Match match) {
		String result;

        if(match.getHomeScore() > match.getAwayScore()){
            result = ResultConstant.WON;
        }else if(match.getHomeScore() < match.getAwayScore()){
            result = ResultConstant.LOST;
        }else {
            result = ResultConstant.DRAWN;
        }
        
        return result;
	}
}
