package model;

import interfaces.MatchResult;

import java.util.ArrayList;

public class Team implements MatchResult {

    private String name;

    private String teamNationality;

    private ArrayList<Player> players;

    private Trainer trainer;

    private boolean isReadyForTheMatch = false;

    public Team(String name, String teamNationality, ArrayList<Player> players, Trainer trainer) {
        this.name = name;
        this.teamNationality = teamNationality;
        this.players = players;
        this.trainer = trainer;
    }

    public boolean teamIsReadyForTheMatch(){
        if (!isReadyForTheMatch){
            isReadyForTheMatch = true;
            System.out.println("Team " + this.name + " is ready for the match");
            return true;
        }
        System.out.println("Team " + this.name + " is already ready");
        return false;
    }

    public boolean checkTeamRegistration(){
        boolean flag = false;
        for (int i = 0; i < players.size(); i++) {
            if (this.players.get(i).isRegistered()){
                flag = true;
            } else{
                return false;
            }
        }
        return flag;
    }

    public int teamAverageTimeInHoursPerWeeek(){
        int result = 0;
        for (int i = 0; i < this.players.size(); i++) {
            result += this.players.get(i).getFreeTimeInHoursPerWeek();
        }
        result /= this.players.size();
        return result;
    }

    @Override
    public void wonGame() {
        try{
            for (Player nextPlayer : this.players) {
                nextPlayer.checkMmr();
            }
        } catch (Exception e) {
            System.out.println("Someone's mmr is not set");
            e.printStackTrace();
            return;
        }

        this.players.stream().forEach(player -> player.setMmr(player.getMmr() + Match.MMR_POINTS_PER_MATCH));
        System.out.println("Team " + this.name + " won the match, all winners get +25 mmr");
    }

    @Override
    public void lostGame() {

        try{
            for (Player nextPlayer : this.players) {
                nextPlayer.checkMmr();
            }
        } catch (Exception e) {
            System.out.println("Someone's mmr is not set");
            e.printStackTrace();
            return;
        }

        this.players.stream().forEach(player -> {
            player.setMmr(player.getMmr() - Match.MMR_POINTS_PER_MATCH);
        });
        System.out.println("Team " + this.name + " won the match, all winners get +25 mmr");
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamNationality() {
        return teamNationality;
    }

    public void setTeamNationality(String teamNationality) {
        this.teamNationality = teamNationality;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public boolean isReadyForTheMatch() {
        return isReadyForTheMatch;
    }

    public void setReadyForTheMatch(boolean readyForTheMatch) {
        isReadyForTheMatch = readyForTheMatch;
    }

}
