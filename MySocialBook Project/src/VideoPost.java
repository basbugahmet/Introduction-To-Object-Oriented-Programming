import java.util.ArrayList;
import java.util.Date;

public class VideoPost extends Post{ //inherited from Post class.

    private String textualPart; // textual part is not in all post type so we define it here not in superclass.
    private String FilePath; // e.g myvideo.avi
    private double videoDuration;
    final int constant = 10; // We do not want this value to be overridden later, therefore we define it with final keyword.

    public VideoPost(String textualPart, Location location, ArrayList<User> taggedFriends, String FilePath, double videoDuration, Date date) {
        super(location, taggedFriends, date); //comes from Post superclass, these are common for all types.
        this.setTextualPart(textualPart);
        this.setFilePath(FilePath);
        this.setVideoDuration(videoDuration);
    }

    public String getTextualPart() {
        return textualPart;
    }

    public void setTextualPart(String textualPart) {
        this.textualPart = textualPart;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        this.FilePath = filePath;
    }

    public int getConstant() { return constant; }

    public double getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(double videoDuration) {
        if(videoDuration <= getConstant() && videoDuration > 0) { //video duration should be positive
            this.videoDuration = videoDuration;
        }
        //if the condition is not met, then it will be 0.0 by default.
    }

    @Override
    public String getTaggedFriends() {
        return super.getTaggedFriends();
    }
    //Here we use method that we create for Post superclass. It is also valid for this class thanks to inheritance.


    @Override
    public String showPost() {
        if(getTaggedFriends().equals("")){ // if the string is empty than we do not want to display "Friends tagged in the post: "
           return getTextualPart() + "\n" + "Date: " + getDate() + "\n" + "Location: " + getLocation() + "\n" + "Video: " + getFilePath() +  "\n" + "Video duration: " + getVideoDuration() + " minutes";

       }else{
            return getTextualPart() + "\n" + "Date: " + getDate() + "\n" + "Location: " + getLocation() + "\n" + "Friends tagged in the post: " + getTaggedFriends() +  "\n" + "Video: " + getFilePath() +  "\n" + "Video duration: " + getVideoDuration() + " minutes";
        }
    }
}

