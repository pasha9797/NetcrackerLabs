package vsu.netcracker;

public class CashBox {
    private int cash;

    public int getCash() {
        return cash;
    }

    public void putCash(int amount){
        cash+=amount;
    }

    public void takeCash(int amount){
        cash-=amount;
    }

    public CashBox(int initialCash){
        cash=initialCash;
    }
}
