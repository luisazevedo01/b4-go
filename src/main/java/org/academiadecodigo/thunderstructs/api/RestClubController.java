package org.academiadecodigo.thunderstructs.api;

import org.academiadecodigo.thunderstructs.models.Club;
import org.academiadecodigo.thunderstructs.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path = "/club")
public class RestClubController {

    private ClubService clubService;

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<Club>> listClubs() {

        HashMap<Integer, Club> clubsMap = clubService.getClubs();
        List<Club> clubList = new LinkedList<>();
        clubList.addAll(clubsMap.values());


        return new ResponseEntity<>(clubList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/genre")
    public ResponseEntity<List<Club>> listClubsByGenre() {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Club> showClub(@PathVariable Integer id) {
        return null;
    }

    @Autowired
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }
}
