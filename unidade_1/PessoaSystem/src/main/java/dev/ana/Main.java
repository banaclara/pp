package dev.ana;

import dev.ana.conn.Database;
import dev.ana.utils.Menu;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Database db = new Database();
            Scanner scanner = new Scanner(System.in);

            Menu.principal(scanner, db);
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }
}
