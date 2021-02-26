package utils;

public class ProductQuantity {
    private ProductCode product;
    private int quantity = 0;

    public ProductCode getProduct() {
        return product;
    }

    public void setProduct(ProductCode product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
