package models;

public class Coins {
    private String name;
    private Double rate;

    public Coins(String name, Double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Coin(" + name + "=" + rate + ")";
    }
}
