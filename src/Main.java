import Exceptions.NullConversionRatesException;
import models.Coins;
import models.ConsultCurrenciesJson;
import models.Currencies;
import models.CurrenciesJson;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("digite a moeda base(ex: USD --> dolar): ");
            String baseCode = sc.nextLine();

            if(baseCode.equalsIgnoreCase("")){
                baseCode = "USD";
                System.out.println("Como nada foi digitado, Dolar foi selecionado automaticamente");
            }

            ConsultCurrenciesJson consult = new ConsultCurrenciesJson();
            CurrenciesJson currenciesJson = consult.generateCurrenciesJson(baseCode);
            try {
                Currencies currencies = new Currencies(currenciesJson);
                currencies.showCurrencies();
                System.out.println("Selecione moeda para troca/mudança: ");
                int i = sc.nextInt();

                Coins coin = currencies.getConversionRates().get(i);

                System.out.println("Informe um valor: ");
                double j = sc.nextDouble();

                System.out.println(j + " " + baseCode + " em " + coin.getName() + " são: " + coin.getRate()*j);

            } catch (NullConversionRatesException e) {
                System.out.println("Error!");
                System.out.println(e.getMessage());
            } catch (InputMismatchException e){
                System.out.println("Error!");
                System.out.println("invalid input");
            }
            System.out.println("Deseja sair? Então digite 'sair'.");
            baseCode = sc.next();
            if(baseCode.equalsIgnoreCase("sair")){
                break;
            }
        }
    }
}