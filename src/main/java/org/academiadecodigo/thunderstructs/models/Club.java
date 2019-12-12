package org.academiadecodigo.thunderstructs.models;

import org.academiadecodigo.thunderstructs.utility.MusicGenre;

import java.util.List;
import java.util.Map;

public class Club {

    private Integer id;
    private String name;
    private MusicGenre musicGenre;
    private Map<String, User> userList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MusicGenre getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(MusicGenre musicGenre) {
        this.musicGenre = musicGenre;
    }

    public Map<String, User> getUserList() {
        return userList;
    }

    public void setUserList(Map<String, User> userList) {
        this.userList = userList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
