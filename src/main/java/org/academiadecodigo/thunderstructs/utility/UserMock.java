package org.academiadecodigo.thunderstructs.utility;

import org.academiadecodigo.thunderstructs.models.User;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserMock {

    private Map<String, User> usersMap;

    public UserMock() {
        usersMap = new HashMap<>();
        populate();
    }

    public Map<String, User> getUsersMap() {
        return usersMap;
    }

    private void populate(){
        User user1 = new User();
        user1.setName("Rafael");
        user1.setUsername("rafa");
        user1.setPassword("rafael");

        User user2 = new User();
        user2.setName("Karolis");
        user2.setUsername("karolis");
        user2.setPassword("1234");

        usersMap.put(user1.getUsername(), user1);
        usersMap.put(user2.getUsername(), user2);
    }
}
