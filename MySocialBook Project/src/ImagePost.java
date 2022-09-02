import java.util.ArrayList;
import java.util.Date;

public class ImagePost extends Post{ //inherited from Post class.


    private String textualPart; // textual part is not in all post type so we define it here not in superclass.
    private String filePath; // e.g img.png
    private String resolution;


    public ImagePost(String textualPart, Location location, ArrayList<User> taggedFriends, String filePath, String resolution, Date date) {
        super(location, taggedFriends, date); //comes from Post superclass, these are common for all types.
        this.setTextualPart(textualPart);
        this.setFilePath(filePath);
        this.setResolution(resolution);

    }

    @Override
    public String getTaggedFriends() {
        return super.getTaggedFriends();
    }
    //Here we use method that we create for Post superclass. It is also valid for this class thanks to inheritance.

    public String getTextualPart() {
        return textualPart;
    }

    public void setTextualPart(String textualPart) {
        this.textualPart = textualPart;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public String showPost() { //to be displayed as desired, it is overridden.
        if(getTaggedFriends().equals("")){ // if the string is empty than we do not want to display "Friends tagged in the post: "
            return getTextualPart() + "\n" + "Date: " + getDate() + "\n" + "Location: " + getLocation() + "\n" + "Image: " + getFilePath() +  "\n" + "Image resolution: " + getResolution();
        }else{
            return getTextualPart() + "\n" + "Date: " + getDate() + "\n" + "Location: " + getLocation() + "\n" + "Friends tagged in the post: " + getTaggedFriends() +  "\n" + "Image: " + getFilePath() +  "\n" + "Image resolution: " + getResolution();
        }
    }
}
