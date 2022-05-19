import java.sql.*;
import java.util.Scanner;

public class Director {


    public static void directorMenu() {

        Director director = new Director();
        Scanner scan = new Scanner(System.in);

        System.out.println("""
                Greetings dear Director!
                Please dial the menu number to work with the program, if you have finished, then dial 6:""");

        try{
            System.out.println("""
                1. Show list of equipment
                2. Show Number of pieces of equipment
                3. Show equipment with the highest number
                4. Show machines with the lowest number
                5. Show equipment purchase report
                6. Exit""");
            String numberOfMenu = scan.next();

            switch (numberOfMenu) {
                case "1" -> director.viewListEquipment();
                case "2" -> director.viewCountOfEquipment();
                case "3" -> director.maxAndMinCount("Max");
                case "4" -> director.maxAndMinCount("Min");
                case "5" -> director.viewReports();
                case "6" -> MainMenu.mainMenu();
                default -> System.out.println("Wrong input!!! Try again :)");
            }
        } catch (Exception e) {
            directorMenu();
        }
    }

    public void viewListEquipment() throws SQLException {

        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));


        System.out.println("+------------+");
        System.out.printf("| %-10s |\n","NAME");
        while(resultSet.next()) {
            System.out.println("+------------+");
            equipment.setName(resultSet.getString("name"));
            String result = String.format("| %-10s |",equipment.getName());
            System.out.println(result);
        }
        System.out.println("+------------+");
        directorMenu();
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
        directorMenu();
    }

    public void maxAndMinCount(String maxOrMin) throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        int min = Integer.MAX_VALUE;
        int max = 0;
        StringBuilder result = new StringBuilder();
        System.out.println("+------------+------------+");
        System.out.printf("| %-10s | %-10s |\n","NAME", "COUNT");
        System.out.println("+------------+------------+");
        if (maxOrMin.equals("Max")) {
            while (resultSet.next()) {
                equipment.setName(resultSet.getString("name"));
                equipment.setCount(Integer.parseInt(resultSet.getString("count")));
                if (equipment.getCount() > max) {
                    max = equipment.getCount();
                }
            }
            result.append(String.format("| %-10s | %-10s |", equipment.getName(), max));
            System.out.println(result);
            System.out.println("+------------+------------+");
            directorMenu();
        }
        else if (maxOrMin.equals("Min")) {
            while (resultSet.next()) {
                equipment.setName(resultSet.getString("name"));
                equipment.setCount(Integer.parseInt(resultSet.getString("count")));
                if (equipment.getCount() < min) {
                    min = equipment.getCount();
                }
            }
            result.append(String.format("| %-10s | %-10s |", equipment.getName(), max));
            System.out.println(result);
            System.out.println("+------------+------------+");
            directorMenu();
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
        directorMenu();
    }

}

