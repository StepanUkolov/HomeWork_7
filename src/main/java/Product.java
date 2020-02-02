public class Product {
    private String name;
    private String garanty;
    private int price;
    private int garantyPrice;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGaranty() {
        return garanty;
    }

    public void setGaranty(String garanty) {
        this.garanty = garanty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGarantyPrice() {
        return garantyPrice;
    }

    public void setGarantyPrice(int garantyPrice) {
        this.garantyPrice = garantyPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", garanty='" + garanty + '\'' +
                ", price=" + price +
                ", garantyPrice=" + garantyPrice +
                '}';
    }
}
