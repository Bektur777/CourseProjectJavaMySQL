import java.sql.*;
import java.util.Scanner;

public class Manager {

    public static Scanner scan = new Scanner(System.in);

    public static void managerMenu() {

        Manager manager = new Manager();
        Director director = new Director();

        System.out.println("""
                Greetings, dear Zavkhoz!
                Please dial the menu number to work with the program, if you are finished, then dial 7:""");

        try {
            System.out.println("""                    
                1.Show all list of equipment for school
                2.Search for equipment:
                    By serial number.
                    By name
                3.Show equipment report
                4.Place an order for the equipment:
                5.View list of ordered equipment
                6.Delete order:
                7.Exit.""");
            String numberOfMenu = scan.nextLine();

            switch (numberOfMenu) {
                case "1" -> manager.viewCountOfEquipment();
                case "2" -> manager.searchingEquipment();
                case "3" -> director.viewReports();
                case "4" -> manager.orderEquipment();
                case "5" -> manager.viewOrderingEquipment();
                case "6" -> manager.deleteOrder();
                case "7" -> MainMenu.mainMenu();
                default -> System.out.println("Wrong input!!! Try again :)");
            }
        } catch (Exception e) {
            managerMenu();
        }
    }

    public void viewCountOfEquipment() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        System.out.println("+------------+------------+");
        System.out.printf("| %-10s | %-10s |\n","NAME", "COUNT");
        while(resultSet.next()) {
            System.out.println("+------------+------------+");
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            String result = String.format("| %-10s | %-10s |",equipment.getName(), equipment.getCount());
            System.out.println(result);
        }
        System.out.println("+------------+------------+");
        managerMenu();
    }

    public void searchingEquipment() throws SQLException {

        Manager manager = new Manager();
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the equipment for searching!!!\nInput serial number or name (name or ser) ");
        String searching = scan.next();
        if (searching.equalsIgnoreCase("name")) {
            String searchingByName = scan.next();
            manager.searching(searchingByName);
        }
        else if (searching.equalsIgnoreCase("ser")) {
            int searchingBySerNum = scan.nextInt();
            manager.searching(searchingBySerNum);
        }
        else {
            System.out.println("Wrong input");
            searchingEquipment();
        }

    }

    public void viewReports() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        System.out.println("+--------+------------+------------+---------------------+");
        System.out.printf("| %6s | %-10s | %-10s | %-19s |\n","ID", "NAME", "COUNT", "DATE");
        while(resultSet.next()) {
            System.out.println("+--------+------------+------------+---------------------+");
            equipment.setSerialNumber(Integer.parseInt(resultSet.getString("serialnumber")));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            equipment.setDate(resultSet.getString("date"));
            String result = String.format(
                    "| %6s | %-10s | %-10s | %-10s |", equipment.getSerialNumber(),
                    equipment.getName(), equipment.getCount(), equipment.getDate());
            System.out.println(result);
        }
        System.out.println("+--------+------------+------------+---------------------+");
        managerMenu();
    }

    public void searching(String searchingByName) throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));
        System.out.println("+--------+------------+------------+");
        System.out.printf("| %-6s | %-10s | %-10s |\n","ID", "NAME", "COUNT");
        System.out.println("+--------+------------+------------+");
        while(resultSet.next()) {
            equipment.setSerialNumber(Integer.parseInt((resultSet.getString("serialnumber"))));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));

            if (searchingByName.equals(equipment.getName())) {
                String result = String.format("| %-6s | %-10s | %-10s |",
                        equipment.getSerialNumber(), equipment.getName(), equipment.getCount());
                System.out.println(result);
            }
        }
        System.out.println("+--------+------------+------------+");
        managerMenu();
    }
    public void searching(int searchingBySerNum) throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));
        System.out.println("+--------+------------+------------+");
        System.out.printf("| %-6s | %-10s | %-10s |\n","ID", "NAME", "COUNT");
        System.out.println("+--------+------------+------------+");
        while(resultSet.next()) {
            equipment.setSerialNumber(Integer.parseInt((resultSet.getString("serialnumber"))));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));

            if (searchingBySerNum == equipment.getSerialNumber()) {
                String result = String.format("| %-6s | %-10s | %-10s |",
                        equipment.getSerialNumber(), equipment.getName(), equipment.getCount());
                System.out.println(result);
            }
        }
        System.out.println("+--------+------------+------------+");
        managerMenu();
    }

    public void orderEquipment() throws SQLException {
        Selecting selecting = new Selecting();
        SerialNumber serialNumber = new SerialNumber();
        int num = serialNumber.serialNumber();

        System.out.println("Enter the equipment for order");
        String order = scan.nextLine();
        System.out.println("Enter the count for order");
        String count = scan.nextLine();

        Statement statement = selecting.getStatement();

        String orderEquipment = String.format("(%s,'%s',%s, %s)", num, order, count, "now()");
        statement.executeUpdate("insert orderedproducts(serialnumber, name, count, date) values" + orderEquipment);
        managerMenu();
    }

    public void viewOrderingEquipment() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from orderedproducts");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        System.out.println("+--------+------------+------------+---------------------+");
        System.out.printf("| %6s | %-10s | %-10s | %-19s |\n","ID", "NAME", "COUNT", "DATE");
        while(resultSet.next()) {
            System.out.println("+--------+------------+------------+---------------------+");
            equipment.setSerialNumber(Integer.parseInt(resultSet.getString("serialnumber")));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            equipment.setDate(resultSet.getString("date"));
            String result = String.format("| %6s | %-10s | %-10s | %-10s |",
                    equipment.getSerialNumber(), equipment.getName(), equipment.getCount(), equipment.getDate());

            System.out.println(result);
        }
        System.out.println("+--------+------------+------------+---------------------+");
        managerMenu();
    }

    public void deleteOrder() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from orderedproducts");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));
        System.out.println("Input serial number for deleting");
        Statement statement = selecting.getStatement();
        int searchingBySerNum = scan.nextInt();
        int id = 0;

        while(resultSet.next()) {
            equipment.setId(Integer.parseInt(resultSet.getString("id")));
            equipment.setSerialNumber(Integer.parseInt((resultSet.getString("serialnumber"))));
            if (searchingBySerNum == equipment.getSerialNumber()) {
                id = equipment.getId();
            }
        }
        statement.execute("delete from orderedproducts where id = " + id);
        System.out.println("The order was delete");
        managerMenu();
    }

}
