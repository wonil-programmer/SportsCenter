package api;

public class User {

    private String userID;
    private String userPassword;
    private String userName;
    private String userBelong;
    private String userPhoneNumber;
    private String userGender;
    private Boolean userLocker;
    private String createTime ;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBelong() {
        return userBelong;
    }

    public void setUserBelong(String userBelong) {
        this.userBelong = userBelong;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Boolean getUserLocker() {
        return userLocker;
    }

    public void setUserLocker(Boolean userLocker) {
        this.userLocker = userLocker;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
