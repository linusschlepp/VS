package a2;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class UserStore {


    private final HashMap<String, Socket> stringSocketHashMap;

    public UserStore() {
        this.stringSocketHashMap = new HashMap<>();
    }


    public void addUser(String user, Socket socket) {
        this.stringSocketHashMap.put(user, socket);
    }


    public boolean isDuplicate(String user) {
        return this.stringSocketHashMap.containsKey(user);
    }


    public String printUsers() {

        StringBuilder sb = new StringBuilder();

        this.stringSocketHashMap.keySet().forEach(s -> sb.append(s).append(" "));


        return sb.toString();
    }


    public List<Socket> getSockets(Socket socket){

        return this.stringSocketHashMap.values().stream()
                .filter(s -> s != socket).collect(Collectors.toList());

    }


    public String getUser(Socket socket) {

        String tempUser = "";


        for (String user : stringSocketHashMap.keySet()) {
            if (stringSocketHashMap.get(user) == socket)
                tempUser = user;
        }

        return tempUser;
    }

    public void deleteUser(String user){

        this.stringSocketHashMap.remove(user);
    }


}
