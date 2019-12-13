package org.academiadecodigo.thunderstructs.api;

import org.academiadecodigo.thunderstructs.dto.UserDto;
import org.academiadecodigo.thunderstructs.dto.UserDtoToUser;
import org.academiadecodigo.thunderstructs.dto.UserToUserDto;
import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.models.User;
import org.academiadecodigo.thunderstructs.services.*;
import org.academiadecodigo.thunderstructs.utility.MusicGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/user")
public class RestUserController {

    private UserService userService;
    private RegisterService registerService;
    private UserToUserDto userToUserDto;
    private UserDtoToUser userDtoToUser;
    private LoginService loginService;
    private MusicGenreService musicGenreService;
    private ClubService clubService;

    @Autowired
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    @Autowired
    public void setMusicGenreService(MusicGenreService musicGenreService) {
        this.musicGenreService = musicGenreService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
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

    @Autowired
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<UserDto>> allUsers() {

        List<UserDto> userDtoList = new LinkedList<>();

        for (User user : userService.getUsersMap().values()) {
            userDtoList.add(userToUserDto.convert(user));
        }

        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        if (userService.getUserById(username) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userToUserDto.convert(userService.getUserById(username)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<User> registUser(@Valid @RequestBody User user, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!(registerService.registConfirmation(user))) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        registerService.registUser(user);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (loginService.verification(user.getUsername(), user.getPassword())) {
            return new ResponseEntity<>(userToUserDto.convert(user), HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/vote")
    public ResponseEntity<Map<User, MusicGenre>> vote(@Valid @RequestBody UserDto userDto, MusicGenre musicGenre, BindingResult bindingResult) {
        User user = userDtoToUser.convert(userDto);


        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (user.getMusicGenre() == musicGenre) {
            return new ResponseEntity<>(HttpStatus.CONTINUE);
        }
        user.setMusicGenre(musicGenre);
        musicGenreService.addVote(user, musicGenre);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/win-votation")
    public ResponseEntity<MusicGenre> getUser(@PathVariable int id) {

        if (clubService.getClub(id).getUserList().size() * 0.5 < musicGenreService.winnerValue()) {
            return new ResponseEntity<>(musicGenreService.changeGenre(id), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
