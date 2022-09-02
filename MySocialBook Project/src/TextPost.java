import java.util.ArrayList;
import java.util.Date;

public class TextPost extends Post { //inherited from Post class.

    private String textualPart; // textual part is not in all post type so we define it here not in superclass.
                                //(new Post type do not have to have textualPart)

    public TextPost(String textualPart, Location location, ArrayList<User> taggedFriends, Date date) {
        super(location, taggedFriends, date); //comes from Post superclass, these are common for all types.
        this.setTextualPart(textualPart);
    }

    public String getTextualPart() {
        return textualPart;
    }

    public void setTextualPart(String textualPart) {
        this.textualPart = textualPart;
    }

    @Override
    public String getTaggedFriends() {
        return super.getTaggedFriends();
    }
    //Here we use method that we create for Post superclass. It is also valid for this class thanks to inheritance.


    @Override
    public String showPost() {
        if (getTaggedFriends().equals("")) { // if the string is empty than we do not want to display "Friends tagged in the post: "
            return getTextualPart() + "\n" + "Date: " + getDate() + "\n" + "Location: " + getLocation();
        } else {
            return getTextualPart() + "\n" + "Date: " + getDate() + "\n" + "Location: " + getLocation() + "\n" + "Friends tagged in the post: " + getTaggedFriends();
        }
    }
}
