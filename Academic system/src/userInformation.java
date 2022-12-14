import java.sql.Connection;

abstract public class userInformation {
    private String userPosition;
    public String getUserPosition() {
        return userPosition;
    }
    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }
    abstract public void loggedIn(Connection con, int userID);
}
