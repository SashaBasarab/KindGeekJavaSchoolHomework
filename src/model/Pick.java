package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Pick {

    private Team team;

    private ArrayList<Hero> heroes = new ArrayList<>();

    public Pick(Team team) {
        this.team = team;
    }

    public ArrayList<Hero> getPick(){
        heroes.addAll(team.getPlayers().stream().map(Player::getHero).sorted(Comparator.comparing(Hero::getRole)).collect(Collectors.toList()));
        return heroes;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

}
