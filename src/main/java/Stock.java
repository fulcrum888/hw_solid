import java.util.List;

public class Stock extends Products{
    private static Stock instance = null;

    private Stock() {
    }

    public static Stock get() {
        if (instance == null) instance = new Stock();
        return instance;
    }

    public void loadToStock(List<Product> products) {
        this.products = new Products(products).getProducts();
    }

}
