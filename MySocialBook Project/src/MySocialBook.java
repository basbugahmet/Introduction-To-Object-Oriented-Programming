import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MySocialBook {

    static ArrayList<User> userRecords = new ArrayList<User>(); //list which we collect users that we create.
    static User currentLoggedInUser; //user who is logged in now.

    //Here, we defined them as static because, they belong to the class, not for instances of class.


    public static String[] splitterForTab(String line) { /*Words in each line are tab-separated. This method
                                                         will give us arrayList of them.*/

        return line.split("\t");
    }

    public static String[] splitterForColon(String line) {  /*In the friend tagging, tagged users separated by colons.
                                                            This method will give us arrayList of them.*/

        return line.split(":");

    }


    public static void main(String[] args) throws FileNotFoundException,ParseException  {

        if(args.length == 2) { //This program needs two arguments(users.txt, commands.txt)

      /* These are to read the txt files, create users from users.txt ;
        performing the necessary operations by reading each line from commands.txt*/

            Scanner scannerForUser = new Scanner(new File(args[0]));
            while (scannerForUser.hasNextLine()) {
                String line = scannerForUser.nextLine();
                String[] spliced = splitterForTab(line);
                User user = new User(spliced[0], spliced[1], spliced[2], spliced[3], spliced[4]);//creating user
                userRecords.add(user); //we add the user that we create to the arrayList which is called userRecords here.
            }

            Scanner scannerForCommands = new Scanner(new File(args[1]));
            while (scannerForCommands.hasNextLine()) {
                String line = scannerForCommands.nextLine();
                String[] spliced = splitterForTab(line); //We separate them according to tab blanks.
                System.out.println("Command: " + line);
                processor(spliced);//As we can see, we use processor method here to identify the command that we want to perform.
                System.out.println("--------------------------------------------------------------------------------------");
            }

        }else{
            System.out.println("You need to enter two arguments!");
        }
    }

    public static void processor(String[] array) throws ParseException { //it will take, String[] array, parameter which comes from above
        if (array[0].equals("ADDUSER")) {
            addUser(array);
            for (int i = 0; i < userRecords.size(); i++) { //When we are creating an user, we give him/her ID number here.
                userRecords.get(i).setUserID(i+1); //userRecords.get(i) = eachUser
            }
        } else if (array[0].equals("REMOVEUSER")) {
            removeUser(array);
        } else if (array[0].equals("SIGNIN")) {
            signIn(array);
        } else if (array[0].equals("SIGNOUT")) {
            signOut();
        } else if (array[0].equals("UPDATEPROFILE")) {
            updateProfile(array);
        } else if (array[0].equals("CHPASS")) {
            changePassword(array);
        } else if (array[0].equals("ADDFRIEND")) {
            addFriend(array);
        } else if (array[0].equals("REMOVEFRIEND")) {
            removeFriend(array);
        } else if (array[0].equals("BLOCK")) {
            block(array);
        } else if (array[0].equals("UNBLOCK")) {
            unblock(array);
        } else if (array[0].equals("LISTFRIENDS")) {
            listFriends();
        } else if (array[0].equals("LISTUSERS")) {
            listUsers();
        } else if (array[0].equals("SHOWBLOCKEDFRIENDS")) {
            showBlockedFriends();
        }else if (array[0].equals("SHOWBLOCKEDUSERS")) {
            showBlockedUsers();
        }else if (array[0].equals("ADDPOST-TEXT") || array[0].equals("ADDPOST-IMAGE") || array[0].equals("ADDPOST-VIDEO")) {
            addPost(array);
        }else if (array[0].equals("REMOVELASTPOST")) {
            removeLastPost();
        }else if (array[0].equals("SHOWPOSTS")) {
            showPosts(array);
        }
    }


    /*    The processor here allows us to call a function according to the command
        in the first index of the list we have obtained as a result of the previous allocation process.*/
    //Above, we can also use switch-case structure.


    public static void addUser(String[] array) throws ParseException {
        //To add a user to the system, these users will be stored in usersRecord arrayList which is static attribute of MySocialBook Class.\\
        User user = new User(array[1], array[2], array[3], array[4], array[5]);//creating a user
        //array[1] = name, array[2] = userName, array[3] = password, array[4] = dateOfBirth, array[5] = schoolGraduated
        userRecords.add(user); //adding to the arrayList which is static.
        System.out.println(array[1] + " has been successfully added");
    }


    public static void removeUser(String[] array) {
        //To remove a user according to the ID number of the user from the usersRecords arrayList.\\
        boolean userExistOrNot = false;//we will use it to determine user is registered to system or not.
        for(int i = 0; i< userRecords.size(); i++){
            User eachUser = userRecords.get(i);
            if(Integer.parseInt(array[1]) == eachUser.getUserID()){ //Integer.parseInt for String ==> Integer
                userExistOrNot = true;
                userRecords.remove(eachUser); //remove from userRecords list that we keep all users in the system.
                System.out.println("User has been successfully removed.");
            }
        }if(!userExistOrNot){ //if there is no such user like that in the system.
            System.out.println("No such user!");
        }
    }

    public static void signIn(String[] array) {
/*        To login to the system according to the username and password.
         Via this method, currentLoggedInUser will be this user and the signIn attribute of this user will be true.*/
        boolean userExistOrNot = false;//we will use it to determine user is registered to system or not.
        for (int i = 0, usersLogSize = userRecords.size(); i < usersLogSize; i++) {
            User user = userRecords.get(i);
            if (array[1].equals(user.getUserName())) {
                userExistOrNot = true;
                if (user.getPassword().equals(array[2])) {
                    user.setSignIn(true);//signIn attribute of user will be true
                    currentLoggedInUser = user;
                    currentLoggedInUser.setLastLogInDate(new Date());//current time
                    System.out.println("You have successfully signed in.");
                } else {
                    System.out.println("Invalid username or password! Please try again.");
                }
            }
        }
        if(!userExistOrNot){
            System.out.println("Invalid username or password! Please try again.");
        }
    }

    public static void signOut() {
        //if there is no user current logged in then program give us a feed-back.
        if(currentLoggedInUser != null){
            currentLoggedInUser.setSignIn(false);
            currentLoggedInUser = null;
            System.out.println("You have successfully signed out.");
        }else{
            System.out.println("You are already logged out!"); //optional feed-back
        }
    }

    public static void updateProfile(String[] array) throws ParseException {
        if (currentLoggedInUser != null) { //checking there is a current logged in user or not.
            currentLoggedInUser.setName(array[1]);
            currentLoggedInUser.setDateOfBirth(array[2]);
            currentLoggedInUser.setSchoolGraduated(array[3]);
            System.out.println("Your user profile has been successfully updated.");

        } else {//if not logged in.
            System.out.println("Error: Please sign in and try again.");
        }
    }

    public static void changePassword(String[] array) {
        if (currentLoggedInUser != null) { //checking there is a logged in user or not
            if (currentLoggedInUser.getPassword().equals(array[1])) {
                currentLoggedInUser.setPassword(array[2]);
                System.out.println("Password changed successfully.");//optional feed-back
            }else{//wrong password
                System.out.println("Password mismatch!");
            }
        } else {//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }

    public static void addFriend(String[] array) {
        boolean userExistOrNot = false;//we will use it to determine user is registered to system or not.
        if (currentLoggedInUser != null) { //checking there is a logged in user or not
            for (int i = 0; i < userRecords.size(); i++) {
                if (array[1].equals(userRecords.get(i).getUserName())) {
                    userExistOrNot = true;//the user is registered in the system
                    if (!array[1].equals(currentLoggedInUser.getUserName())) {
                        User friendWillBeAdded = userRecords.get(i);
                        if (!(currentLoggedInUser.getFriendList().contains(friendWillBeAdded))) {
                            currentLoggedInUser.getFriendList().add(friendWillBeAdded);
                            System.out.println(friendWillBeAdded.getUserName() + " has been successfully added to your friend list.");
                            break;
                        } else { //if the user is already in the FriendList array
                            System.out.println("This user is already in your friend list!");
                        }
                    }else{ // array[1].equals(currentLoggedInUser.getUserName()) if this condition is true;
                        System.out.println("You cannot add yourself as a friend."); //optional feed-back
                    }
                }
            }
            if (!userExistOrNot) { //if there is no such registered user in the system
                System.out.println("No such user!");
            }
        } else {//if no logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }

    public static void removeFriend(String[] array) {
        boolean userExistOrNot = false;
        if (currentLoggedInUser != null) { //checking there is a logged in user or not
            for (int i = 0; i < userRecords.size(); i++) {
                if (array[1].equals(userRecords.get(i).getUserName())) {
                    userExistOrNot = true;
                    User friendWillBeRemoved = userRecords.get(i);
                    if ((currentLoggedInUser.getFriendList().contains(friendWillBeRemoved))) { //checking he/she is friend or not
                        currentLoggedInUser.getFriendList().remove(friendWillBeRemoved);
                        System.out.println(friendWillBeRemoved.getUserName() + " has been successfully removed from your friend list.");
                        break; //if we find the user that we want to remove, we do not have to check other users.
                    } else { //user is not in the friendList of currentLoggedInUser
                        System.out.println("No such friend!");
                    }
                }
            }
            if (!userExistOrNot) { //if not registered in the system
                System.out.println("No such friend!, in fact no such user in the system.");
            }
        } else {//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }

    }


    public static void block(String[] array) {
        boolean userExistOrNot = false;
        if (currentLoggedInUser != null) {//checking there is a logged in user or not
            for (int i = 0; i < userRecords.size(); i++) {
                if (array[1].equals(userRecords.get(i).getUserName())) {
                    userExistOrNot = true;//the user is registered in the system
                    User userWillBeBlocked = userRecords.get(i);
                    currentLoggedInUser.getBlockedUsers().add(userWillBeBlocked);//adding blockedUser list which is attribute of User class.
                    System.out.println(userWillBeBlocked.getUserName() + " has been successfully blocked.");
                }
            }
            if (!userExistOrNot) {//if not registered in the system
                System.out.println("No such user!");
            }
        } else {//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }


    public static void unblock(String[] array) {
        boolean userExistOrNot = false;
        if (currentLoggedInUser != null) {//checking there is a logged in user or not
            for (int i = 0; i < userRecords.size(); i++) {
                if (array[1].equals(userRecords.get(i).getUserName())) {
                    userExistOrNot = true;//the user is registered in the system
                    User userWillBeUnBlocked = userRecords.get(i);
                    if (currentLoggedInUser.getBlockedUsers().contains(userWillBeUnBlocked)) {
                        currentLoggedInUser.getBlockedUsers().remove(userWillBeUnBlocked);
                        System.out.println(userWillBeUnBlocked.getUserName() + " has been successfully unblocked.");
                    } else {//checking whether user is blocked before or not
                        System.out.println("No such user in your blocked-user list!");
                    }
                }
            }
            if (!userExistOrNot) {//if not registered in the system
                System.out.println("No such user in your blocked-user list!, in fact no such user in the system.");
            }
        } else {//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }


    public static void listFriends() {
        if (currentLoggedInUser != null) {//checking there is a logged in user or not
            if (currentLoggedInUser.getFriendList().size() != 0) { //checking friend list is empty or not
                for (int i = 0; i < currentLoggedInUser.getFriendList().size(); i++) {
                    System.out.println("Name: " + currentLoggedInUser.getFriendList().get(i).getName());
                    System.out.println("Username: " + currentLoggedInUser.getFriendList().get(i).getUserName());
                    System.out.println("Date of Birth: " + currentLoggedInUser.getFriendList().get(i).getDateOfBirth());
                    //in users.txt, date is like that MM/dd/yyyy, in expected output, dd/MM/yyyy
                    System.out.println("School: " + currentLoggedInUser.getFriendList().get(i).getSchoolGraduated());
                    System.out.println("----------------------------");
                }
            }else{//if the friendList is empty
                System.out.println("You have not added any friend yet!");
            }
        } else {//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }

    public static void listUsers() {
        if (currentLoggedInUser != null) {//checking there is a logged in user or not
            for (int i = 0; i < userRecords.size(); i++) {
                System.out.println("Name: " + userRecords.get(i).getName());
                System.out.println("Username: " + userRecords.get(i).getUserName());
                System.out.println("Date of Birth: " + userRecords.get(i).getDateOfBirth());
                //in users.txt, date is like that MM/dd/yyyy, in expected output, dd/MM/yyyy
                System.out.println("School: " + userRecords.get(i).getSchoolGraduated());
                System.out.println("----------------------------");
            }
        } else {//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }

    public static void showBlockedFriends() {
        boolean blockedFriendCheck = false;
        if (currentLoggedInUser != null) {//checking there is a logged in user or not
            if (currentLoggedInUser.getBlockedUsers().size() != 0) {//checking there is blocked user or not in the list
                for (int i = 0; i < currentLoggedInUser.getBlockedUsers().size(); i++) {
                    User tempUser = currentLoggedInUser.getBlockedUsers().get(i);
                    if (currentLoggedInUser.getFriendList().contains(tempUser)) {
                        blockedFriendCheck = true;
                        System.out.println("Name: " + tempUser.getName());
                        System.out.println("Username: " + tempUser.getUserName());
                        System.out.println("Date of Birth: " + tempUser.getDateOfBirth());
                        //in users.txt, date is like that MM/dd/yyyy, in expected output, dd/MM/yyyy
                        System.out.println("School: " + tempUser.getSchoolGraduated());
                        System.out.println("----------------------------");
                    }
                }
                if (!blockedFriendCheck) {
                    System.out.println("You haven’t blocked any friend yet!");
                }
            } else {
                System.out.println("You haven’t blocked any friend yet!");
            }
        } else {//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }


    public static void showBlockedUsers() {
        if (currentLoggedInUser != null) {//checking there is a logged in user or not
            if (currentLoggedInUser.getBlockedUsers().size() != 0) {//checking there is blocked user or not in the list
                for (int i = 0; i < currentLoggedInUser.getBlockedUsers().size(); i++) {
                    System.out.println("Name: " + currentLoggedInUser.getBlockedUsers().get(i).getName());
                    System.out.println("Username: " + currentLoggedInUser.getBlockedUsers().get(i).getUserName());
                    System.out.println("Date of Birth: " + currentLoggedInUser.getBlockedUsers().get(i).getDateOfBirth());
                    //in users.txt, date is like that MM/dd/yyyy, in expected output, dd/MM/yyyy
                    System.out.println("School: " + currentLoggedInUser.getBlockedUsers().get(i).getSchoolGraduated());
                    System.out.println("----------------------------");
                }
            } else { //if the list size is 0, isEmpty()
                System.out.println("You haven’t blocked any user yet!");
            }
        } else {//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }


    public static void addPost(String[] array){

/*     As we can see in the processor function, if the user gives the ADDPOST-TEXT, ADDPOST-IMAGE, or ADDPOST-VIDEO command, this function will be called.
       The important point here is the tagging of friends. Because the friend tagging event is common for each post type,
       they are all combined under the roof of this method called addPost. In the last part of the function, objects are created according to the command,
       i.e. the word in the first index. Instead of writing 3 different functions, parsing in the last part gave our program flexibility and made it more meaningful.
*/

        ArrayList<User> taggedFriends = new ArrayList<User>(); //this is the list that we keep users who are tagged.
        if(currentLoggedInUser != null) { //checking there is a logged in user or not
            String taggedString = array[4]; //the tagged friends are always fourth index of the arrayList.
            String[] taggedArrayByColon = splitterForColon(taggedString);
            //In the friend tagging, tagged users separated by colons, we can get splitted list of them via this method.\\

            for(int i = 0; i< taggedArrayByColon.length; i++){
                boolean userExistOrNot = false;
                for(int j = 0; j< currentLoggedInUser.getFriendList().size(); j++){
                    if(taggedArrayByColon[i].equals(currentLoggedInUser.getFriendList().get(j).getUserName())){
                        //checking the user is in friendList of the currentLoggedInUser or not
                        userExistOrNot = true;//if user in the friend list
                        if(!taggedFriends.contains(currentLoggedInUser.getFriendList().get(j))){//to avoid tagging the user twice
                            taggedFriends.add(currentLoggedInUser.getFriendList().get(j));
                            /* As we know taggedFriends list is empty at the beginning. If conditions are satisfied, we will
                            add users to taggedFriends list according to taggedArrayByColon*/
                            break;//to pass to another friend of currentLoggedInUser
                        }else{
                            System.out.println(currentLoggedInUser.getFriendList().get(j).getUserName() + " is already tagged.");//optional feed-back
                        }
                    }

                }if(!userExistOrNot){//if user not in the friend list
                    System.out.println(taggedArrayByColon[i] + " is not your friend, and will not be tagged!");
                }
            }

            // ADDING POST PART

            if(array[0].equals("ADDPOST-TEXT")) {
                TextPost newTextPost = new TextPost(array[1], new Location(Double.parseDouble(array[2]),
                        Double.parseDouble(array[3])), taggedFriends, new Date());
                currentLoggedInUser.getPosts().add(newTextPost);
                //we will add the all posts to the posts arrayList which is the attribute of the User class objects.
                System.out.println("The post has been successfully added.");
            }

            else if(array[0].equals("ADDPOST-IMAGE")) {
                ImagePost newImagePost = new ImagePost(array[1],new Location(Double.parseDouble(array[2]),
                        Double.parseDouble(array[3])),taggedFriends, array[5],array[6],new Date());
                currentLoggedInUser.getPosts().add(newImagePost);
                //we will add the all posts to the posts arrayList which is the attribute of the User class objects.
                System.out.println("The post has been successfully added.");
            }

            else if(array[0].equals("ADDPOST-VIDEO")) {
                VideoPost newVideoPost = new VideoPost(array[1], new Location(Double.parseDouble(array[2]),
                        Double.parseDouble(array[3])), taggedFriends, array[5], Double.parseDouble(array[6]), new Date());

                if (newVideoPost.getVideoDuration() != 0.0) { //if it is different from 0.0
                    /* Why we write != 0.0 because in the VideoPost class, VideoDuration attribute is 0.0 at the beginning.
                    (Since its type is double, it was assigned 0.0 by default.). If we check the VideoPost class we can clearly
                    see that if it does not meet the conditions which is about constant(10 minutes) then it will assigned
                    anything, and it will stay as 0.0.
                     */
                    currentLoggedInUser.getPosts().add(newVideoPost);
                    //we will add the all posts to the posts arrayList which is the attribute of the User class objects.
                    System.out.println("The post has been successfully added.");
                }else{
                    System.out.println("Error: Your video exceeds maximum allowed duration of 10 minutes.");
                    /*If videoDuration is 0 or negative it will give same massage. Instead of it, this general message might
                    be better System.out.println("Error: Your video exceeds maximum allowed duration of 10 minutes, also must be positive");*/
                }
            }
        }else{//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }

    public static void removeLastPost(){
        if(currentLoggedInUser != null) {//checking there is a logged in user or not
            if (!currentLoggedInUser.getPosts().isEmpty()) {
                Post lastPost = currentLoggedInUser.getPosts().get(currentLoggedInUser.getPosts().size() - 1);
                //currentLoggedInUser.getPosts().size() - 1 give us the last index
                currentLoggedInUser.getPosts().remove(lastPost);
                System.out.println("Your last post has been successfully removed.");
            }else{//if the getPosts() is empty
                System.out.println("Error: You do not have any post.");
            }
        }else{//if not logged in
            System.out.println("Error: Please sign in and try again.");
        }
    }


    public static void showPosts(String[] array){
        boolean existOrNot = false;//we will use it to determine user is registered to system or not.
        for(int i = 0; i< userRecords.size(); i++){ //according to userRecords arrayList
            if(userRecords.get(i).getUserName().equals(array[1])){//to find the user that we want to see him/her posts.
                existOrNot = true;
                if(userRecords.get(i).getPosts().size() != 0) {//if posts list of user is not empty
                    System.out.println("******************");
                    System.out.println(userRecords.get(i).getName() + "'s Posts");
                    System.out.println("******************");
                    for (int j = 0; j < userRecords.get(i).getPosts().size(); j++) {
                        //This is the second loop in order to access each Post objects in the posts arrayList
                        System.out.println(userRecords.get(i).getPosts().get(j).showPost());
/*                      We use showPost method here, it is the method of post classes(text, image, video and also Post superclass)
                        Thanks to this method, posts will be displayed as desired, in fact, it is like toString method.
                        */

                        System.out.println("------------------");
                    }
                }else{//if there is no post shared
                    System.out.println(userRecords.get(i).getUserName() + " does not have any posts yet.");
                }
            }
        }if(!existOrNot){
            System.out.println("No such user!");
        }
    }
}

