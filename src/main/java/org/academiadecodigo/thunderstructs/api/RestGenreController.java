package org.academiadecodigo.thunderstructs.api;

import org.academiadecodigo.thunderstructs.utility.MusicGenre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/genre")
public class RestGenreController {

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<MusicGenre[]> showGenres() {
        return new ResponseEntity<>(MusicGenre.values(), HttpStatus.OK);
    }

}
