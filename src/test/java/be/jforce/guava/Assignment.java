package be.jforce.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Assignment {

    @Test
    public void scored6GoalsOrMore() {
        Collection<Team> teams = Sets.newHashSet(); // TODO

        assertThat(teams.size(), is(11));
        assertTrue(teams.containsAll(Sets.newHashSet(NETHERLANDS, COLOMBIA, FRANCE, GERMANY, BRAZIL, ALGERIA, SWITZERLAND, ARGENTINA, CROATIA, BELGIUM, CHILE)));
    }

    @Test
    public void matchReportsForBelgium() {
        List<String> reports = Lists.newArrayList(); // TODO

        assertThat(reports, is(Arrays.asList(
                "Belgium-Algeria: 2-1",
                "Belgium-Russia: 1-0",
                "Korea-Belgium: 0-1",
                "Belgium-USA: 2-1"
        )));
    }

    @Test
    public void indexAllQualifiedTeamsByNumberOfMatchesWonSortedByName() {
        ListMultimap<Integer, Team> teamsByMatchesWon = ArrayListMultimap.create(); // TODO

        assertThat(teamsByMatchesWon.get(4), is(Arrays.asList(ARGENTINA, BELGIUM, COLOMBIA, NETHERLANDS)));
        assertThat(teamsByMatchesWon.get(3), is(Arrays.asList(FRANCE, GERMANY)));
        assertThat(teamsByMatchesWon.get(2), is(Arrays.asList(BRAZIL, CHILE, COSTA_RICA, MEXICO, SWITZERLAND, URUGUAY)));
        assertThat(teamsByMatchesWon.get(1), is(Arrays.asList(ALGERIA, GREECE, NIGERIA, USA)));
    }

    @Test
    public void sortAllQualifiedTeamsByNumberOfGoalsDescendingThenByNameAscending() {
        List<Team> sortedQualified = Lists.newArrayList(); // TODO

        assertThat(sortedQualified, is(Arrays.asList(
                NETHERLANDS, COLOMBIA, FRANCE, GERMANY, BRAZIL, ALGERIA, ARGENTINA, SWITZERLAND, BELGIUM, CHILE, COSTA_RICA, MEXICO, USA, URUGUAY, GREECE, NIGERIA
        )));
    }

    static final Team ALGERIA = new Team("Algeria");
    static final Team ARGENTINA = new Team("Argentina");
    static final Team AUSTRALIA = new Team("Australia");
    static final Team BELGIUM = new Team("Belgium");
    static final Team BOSNIA_AND_HERZEGOVINA = new Team("Bosnia and Herzegovina");
    static final Team BRAZIL = new Team("Brazil");
    static final Team CAMEROON = new Team("Cameroon");
    static final Team CHILE = new Team("Chile");
    static final Team COLOMBIA = new Team("Colombia");
    static final Team COSTA_RICA = new Team("Costa Rica");
    static final Team CROATIA = new Team("Croatia");
    static final Team COTE_DIVOIRE = new Team("Côte d'Ivoire");
    static final Team ECUADOR = new Team("Ecuador");
    static final Team ENGLAND = new Team("England");
    static final Team FRANCE = new Team("France");
    static final Team GERMANY = new Team("Germany");
    static final Team GHANA = new Team("Ghana");
    static final Team GREECE = new Team("Greece");
    static final Team HONDURAS = new Team("Honduras");
    static final Team IRAN = new Team("Iran");
    static final Team ITALY = new Team("Italy");
    static final Team JAPAN = new Team("Japan");
    static final Team KOREA = new Team("Korea");
    static final Team MEXICO = new Team("Mexico");
    static final Team NETHERLANDS = new Team("Netherlands");
    static final Team NIGERIA = new Team("Nigeria");
    static final Team PORTUGAL = new Team("Portugal");
    static final Team RUSSIA = new Team("Russia");
    static final Team SPAIN = new Team("Spain");
    static final Team SWITZERLAND = new Team("Switzerland");
    static final Team USA = new Team("USA");
    static final Team URUGUAY = new Team("Uruguay");

    static final Collection<Team> TEAMS = Sets.newHashSet(
            ALGERIA, ARGENTINA, AUSTRALIA, BELGIUM, BOSNIA_AND_HERZEGOVINA, BRAZIL, CAMEROON, CHILE, COLOMBIA, COSTA_RICA, CROATIA, COTE_DIVOIRE, ECUADOR,
            ENGLAND, FRANCE, GERMANY, GHANA, GREECE, HONDURAS, IRAN, ITALY, JAPAN, KOREA, MEXICO, NETHERLANDS, NIGERIA, PORTUGAL, RUSSIA, SPAIN, SWITZERLAND,
            USA, URUGUAY);

    static final Collection<Match> MATCHES = Lists.newArrayList(
            new Match(BRAZIL, 3, CROATIA, 1),
            new Match(MEXICO, 1, CAMEROON, 0),
            new Match(SPAIN, 1, NETHERLANDS, 5),
            new Match(CHILE, 3, AUSTRALIA, 1),
            new Match(COLOMBIA, 3, GREECE, 0),
            new Match(URUGUAY, 1, COSTA_RICA, 3),
            new Match(ENGLAND, 1, ITALY, 2),
            new Match(COTE_DIVOIRE, 2, JAPAN, 1),
            new Match(SWITZERLAND, 2, ECUADOR, 1),
            new Match(FRANCE, 3, HONDURAS, 0),
            new Match(ARGENTINA, 2, BOSNIA_AND_HERZEGOVINA, 1),
            new Match(GERMANY, 4, PORTUGAL, 0),
            new Match(IRAN, 0, NIGERIA, 0),
            new Match(GHANA, 1, USA, 2),
            new Match(BELGIUM, 2, ALGERIA, 1),
            new Match(BRAZIL, 0, MEXICO, 0),
            new Match(RUSSIA, 1, KOREA, 1),
            new Match(AUSTRALIA, 2, NETHERLANDS, 3),
            new Match(SPAIN, 0, CHILE, 2),
            new Match(CAMEROON, 0, CROATIA, 4),
            new Match(COLOMBIA, 2, COTE_DIVOIRE, 1),
            new Match(URUGUAY, 2, ENGLAND, 1),
            new Match(JAPAN, 0, GREECE, 0),
            new Match(ITALY, 0, COSTA_RICA, 1),
            new Match(SWITZERLAND, 2, FRANCE, 5),
            new Match(HONDURAS, 1, ECUADOR, 2),
            new Match(ARGENTINA, 1, IRAN, 0),
            new Match(GERMANY, 2, GHANA, 2),
            new Match(NIGERIA, 1, BOSNIA_AND_HERZEGOVINA, 0),
            new Match(BELGIUM, 1, RUSSIA, 0),
            new Match(KOREA, 2, ALGERIA, 4),
            new Match(USA, 2, PORTUGAL, 2),
            new Match(NETHERLANDS, 2, CHILE, 0),
            new Match(AUSTRALIA, 0, SPAIN, 3),
            new Match(CAMEROON, 1, BRAZIL, 4),
            new Match(CROATIA, 1, MEXICO, 3),
            new Match(ITALY, 0, URUGUAY, 1),
            new Match(COSTA_RICA, 0, ENGLAND, 0),
            new Match(JAPAN, 1, COLOMBIA, 4),
            new Match(GREECE, 2, COTE_DIVOIRE, 1),
            new Match(NIGERIA, 2, ARGENTINA, 3),
            new Match(BOSNIA_AND_HERZEGOVINA, 3, IRAN, 1),
            new Match(HONDURAS, 0, SWITZERLAND, 3),
            new Match(ECUADOR, 0, FRANCE, 0),
            new Match(PORTUGAL, 2, GHANA, 1),
            new Match(USA, 0, GERMANY, 1),
            new Match(KOREA, 0, BELGIUM, 1),
            new Match(ALGERIA, 1, RUSSIA, 1),
            new Match(BRAZIL, 1, CHILE, 1),
            new Match(COLOMBIA, 2, URUGUAY, 0),
            new Match(NETHERLANDS, 2, MEXICO, 1),
            new Match(COSTA_RICA, 1, GREECE, 1),
            new Match(FRANCE, 2, NIGERIA, 0),
            new Match(GERMANY, 2, ALGERIA, 1),
            new Match(ARGENTINA, 1, SWITZERLAND, 0),
            new Match(BELGIUM, 2, USA, 1)
    );

}
