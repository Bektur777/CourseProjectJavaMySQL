package accounts;

import entrance.MainMenu;
import sources.*;

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

        PrettyTable table = new PrettyTable("NAME");
        while(resultSet.next()) {
            equipment.setName(resultSet.getString("name"));
            table.addRow(equipment.getName());
        }
        System.out.println(table);
        directorMenu();
    }

    public void viewCountOfEquipment() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        PrettyTable table = new PrettyTable("NAME", "COUNT");
        while(resultSet.next()) {
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            table.addRow(equipment.getName(),Integer.toString(equipment.getCount()));
        }
        System.out.println(table);
        directorMenu();
    }

    public void maxAndMinCount(String maxOrMin) throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        int min = Integer.MAX_VALUE;
        int max = 0;
        PrettyTable table = new PrettyTable("NAME", "COUNT");
        if (maxOrMin.equals("Max")) {
            while (resultSet.next()) {
                equipment.setName(resultSet.getString("name"));
                equipment.setCount(Integer.parseInt(resultSet.getString("count")));
                if (equipment.getCount() > max) {
                    max = equipment.getCount();
                }
            }
            table.addRow(equipment.getName(), Integer.toString(max));
            System.out.println(table);
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
            table.addRow(equipment.getName(), Integer.toString(min));
            System.out.println(table);
            directorMenu();
        }
    }

    public void viewReports() throws SQLException {
        Equipment equipment = new Equipment();
        Selecting selecting = new Selecting();
        selecting.setQuery("select * from allequipment");
        ResultSet resultSet = selecting.setResultSet(selecting.getStatement().executeQuery(selecting.getQuery()));

        PrettyTable table = new PrettyTable("ID","NAME", "COUNT", "DATE");
        while(resultSet.next()) {
            equipment.setSerialNumber(Integer.parseInt(resultSet.getString("serialnumber")));
            equipment.setName(resultSet.getString("name"));
            equipment.setCount(Integer.parseInt(resultSet.getString("count")));
            equipment.setDate(resultSet.getString("date"));

            table.addRow(Integer.toString(equipment.getSerialNumber()), equipment.getName(),
                    Integer.toString(equipment.getCount()), equipment.getDate());
        }
        System.out.println(table);
        directorMenu();
    }

}

