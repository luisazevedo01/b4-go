package org.academiadecodigo.thunderstructs.api;

import org.academiadecodigo.thunderstructs.dto.UserDto;
import org.academiadecodigo.thunderstructs.dto.UserDtoToUser;
import org.academiadecodigo.thunderstructs.dto.UserToUserDto;
import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.models.User;
import org.academiadecodigo.thunderstructs.services.ClubService;
import org.academiadecodigo.thunderstructs.services.UserService;
import org.academiadecodigo.thunderstructs.utility.MusicGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/club") // TODO: 2019-12-12 change to pathvariable userId
public class RestClubController {

    private ClubService clubService;
    private UserService userService;
    private UserDtoToUser userDtoToUser;
    private UserToUserDto userToUserDto;

    @Autowired
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    @Autowired
    public void setUserDtoToUser(UserDtoToUser userDtoToUser) {
        this.userDtoToUser = userDtoToUser;
    }

    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<Club>> listClubs() {

        HashMap<Integer, Club> clubsMap = clubService.getClubs();
        List<Club> clubList = new LinkedList<>();
        clubList.addAll(clubsMap.values());


        return new ResponseEntity<>(clubList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/genre/{genre}")
    public ResponseEntity<List<Club>> listClubsByGenre(@PathVariable MusicGenre genre) {

        List<Club> clubList = new LinkedList<>();
        clubList.addAll(clubService.getClubs().values());

        List<Club> clubListByGenre = new LinkedList<>();
        for (Club club : clubList) {
            if (club.getMusicGenre() == genre) {
                clubListByGenre.add(club);
            }
        }

        return new ResponseEntity<>(clubListByGenre, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Club> showClub(@PathVariable Integer id) {

        return new ResponseEntity<>(clubService.getClub(id), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{clubId}")
    public ResponseEntity<UserDto> goToClub(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, @PathVariable Integer clubId){

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userDtoToUser.convert(userDto);

        //checks if the user is already in the club, then remove him todo

        if (clubService.getClub(clubId).getUserList().get(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }

        String clubName = clubService.getClub(clubId).getName();

        //set the user converted from dto club to clubName
        user.setClub(clubName);

        //set the club of the user in DB to the clubName
        userService.getUserById(user.getUsername()).setClub(clubName);

        //add user to the clubs list of users
        clubService.addUserToClub(user, clubId);

        return new ResponseEntity<>(userToUserDto.convert(userService.getUserById(user.getUsername())), HttpStatus.ACCEPTED);
    }


}
