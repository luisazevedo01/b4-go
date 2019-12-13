package org.academiadecodigo.thunderstructs.models;

import org.academiadecodigo.thunderstructs.dto.UserDto;
import org.academiadecodigo.thunderstructs.utility.MusicGenre;

import java.util.List;
import java.util.Map;

public class Club {

    private Integer id;
    private String name;
    private String image;
    private String description;
    private MusicGenre musicGenre;
    private Map<String, UserDto> userList;

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

    public Map<String, UserDto> getUserList() {
        return userList;
    }

    public void setUserList(Map<String, UserDto> userList) {
        this.userList = userList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
