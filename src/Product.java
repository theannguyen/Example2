import java.io.Serializable;

public class Product implements Serializable {
    int id;
    String name;
    double price;
    int amount;
    String describe;

    public Product(int id, String name, double price, int amount, String describe) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.describe = describe;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Mã sản phẩm=" + id +
                ", Tên sản phẩm='" + name + '\'' +
                ", Giá sản phẩm=" + price +
                ", Số lượng=" + amount +
                ", Mô tả='" + describe + '\'' +
                '}';
    }
}
