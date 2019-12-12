package org.academiadecodigo.thunderstructs.services;

import org.academiadecodigo.thunderstructs.models.Club;

import java.util.HashMap;
import java.util.Set;

public interface ClubService {

    /**
     * Get all clubs that exist registered on the webiste
     * @return
     */
    HashMap<Integer, Club> getClubs();

    /**
     * Returns the club with specified id (which has its info and users, etc...)
     * @return
     */
    Club getClub(Integer id);

    /**
     * After user clicks GO he is inserted into the clubs users
     * @param username
     */
    void addUserToClub(String username);


}
