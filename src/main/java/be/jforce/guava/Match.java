package be.jforce.guava;

public class Match {
    Team team1;
    int goals1;
    Team team2;
    int goals2;

    public Match(Team team1, int goals1, Team team2, int goals2) {
        this.team1 = team1;
        this.goals1 = goals1;
        this.team2 = team2;
        this.goals2 = goals2;
        team1.addMatch(this);
        team2.addMatch(this);
    }

    public Team getTeam1() {
        return team1;
    }

    public int getGoals1() {
        return goals1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getGoals2() {
        return goals2;
    }

    public int goalsOf(Team team) {
        if (team == team1) {
            return goals1;
        } else if (team == team2) {
            return goals2;
        }
        return 0;
    }

    public Outcome outcomeOf(Team team) {
        int goals = goalsOf(team);
        int otherGoals = goalsOf(other(team));
        return goals > otherGoals ? Outcome.WON :
                (goals == otherGoals ? Outcome.DRAW : Outcome.LOST);
    }

    private Team other(Team team) {
        return team == team1 ? team2 : team1;
    }
}
