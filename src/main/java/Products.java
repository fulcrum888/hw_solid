import java.util.ArrayList;
import java.util.List;

public class Products implements ConvertibleToStringArray {
    protected List<Product> products = new ArrayList<>();

    public Products() {
    }

    public Products(List<Product> products) {
        this.products.addAll(products);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String[] getAsArray() {
        String[] result = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            result[i] = products.get(i).toString();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            result.append(i+1)
                    .append(". ")
                    .append(products.get(i).toString())
                    .append("\n");
        }
        return result.toString();
    }
}
