import java.sql.*;
import java.util.*;

public class Main {

    protected static final Scanner scanner = new Scanner(System.in);
    protected static Main main = new Main();

    public static void main(String[] args) throws SQLException {

        System.out.println("""
                Choose the type of account
                Director
                Manager
                Delivery""");

        while (scanner.hasNextLine()){

            String choiceAcc = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if (choiceAcc.equalsIgnoreCase("director")
                    || choiceAcc.equalsIgnoreCase("manager")
                    || choiceAcc.equalsIgnoreCase("delivery")) {
                main.choiceLogOrReg(choiceAcc);
            }
            else{
                System.out.println("Try again");
                continue;
            }
        }

    }

    public void choiceLogOrReg(String choiceAcc) throws SQLException {

        System.out.println("Log in or register to continue ====> (log or reg)");
        String entrance = scanner.next();
        while (scanner.hasNextLine()) {
            switch (entrance){
                case "reg": main.registration(choiceAcc);
                case "log": main.logIn(choiceAcc);
                default: System.out.println("Try again");
            }
        }

    }

    public void logIn(String choiceAcc) throws SQLException {

        Users users = new Users();
        Selecting selecting = new Selecting();
        selecting.setQuery("SELECT * FROM accounts");
        ResultSet set = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        System.out.println("To log in, enter your username and password");
        String login = scanner.next();
        String password = scanner.next();
        boolean entrance = true;

        while (set.next()) {

            users.setId(set.getInt("id"));
            users.setUsername(set.getString("username"));
            users.setPassword(set.getString("password"));
            users.setAccount(set.getString("account"));
            if (login.equals(users.getUsername()) &&
                    password.equals(users.getPassword()) &&
                    choiceAcc.equals(users.getAccount())) {
                System.out.println(users.getId());
            }
            else {
                entrance = false;
            }
        }

        if (!entrance) {
            System.out.println("Try again");
            main.logIn(choiceAcc);
        }

    }

    public void registration(String choiceAcc) throws SQLException {

        Selecting selecting = new Selecting();

        System.out.println("Enter login and password");

        String login = scanner.next();
        String password = scanner.next();
        Statement statement = selecting.getStatement();

        String account = String.format("('%s','%s','%s')",login, password, choiceAcc);
        statement.executeUpdate("insert accounts(username, password, account) values" + account);
        main.logIn(choiceAcc);

    }

}
