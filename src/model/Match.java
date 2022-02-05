package model;

import exceptions.CantBeginMatchException;

import java.util.ArrayList;

public class Match {

    private Team team1;

    private Team team2;

    private ArrayList<Person> visitors;

    public static final int MMR_POINTS_PER_MATCH = 25;

    private boolean isStarted = false;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void beginMatch() throws CantBeginMatchException{
        if (team1.teamIsReadyForTheMatch() && team2.teamIsReadyForTheMatch()){
            System.out.println("The match has begun, first team is: " + team1.getName() + " and the second team is: " + team2.getName());
        }else{
            throw new CantBeginMatchException("Some team is not ready yet");
        }
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public ArrayList<Person> getVisitors() {
        return visitors;
    }

    public void setVisitors(ArrayList<Person> visitors) {
        this.visitors = visitors;
    }

}
