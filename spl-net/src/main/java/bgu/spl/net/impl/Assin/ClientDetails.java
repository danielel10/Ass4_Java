package bgu.spl.net.impl.Assin;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


public class ClientDetails {
    private String username;
    private String password;
    private String birthday;
    LinkedList<String> Following;
    LinkedList<String> Followers;
    LinkedList<MessageswaitingToBesent> messages;
    private int numOfPosts;
    private int age;


    public ClientDetails(String username, String password, String birthday) {
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        numOfPosts = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birth = LocalDate.parse(birthday,formatter);
        LocalDate localDate = LocalDate.now();
        age = Period.between(birth,localDate).getYears();
        Following = new LinkedList<>();
        Followers = new LinkedList<>();
        messages = new LinkedList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public LinkedList<String> getFollowing() {
        return Following;
    }

    public LinkedList<String> getFollowers() {
        return Followers;
    }

    public int getNumOfPosts() {
        return numOfPosts;
    }

    public int getAge() {
        return age;
    }

    public LinkedList<MessageswaitingToBesent> getMessages() {
        return messages;
    }

    public void addPost() {
        numOfPosts++;
    }

    public void AddUsernameToFollow(String username) {
        Following.add(username);
    }

    public void AddUsernameWhoFollowsYou(String username) {
        Followers.add(username);
    }

    public void addMessageTobeSent(MessageswaitingToBesent messageswaitingToBesent) {
        messages.add(messageswaitingToBesent);
    }
}
