package com.evoluirmais.evoluirmaisapi.service;

public class CpfValidator {


    public static boolean isValidCpf(String cpf) {
        if (cpf == null) {
            return false;
        }

        String cleanCpf = cpf.replaceAll("[^0-9]", "");

        if (cleanCpf.length() != 11 || cleanCpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int[] numbers = new int[11];
            for (int i = 0; i < 11; i++) {
                numbers[i] = Character.getNumericValue(cleanCpf.charAt(i));
            }

            // CALCULO DO PRIMEIRO DIGITO VERIFICADOR
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += numbers[i] * (10 - i);
            }
            int firstVerifier = 11 - (sum % 11);
            if (firstVerifier > 9) {
                firstVerifier = 0;
            }
            if (numbers[9] != firstVerifier) {
                return false;
            }

            // CALCULO DO SEGUNDO DIGITO VERIFICADOR
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += numbers[i] * (11 - i);
            }
            int secondVerifier = 11 - (sum % 11);
            if (secondVerifier > 9) {
                secondVerifier = 0;
            }

            return numbers[10] == secondVerifier;

        } catch (Exception e) {
            return false;
        }
    }
}