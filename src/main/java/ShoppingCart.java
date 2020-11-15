import java.util.ArrayList;

//Liskov substitution principle
//Корзина может логически сыграть роль списка товаров,
//так как представляет собой список товаров,
//но дополнительно позволяет добавить товар в корзину, оформить заказ и очисть корзину
public class ShoppingCart extends Products {

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addToCart(Product product) {
        this.products.add(product);
    }

    public void confirmOrder() {
        if (this.products.size() > 0) {
            Products newOrder = new Products(this.products);
            Orders.get().addOrder(newOrder);
        } else {
            System.out.println("Невозможно сделать заказ, корзина пуста");
        }
    }

    public void emptyCart() {
        products.clear();
    }
}
