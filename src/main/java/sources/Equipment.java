package sources;

public class Equipment {
    private int id;
    private int serialNumber;
    private String name;
    private int count;
    private String date;

    public Equipment() {

    }

    public Equipment(int serialNumber, String name, int count, String date) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.count = count;
        this.date = date;
    }

    public Equipment(int id, int serialNumber, String name, int count, String date) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.name = name;
        this.count = count;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

