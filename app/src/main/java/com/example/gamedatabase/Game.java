package com.example.gamedatabase;

public class Game {

    private int id;
    private String name;
    private int year;
    private int minPlayers;
    private int maxPlayers;
    private String genre;

    public Game(int id, String name, int year, int minPlayers, int maxPlayers, String genre) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.genre = genre;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return name + " (" + year + "), "
                + minPlayers + "-" + maxPlayers + " players, " + genre;
    }
}
