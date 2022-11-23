package api;

public class User {

    private String ID;
    private String Password;
    private String Name;
    private String Type;
    private String PhoneNumber;
    public String Gender;
    private Boolean Locker;
    private String createTime ;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

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

    public Boolean getLocker() {
        return Locker;
    }


    public void setLocker(Boolean Locker) {
        this.Locker = Locker;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}