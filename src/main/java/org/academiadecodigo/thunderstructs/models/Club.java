package org.academiadecodigo.thunderstructs.models;

import org.academiadecodigo.thunderstructs.utility.MusicGenre;

import java.util.List;

public class Club {

    private int id;
    private String name;
    private MusicGenre musicGenres;
    private List<User> userList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MusicGenre getMusicGenres() {
        return musicGenres;
    }

    public void setMusicGenres(MusicGenre musicGenres) {
        this.musicGenres = musicGenres;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
