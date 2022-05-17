import java.util.Scanner;

public class Wallet {

    private int id;
    private  int wallet;

    public Wallet (int id, int wallet) {
        this.id = id;
        this.wallet = wallet;
    }

    public Wallet() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

}
