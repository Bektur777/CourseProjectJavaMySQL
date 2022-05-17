import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delivery {

    static Scanner scan = new Scanner(System.in);

    public static void deliveryMenu() throws Exception{

        Delivery delivery = new Delivery();

        System.out.println("""
                Greetings, dear Deliverer!
                Please dial the menu number to work with the program, if you have finished, then dial 7:""");
        String str = ("""
                
                1.Show the list of equipment to be delivered
                2.Show the delivered equipment
                3.Show the delivered equipment:
                    What equipment has been delivered? Enter the name or serial number of the equipment
                4.Show number of delivered machines
                5.Show number of ordered machines
                6.Show how much I earned
                7.Exit""");

        try{
            System.out.println(str);
            int numberOfMenu = scan.nextInt();
            switch (numberOfMenu) {
                case 1 -> delivery.viewEquipmentForDelivery();
                case 2 -> delivery.viewDeliveredEquipment();
                case 3 -> delivery.deliverEquipment();
                case 4 -> delivery.viewCountOfDeliveredEquipment();
                case 5 -> delivery.viewCountOfOrderedEquipment();
                case 6 -> delivery.viewSalary();
                case 7 -> MainMenu.mainMenu();
                default -> System.out.println("Wrong input!!! Try again :)");
            }
        } catch (SQLException e) {
            deliveryMenu();
        }
    }

    public void viewEquipmentForDelivery() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from orderedproducts");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        while(resultSet.next()) {
            equipment.setSerialNumber(Integer.parseInt(resultSet.getString("serialnumber")));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            equipment.setDate(resultSet.getString("date"));
            String result = String.format("Serial number: %s Name: %s Count: %s Date: %s",
                    equipment.getSerialNumber(), equipment.getName(), equipment.getCount(), equipment.getDate());

            System.out.println(result);
        }
    }

    public void viewDeliveredEquipment() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from deliveredproducts");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        while(resultSet.next()) {
            equipment.setSerialNumber(Integer.parseInt(resultSet.getString("serialnumber")));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            equipment.setDate(resultSet.getString("date"));
            String result = String.format("Serial number: %s Name: %s Count: %s Date: %s",
                    equipment.getSerialNumber(), equipment.getName(), equipment.getCount(), equipment.getDate());

            System.out.println(result);
        }
    }

    public void deliverEquipment() throws SQLException {

        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from orderedproducts");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));
        Statement statement = selecting.getStatement();

        System.out.println("Enter the serial number");
        String deliver = "";
        int serNum = scan.nextInt();
        int id = 0;

        while(resultSet.next()) {
            equipment.setId(Integer.parseInt(resultSet.getString("id")));
            equipment.setSerialNumber(Integer.parseInt((resultSet.getString("serialnumber"))));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));

            if (serNum == equipment.getSerialNumber()) {
                String result = String.format("(%s,'%s',%s,%s)",
                        equipment.getSerialNumber(), equipment.getName(), equipment.getCount(), "now()");
                deliver = result;
                System.out.println(result);
                id = equipment.getId();
                System.out.println(id);
            }
        }
        statement.executeUpdate("delete from orderedproducts where id = " + id);
        statement.execute("insert deliveredproducts(serialnumber, name, count, date) values" + deliver);
    }

    public void viewCountOfDeliveredEquipment() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from deliveredproducts");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        int count = 0;
        System.out.println("Name Count");
        while(resultSet.next()) {
            equipment.setSerialNumber(Integer.parseInt(resultSet.getString("serialnumber")));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            equipment.setDate(resultSet.getString("date"));
            String result = String.format("%s %s ", equipment.getName(), equipment.getCount());
            System.out.println(result);
            count += equipment.getCount();
        }
        System.out.println("All delivered products: " + count);
    }

    public void viewCountOfOrderedEquipment() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from orderedproducts");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        int count = 0;
        System.out.println("Name Count");
        while(resultSet.next()) {
            equipment.setSerialNumber(Integer.parseInt(resultSet.getString("serialnumber")));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            equipment.setDate(resultSet.getString("date"));
            String result = String.format("%s %s ", equipment.getName(), equipment.getCount());
            System.out.println(result);
            count += equipment.getCount();
        }
        System.out.println("All ordered products: " + count);
    }

    public void viewSalary() throws SQLException {

        Selecting selecting = new Selecting();

        selecting.setQuery("select * from orderedproducts");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));
        Statement statement = selecting.getStatement();

        int count = 0;
        System.out.println("The price for delivery is equal to " + 20 + "$");
        while(resultSet.next()) {
            count++;
        }
        count *= 20;
        System.out.println("Income for delivered goods: " + count + "$");
        statement.executeUpdate("update salary set wallet = " + count + " where id = 1");
        wallet();
    }

    public void wallet() throws SQLException {
        Wallet wallet = new Wallet();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from salary");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        while(resultSet.next()) {
            wallet.setId(Integer.parseInt(resultSet.getString("id")));
            wallet.setWallet(Integer.parseInt(resultSet.getString("wallet")));
            System.out.println ("Your salary: " + wallet.getWallet() + "$");
        }
    }

}
