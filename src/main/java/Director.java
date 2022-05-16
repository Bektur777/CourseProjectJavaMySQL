import java.sql.*;
import java.util.Scanner;

public class Director {

    Reading reading = new Reading();

    public Director() throws SQLException {
    }

    public static void main(String[] args) throws Exception{

        Director director = new Director();
        Scanner scan = new Scanner(System.in);

        System.out.println("""
                Greetings dear Director!
                Please dial the menu number to work with the program, if you have finished, then dial 6:""");

        while(true) {

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
                case "6" -> Main.main(args);
                default -> System.out.println("Wrong input!!! Try again the next time :)");
            }
        }
    }

    public void viewListEquipment() throws SQLException {

        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        while(resultSet.next()) {
            equipment.setName(resultSet.getString("name"));
            String result = String.format("Name: %s",equipment.getName());
            System.out.println(result);
        }

    }

    public void viewCountOfEquipment() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        while(resultSet.next()) {
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            String result = String.format("Name: %s Count: %s",equipment.getName(), equipment.getCount());
            System.out.println(result);
        }
    }

    public void maxAndMinCount(String maxOrMin) throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        int min = Integer.MAX_VALUE;
        int max = 0;
        StringBuilder result = new StringBuilder();

        if (maxOrMin.equals("Max")) {
            while (resultSet.next()) {
                equipment.setName(resultSet.getString("name"));
                equipment.setCount(Integer.parseInt(resultSet.getString("count")));
                if (equipment.getCount() > max) {
                    max = equipment.getCount();
                }
            }
            result.append(String.format("Name: %s Count: %s", equipment.getName(), max));
            System.out.println(result);
        }
        else if (maxOrMin.equals("Min")) {
            while (resultSet.next()) {
                equipment.setName(resultSet.getString("name"));
                equipment.setCount(Integer.parseInt(resultSet.getString("count")));
                if (equipment.getCount() < min) {
                    min = equipment.getCount();
                }
            }
            result.append(String.format("Name: %s Count: %s", equipment.getName(), min));
            System.out.println(result);
        }
    }

    public void viewReports() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));
        while(resultSet.next()) {
            equipment.setSerialNumber(Integer.parseInt(resultSet.getString("serialnumber")));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            equipment.setDate(resultSet.getString("date"));
            String result = String.format("" +
                    "Serial number: %s Name: %s Count: %s Date: %s", equipment.getSerialNumber(),
                    equipment.getName(), equipment.getCount(), equipment.getDate());
            System.out.println(result);
        }
    }

}

