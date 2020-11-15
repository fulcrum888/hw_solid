import java.util.ArrayList;
import java.util.List;

public class Orders implements ConvertibleToStringArray {
    private static Orders instance = null;
    private List<Order> orders;
    private int orderNumber;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void addOrder(Products products) {
        Order order = new Order(products, nextOrderNumber());
        orders.add(order);
    }

    public int nextOrderNumber() {
        return ++orderNumber;
    }

    private Orders() {
        orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public static Orders get() {
        if (instance == null) instance = new Orders();
        return instance;
    }

    @Override
    public String[] getAsArray() {
        String[] result = new String[orders.size()];
        for (int i = 0; i < orders.size(); i++) {
            result[i] = "Заказ №" + orders.get(i).getOrderNumber() + " "
                    + orders.get(i).getTotalCost() + "р. - "
                    + orders.get(i).getStatus();
        }
        return result;
    }
}
