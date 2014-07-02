package be.jforce.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class Team {
    String name;
    int goals = 0;
    Map<Outcome, Integer> outcomes = Maps.newHashMap();
    List<Match> matches = Lists.newArrayList();

    public Team(String name) {
        this.name = name;
        outcomes.put(Outcome.WON, 0);
        outcomes.put(Outcome.DRAW, 0);
        outcomes.put(Outcome.LOST, 0);
    }

    public void addMatch(Match match) {
        goals += match.goalsOf(this);
        matches.add(match);
        Outcome outcome = match.outcomeOf(this);
        outcomes.put(outcome, outcomes.get(outcome) + 1);
    }

    public String getName() {
        return name;
    }

    public int getGoals() {
        return goals;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public int getMatchesWon() {
        return outcomes.get(Outcome.WON);
    }

    public int getMatchesDraw() {
        return outcomes.get(Outcome.DRAW);
    }

    public int getMatchesLost() {
        return outcomes.get(Outcome.LOST);
    }

    @Override
    public String toString() {
        return name + " [goals=" + goals +
                ", matches=" + matches.size() + ']';
    }
}
