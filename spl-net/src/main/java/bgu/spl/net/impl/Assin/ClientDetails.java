package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.bidi.Messages;
import bgu.spl.net.impl.Assin.Messages.RegisterMessage;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


public class ClientDetails {
    private int clientId;
    private String username;
    private String password;
    private String birthday;
    LinkedList<String> Following;
    LinkedList<String> Followers;
    LinkedList<Messages> messages;
    private int numOfPosts;
    private int age;
    boolean isregistered;
    boolean islogedin;


    public ClientDetails(int clientId) {
        this.clientId = clientId;
        numOfPosts = 0;
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

    public LinkedList<Messages> getMessages() {
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

    public void addMessageTobeSent(Messages messages) {
        this.messages.add(messages);
    }
    public boolean isIsregister(){
       return isregistered;
    }

    public boolean isIsloged(){
        return islogedin;
    }

    public void setIsregistered(boolean isregistered) {
        this.isregistered = isregistered;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setIslogedin(boolean islogedin) {
        this.islogedin = islogedin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birth = LocalDate.parse(birthday,formatter);
        LocalDate localDate = LocalDate.now();
        age = Period.between(birth,localDate).getYears();
    }
}
