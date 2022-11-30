package api;

public class User {

    private int ID;
    private String UserID;
    private String Password;
    private String Name;
    private String Type;
    private String PhoneNumber;
    public String Gender;
    private Boolean lockerFlag;
    private String createDateTime ;

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getUserID() { return UserID; }
    public void setUserID(String UserID) { this.UserID = UserID; }
    public String getName() {
        return Name;
    }
    public String getPassword() { return Password; }
    public void setPassword(String Password) {
        this.Password = Password;
    }
    public void setName(String userName) {
        this.Name = Name;
    }
    public String getType() {
        return Type;
    }
    public void setType(String Type) {
        this.Type = Type;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }
    public String getGender() { return Gender; }
    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    public Boolean getLockerFlag() {
        return lockerFlag;
    }
    public void setLockerFlag(Boolean lockerFlag) {
        this.lockerFlag = lockerFlag;
    }
    public String getCreateDateTime() {
        return createDateTime;
    }
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }
}