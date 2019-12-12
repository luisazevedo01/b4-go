package org.academiadecodigo.thunderstructs.controllers;

import org.academiadecodigo.thunderstructs.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClubController {

    private ClubService clubService;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public void showClubs() {
        clubService.getClubs();

    }

    @Autowired
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }
}
