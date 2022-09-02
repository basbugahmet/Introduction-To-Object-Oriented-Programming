import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class User{

    //Since the personal information are hosted, the attributes are created as private.

    private String name;
    private int userID;
    private String userName;
    private String password;
    private Date dateOfBirth; //dateOfBirth will be in Date type, we will arrange it by using setter method.
    private String schoolGraduated;
    private Boolean signIn = false;
    private Date LastLogInDate = null;
    private ArrayList<User> friendList = new ArrayList<User>();//friends of the user are stored in this arrayList.
    private ArrayList<User> blockedUsers = new ArrayList<User>(); //all users that are blocked by the user are stored here.
    private ArrayList<Post> posts = new ArrayList<Post>(); //all posts that are shared by the user are stored here.



    public User(String name, String userName, String password, String dateOfBirth, String schoolGraduated) throws ParseException {
        this.setName(name);
        this.setUserName(userName);
        this.setPassword(password);
        this.setDateOfBirth(dateOfBirth);
        this.setSchoolGraduated(schoolGraduated);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchoolGraduated() {
        return schoolGraduated;
    }

    public void setSchoolGraduated(String schoolGraduated) {
        this.schoolGraduated = schoolGraduated;
    }

    public Boolean getSignIn() {
        return signIn;
    }

    public void setSignIn(Boolean signIn) {
        this.signIn = signIn;
    }

    public Date getLastLogInDate() {
        return LastLogInDate;
    }

    public void setLastLogInDate(Date lastLogInDate) {
        LastLogInDate = lastLogInDate;
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(ArrayList<User> friendList) {
        this.friendList = friendList;
    }

    public ArrayList<User> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(ArrayList<User> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public String getDateOfBirth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(this.dateOfBirth);
        //We take it as "MM/dd/yyyy" however, in expected output we want it as "dd/MM/yyyy"
    }

    public void setDateOfBirth(String dateOfBirth) throws ParseException {
        this.dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(dateOfBirth);
    }

    @Override
    public String toString() {
        return  (getName()  + "   " + getUserName() + "    " + getPassword() + "    " + getDateOfBirth()+
                "   " + getSchoolGraduated() + "   " + getSignIn());
    }






}
