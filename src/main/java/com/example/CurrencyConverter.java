package com.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_KEY = "YOUR-API-KEY"; // Substitua pela sua chave
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String[] SUPPORTED_CURRENCIES = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleHttpClient client = new SimpleHttpClient();

        while (true) {
            System.out.println("\n===== Conversor de Moedas =====");
            System.out.println("Selecione a moeda de origem:");
            for (int i = 0; i < SUPPORTED_CURRENCIES.length; i++) {
                System.out.printf("%d - %s%n", i + 1, SUPPORTED_CURRENCIES[i]);
            }
            System.out.println("0 - Sair");

            int fromIndex = scanner.nextInt();
            if (fromIndex == 0) break;
            if (fromIndex < 1 || fromIndex > SUPPORTED_CURRENCIES.length) {
                System.out.println("Opção inválida.");
                continue;
            }

            String fromCurrency = SUPPORTED_CURRENCIES[fromIndex - 1];

            System.out.println("Selecione a moeda de destino:");
            for (int i = 0; i < SUPPORTED_CURRENCIES.length; i++) {
                System.out.printf("%d - %s%n", i + 1, SUPPORTED_CURRENCIES[i]);
            }

            int toIndex = scanner.nextInt();
            if (toIndex < 1 || toIndex > SUPPORTED_CURRENCIES.length) {
                System.out.println("Opção inválida.");
                continue;
            }

            String toCurrency = SUPPORTED_CURRENCIES[toIndex - 1];

            System.out.print("Digite o valor a ser convertido: ");
            double amount = scanner.nextDouble();

            String url = BASE_URL + API_KEY + "/latest/" + fromCurrency;
            HttpRequest request = new HttpRequest(url);
            HttpResponse response = client.send(request);

            if (response.getStatusCode() == 200) {
                JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();

                if (json.get("conversion_rates").getAsJsonObject().has(toCurrency)) {
                    double rate = json.get("conversion_rates").getAsJsonObject().get(toCurrency).getAsDouble();
                    double result = convertCurrency(amount, rate);
                    System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency, result, toCurrency);
                } else {
                    System.out.println("Moeda de destino não encontrada.");
                }
            } else {
                System.out.println("Erro ao obter dados da API. Código: " + response.getStatusCode());
            }
        }

        scanner.close();
        System.out.println("Programa finalizado.");
    }

    public static double convertCurrency(double amount, double rate) {
        return amount * rate;
    }
}
