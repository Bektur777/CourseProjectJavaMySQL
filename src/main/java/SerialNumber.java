import java.util.Random;

public class SerialNumber {

    private Random random = new Random();
    private int min = 100000;
    private int max = 1000000;

    public SerialNumber() {

    }

    public SerialNumber(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int serialNumber() {
        int range = this.max - this.min;
        int res = random.nextInt(range+1);
        res += min;
        return res;
    }

}
