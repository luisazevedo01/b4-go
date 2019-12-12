package org.academiadecodigo.thunderstructs.dto;

import org.academiadecodigo.thunderstructs.models.Club;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "/^[A-Za-z0-9 ]+$/")
    @Size(min = 3 , max = 50)
    private String username;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 3 , max = 50)
    private String name;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^\\[A-Za-z]*$/")
    @Size(min = 3 , max = 50)
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
