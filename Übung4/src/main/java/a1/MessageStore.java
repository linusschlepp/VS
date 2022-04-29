package a1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MessageStore {


    private final HashMap<String, List<String>> usersMessageMap = new HashMap<>();


    public void addUser(String user) {
        synchronized (this) {
            this.usersMessageMap.put(user, new ArrayList<>());
        }

    }

    public List<String> getMessages(String user) {

        synchronized (this) {
            List<String> tempList = null;
            try {
                tempList = new ArrayList<>(this.usersMessageMap.get(user));
                this.usersMessageMap.get(user).clear();
                return tempList;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return tempList;
        }

    }


    public String sendMessage(String sender, String receiver, String message) {
        synchronized (this) {
            if (this.usersMessageMap.containsKey(receiver) && this.usersMessageMap.containsKey(sender)) {
                this.usersMessageMap.get(receiver).add(message);

                return "Message was sent to " + receiver;
            }

            return receiver + " or " + sender + " does not exist!";
        }

    }

    public String printUsers() {

        StringBuilder sb = new StringBuilder();

        this.usersMessageMap.keySet().forEach(sb::append);


        return sb.toString();

    }


    public boolean notDuplicate(String user) {
        return !this.usersMessageMap.containsKey(user);
    }


}
