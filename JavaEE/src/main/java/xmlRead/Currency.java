package xmlRead;

public class Currency {

    private String currencyName;
    private String forexBuying;
    private String forexSelling;

    public Currency(String currencyName, String forexBuying, String forexSelling) {
        this.currencyName = currencyName;
        this.forexBuying = forexBuying;
        this.forexSelling = forexSelling;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getForexBuying() {
        return forexBuying;
    }

    public void setForexBuying(String forexBuying) {
        this.forexBuying = forexBuying;
    }

    public String getForexSelling() {
        return forexSelling;
    }

    public void setForexSelling(String forexSelling) {
        this.forexSelling = forexSelling;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyName='" + currencyName + '\'' +
                ", forexBuying='" + forexBuying + '\'' +
                ", forexSelling='" + forexSelling + '\'' +
                '}';
    }
}
