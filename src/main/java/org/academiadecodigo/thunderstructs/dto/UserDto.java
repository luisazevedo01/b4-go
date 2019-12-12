package org.academiadecodigo.thunderstructs.dto;

import org.academiadecodigo.thunderstructs.models.Club;

public class UserDto {

    private String username;
    private String name;
    private Club club;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
