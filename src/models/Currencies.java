package models;

import Exceptions.NullConversionRatesException;

import java.util.ArrayList;
import java.util.List;

public class Currencies {
    private String baseCode;
    private List<Coins> conversionRates;

    public Currencies(CurrenciesJson currenciesJson){
        conversionRates = new ArrayList<>();
        this.baseCode = currenciesJson.base_code();
        if(currenciesJson.conversion_rates() == null){
            throw new NullConversionRatesException("the base code isin't a valid coin");
        }
        this.addConversionRates(currenciesJson.conversion_rates().toString());
    }

    private void addConversionRates(String cr){
        cr = cr.substring(1,cr.indexOf("}"));
        String[] conversionRates = cr.split(", ");
        for (String conversionRate: conversionRates){
            String[] fields = conversionRate.split("=");
            Coins coin = new Coins(fields[0],Double.parseDouble(fields[1]));
            this.conversionRates.add(coin);
        }
    }

    public void showCurrencies(){
        int i = 0;
        for (Coins coin : this.getConversionRates()){
            System.out.println(i + ". " + coin.getName());
            i++;
        }
    }

    public List<Coins> getConversionRates() {
        return conversionRates;
    }

    public Coins getCoin(String coin){
        for (Coins c: conversionRates){
            if(c.getName().equalsIgnoreCase(coin)){
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "models.Currencies(" +
                "baseCode = " + baseCode  +
                ", conversionRates = " + conversionRates +
                ')';
    }
}
