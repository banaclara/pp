package dev.ana.utils;

import dev.ana.models.Cargo;
import dev.ana.models.Telefone;

import java.util.Scanner;
import java.util.UUID;

public class Leitor {
    public static String obterInput(Scanner scanner, String legenda) {
        System.out.println(legenda);
        return scanner.nextLine();
    }

    public static double obterDouble(Scanner scanner, String legenda) {
        System.out.println(legenda);
        return scanner.nextDouble();
    }

    public static int obterInt(Scanner scanner, String legenda) {
        System.out.println(legenda);
        return scanner.nextInt();
    }

    public static UUID obterUUID(Scanner scanner, String legenda) {
        System.out.println(legenda);
        String id = scanner.nextLine();
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Formato inválido de UUID. Tente novamente.");
            return null;
        }
    }

    public static Telefone obterTelefone(Scanner scanner) {
        System.out.println("Telefone:");
        String ddd = obterInput(scanner, "DDD: ");
        String numero = obterInput(scanner, "Número:");
        return new Telefone(ddd, numero);
    }

    public static String obterData(Scanner scanner, String legenda) {
        System.out.println(legenda);
        System.out.print("Dia: ");
        int dia = scanner.nextInt();
        System.out.print("Mês: ");
        int mes = scanner.nextInt();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        return String.format("%04d-%02d-%02d", ano, mes, dia);
    }

    public static Cargo obterCargo(Scanner scanner) {
        Cargo cargo = null;
        boolean validar = false;

        while (!validar) {
            System.out.println("Cargo (opções: GERENTE, ANALISTA, DESENVOLVEDOR, DEVOPS, DESIGNER, SUPORTE, QA, ESTAGIARIO):");
            String cargoInput = scanner.nextLine().toUpperCase();

            try {
                cargo = Cargo.valueOf(cargoInput);
                validar = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Cargo inválido. Tente novamente.");
            }
        }

        return cargo;
    }
}
