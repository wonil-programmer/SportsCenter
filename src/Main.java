import api.UserDAO;

import java.util.Arrays;

public class Main {
    public void main(String[] args) {
        System.out.println("Hello world!");
        UserDAO userDAO = new UserDAO();

        String[] stringList = new String[8];
        stringList = userDAO.userInformation(1);
        System.out.println(Arrays.toString(stringList));

    }
}