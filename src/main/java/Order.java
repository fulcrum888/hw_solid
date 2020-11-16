public class Order {
    private Products products;
    private int orderNumber;
    private Status status;

    public Order(Products products, int orderNumber) {
        this.products = products;
        this.status = Status.CONFIRMED;
        this.orderNumber = orderNumber;
    }

    public double getTotalCost() {
        double result = 0;
        for (Product product : products.getProducts()) {
            result = result + product.getPrice();
        }
        return result;
    }

    public Status cancelOrder() {
        if (status == Status.CONFIRMED) {
            status = Status.CANCELED;
        }
        return status;
    }

    public Status payOrder(){
        if (status == Status.CONFIRMED) {
            status = Status.SEND;
        }
        return status;
    }

    public void repeatOrder() {
        Products newOrder = new Products();
        newOrder.getProducts().addAll(products.getProducts());
        Orders.get().addOrder(newOrder);
    }

    public Status returnOrder() {
        if (status == Status.SEND) {
            status = Status.RETURNED;
        }
        return status;
    }

    public Products getProducts() {
        return products;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Заказ №")
                .append(orderNumber)
                .append(":\n")
                .append(products.toString())
                .append("Статус: ")
                .append(status)
                .append("\n");
        return result.toString();
    }
}
