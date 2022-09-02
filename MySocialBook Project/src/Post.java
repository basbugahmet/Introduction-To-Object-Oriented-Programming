import java.util.ArrayList;
import java.util.Date;

public class Post {

/*  This is the superclass for TextPost, ImagePost and VideoPost classes and also it can be used for new type Post Classes.
    All post types have location, taggedFriends and date, so they are in here as attributes.
    Thanks to this superclass, we will make our work easier in other classes using inheritance.
    Using this will provide reusability and readability to our code.*/

    private Location location;
    private ArrayList<User> taggedFriends;
    private Date date;

    public Post(Location location, ArrayList<User> taggedFriends, Date date) {
        this.setLocation(location);
        this.setTaggedFriends(taggedFriends);
        this.setDate(date);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTaggedFriends() {
/*        Why we use this method, as we know, in the MySocialBook class, when we add a newPost we add our friends to the
        taggedFriends arrayList but the type of elements in this list are User, but when we display our post, we would like
        to see just names of friends in the "Friends tagged in the post" part.
         I think it would be better to do this process with get method instead of defining a new method, thanks to advantages of encapsulation.*/

        if(!taggedFriends.isEmpty()) {
/*      we need to tag friends when publishing each post,
        but if the person we are tagging is not our friend,
        then the taggedFriends list may be empty, we check it here.*/
            StringBuilder taggedAsString = new StringBuilder();
            for (User a : taggedFriends) {
                taggedAsString.append(a.getName());
                //to add comma
                taggedAsString.append(",");
            }
            //to remove last comma
            taggedAsString.setLength(taggedAsString.length() - 1);
            return taggedAsString.toString();
        }else{
            return "";
        }
    }

    public void setTaggedFriends(ArrayList<User> taggedFriends) {
        this.taggedFriends = taggedFriends;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String showPost(){ //This method will be overridden in other post classes to be displayed as desired.
        return "Post{" +
                ", location=" + getLocation() +
                ", taggedFriends=" + getTaggedFriends() +
                ", date=" + getDate() +
                '}';
    }

}
