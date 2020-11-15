import java.util.ArrayList;
import java.util.List;

public class TestProductsLoader implements ProductsDataLoader {
    List<Product> products = new ArrayList<>();

    @Override
    public List<Product> loadProducts() {
        products.add(new Product("Кроссовки \"Gel Kayano 27\"", "Asics", 12999));
        products.add(new Product("Велосипед \"Gran Fondo\"", "Fuji", 157038.99));
        products.add(new Product("Очки для плавания \"Nest Pro\"", "TYR", 3610));
        products.add(new Product("Часы \"Vantage M\"", "Polaris", 24302.5));
        products.add(new Product("Джаммеры \"Fusion 2\"", "TYR", 12999));
        products.add(new Product("Рюкзак \"Ultra 8 Marathon\"", "OMM", 12999));
        return products;
    }
}
