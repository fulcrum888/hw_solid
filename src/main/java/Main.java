public class Main {
    final static String[] MAIN_MENU_ITEMS = {
            "Список доступных товаров",
            "Добавить товар в корзину",
            "Корзина",
            "Заказы"
    };
    final static String[] ORDER_MENU_ACTION_ITEMS = {
            "Отменить заказ",
            "Оплатить заказ",
            "Повторить заказ",
            "Вернуть заказ",
    };
    final static String[] CART_MENU_ITEMS = {
            "Оформить заказ",
            "Очистить корзину"
    };
    final static int EXIT_CODE = 0;

    public static void main(String[] args) {
        //Dependency Inversion Principle
        //В логике программы мы не зависим от реализации класса загрузки тестовых данных, а от интерфеса загрузчика
        //в будущем TestProductLoader можно будет заменить, например, на загрузку из файла или БД
        ProductsDataLoader dataLoader = new TestProductsLoader();
        Stock.get().loadToStock(dataLoader.loadProducts());

        Menu mainMenu = new Menu(MAIN_MENU_ITEMS,
                "Выберите действие или введите " + EXIT_CODE + " для выхода\n",
                EXIT_CODE);

        int mainMenuItem;
        do {
            mainMenuItem = mainMenu.readMenuItem();
            switch (mainMenuItem) {
                case 1 -> showStock();
                case 2 -> addProductToCart();
                case 3 -> shoppingCartMenu();
                case 4 -> ordersMenu();
            }
        } while (mainMenuItem != EXIT_CODE);
    }

    static void showStock() {
        System.out.println("Список доступных товаров");
        System.out.println(Stock.get());
    }

    static void addProductToCart() {
        String[] productItems = Stock.get().getAsArray();
        Menu productsMenu = new Menu(productItems,
                "Введите номер товара или " + EXIT_CODE + " для выхода\n", EXIT_CODE);
        int stockIndex = productsMenu.readMenuItem();
        if (stockIndex != 0) {
            ShoppingCart.get().addToCart(Stock.get().getProducts().get(stockIndex-1));
        }
    }

    static void shoppingCartMenu() {
        ShoppingCart cart = ShoppingCart.get();
        if (cart.getProducts().size() == 0) {
            System.out.println("Ваша корзина пуста\n");
        } else {
            System.out.println("Ваша корзина:");
            System.out.println(cart);
            Menu cartMenu = new Menu(CART_MENU_ITEMS,
                    "Выберите действие или введите " + EXIT_CODE + " для выхода\n", EXIT_CODE);
            switch (cartMenu.readMenuItem()) {
                case 1:
                    cart.confirmOrder();
                    cart.emptyCart();
                    break;
                case 2:
                    cart.emptyCart();
            }
        }
    }

    static void ordersMenu() {
        Orders orders = Orders.get();
        if (orders.getOrders().size() > 0) {
            Menu ordersItems = new Menu(orders.getAsArray(),
                    "Введите номер заказа из списка или " + EXIT_CODE + " для выхода\n", EXIT_CODE);
            int orderItem = ordersItems.readMenuItem();
            switch (orderItem) {
                case EXIT_CODE:
                    break;
                default:
                    System.out.println(orders.getOrders().get(orderItem - 1).toString());
                    Menu ordersActionMenu = new Menu(ORDER_MENU_ACTION_ITEMS,
                            "Выберите действие или введите " + EXIT_CODE + " для выхода\n", EXIT_CODE);
                    int orderActionItem = ordersActionMenu.readMenuItem();
                    switch (orderActionItem) {
                        case 0:
                            break;
                        case 1:
                            if (orders.getOrders().get(orderItem - 1).cancelOrder() == Status.CANCELED) {
                                System.out.println("Заказ отменен");
                            } else {
                                System.out.println("Нельзя отменить отправленный или отменённый заказ");
                            }
                            System.out.println();
                            break;
                        case 2:
                            if (orders.getOrders().get(orderItem - 1).payOrder() == Status.SEND) {
                                System.out.println("Заказ оплачен и отправлен");
                            } else {
                                System.out.println("Можно оплатить только подтвержённый заказ");
                            }
                            System.out.println();
                            break;
                        case 3:
                            orders.getOrders().get(orderItem - 1).repeatOrder();
                            System.out.println("Заказ создан");
                            break;
                        case 4:
                            if (orders.getOrders().get(orderItem - 1).returnOrder() == Status.RETURNED) {
                                System.out.println("Заказ возвращен");
                            } else {
                                System.out.println("Нельзя вернуть неотправленный заказ");
                            }
                            System.out.println();
                            break;
                    }
            }
        } else {
            System.out.println("У вас еще нет заказов\n");
        }
    }
}