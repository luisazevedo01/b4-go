package org.academiadecodigo.thunderstructs.utility;

import org.academiadecodigo.thunderstructs.dto.UserDto;
import org.academiadecodigo.thunderstructs.dto.UserToUserDto;
import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ClubDB {

    private HashMap<Integer, Club> clubs = new HashMap<>();

    public ClubDB() {
        populate();
    }

    private void populate() {

        User user1 = new User();
        user1.setName("Jojo");
        user1.setUsername("jojo");
        user1.setPassword("1234");

        User user2 = new User();
        user2.setName("Fabiana");
        user2.setUsername("fabiana");
        user2.setPassword("1234");

        User user3 = new User();
        user3.setName("PDM");
        user3.setUsername("pdm");
        user3.setPassword("1234");

        UserToUserDto userToUserDto = new UserToUserDto();

        UserDto jojo = userToUserDto.convert(user1);
        UserDto fabiana = userToUserDto.convert(user2);
        UserDto pdm = userToUserDto.convert(user3);



        Club delmans = new Club();

        delmans.setId(1);
        delmans.setName("Delmans");
        delmans.setImage("https://media-cdn.tripadvisor.com/media/photo-s/18/5d/bd/26/logotipo-do-espaco.jpg");
        delmans.setDescription("Are you looking for music that you want to fake enjoyment to in a place where there are 40 people and 35 of them are dudes? Then look no further.");
        delmans.setMusicGenre(MusicGenre.PIMBA);
        delmans.setUserList(new HashMap<String, UserDto>());
        delmans.getUserList().put(jojo.getUsername(),jojo);
        delmans.getUserList().put(fabiana.getUsername(),fabiana);
        delmans.getUserList().put(pdm.getUsername(),pdm);

        Club havanna = new Club();
        havanna.setId(2);
        havanna.setName("Havanna");
        havanna.setImage("https://i.imgur.com/ru7XFNO.jpg");
        havanna.setDescription("Not to be confused with Savanna. We're also there, in case you don't like Ibiza.");
        havanna.setMusicGenre(MusicGenre.POP);
        havanna.setUserList(new HashMap<String, UserDto>());

        Club ibiza = new Club();
        ibiza.setId(3);
        ibiza.setName("Ibiza");
        ibiza.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwGmUbI66gYnTav5_VFEgBFPOM3knmXurY_FYP1bvdj-Jv35Wa&s");
        ibiza.setDescription("Ibiza the place where everyone takes pictures at. Take a picture in Ibiza, or a pill if you're feeling adventurous.");
        ibiza.setMusicGenre(MusicGenre.RAP);
        ibiza.setUserList(new HashMap<String, UserDto>());

        Club twins = new Club();
        twins.setId(4);
        twins.setName("Twins");
        twins.setImage("https://i.imgur.com/fNytNzA.jpg");
        twins.setDescription("We are Twins, there's just something very nice about having two of something. Come check us out and find out what we mean by yourself.");
        twins.setMusicGenre(MusicGenre.POP);
        twins.setUserList(new HashMap<String, UserDto>());

        clubs.put(delmans.getId(), delmans);
        clubs.put(havanna.getId(), havanna);
        clubs.put(ibiza.getId(), ibiza);
        clubs.put(twins.getId(), twins);

    }

    public HashMap<Integer, Club> getClubs() {
        return clubs;
    }

}
