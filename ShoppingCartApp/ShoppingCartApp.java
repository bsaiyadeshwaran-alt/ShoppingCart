import java.util.Scanner;

class Item {
    String name;
    double price;
    int quantity;

    Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotalPrice() {
        return price * quantity;
    }

    public String toString() {
        return name + " - Rs." + price + " x " + quantity + " = Rs." + getTotalPrice();
    }
}

class ShoppingCart {
    private Item[] cart;
    private int itemCount;

    public ShoppingCart(int size) {
        cart = new Item[size];
        itemCount = 0;
    }

    public void addItem(String name, double price, int quantity) throws Exception {
        if (itemCount >= cart.length) {
            throw new Exception("Cart is full. Cannot add more items.");
        }
        if (price < 0 || quantity <= 0) {
            throw new IllegalArgumentException("Price must be non-negative and quantity must be greater than zero.");
        }
        cart[itemCount++] = new Item(name, price, quantity);
        System.out.println("Item added successfully.");
    }

    public void displayCart() {
        if (itemCount == 0) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n--- Shopping Cart ---");
        for (int i = 0; i < itemCount; i++) {
            System.out.println((i + 1) + ". " + cart[i]);
        }
        System.out.printf("Total: Rs.%.2f%n", calculateTotal());
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += cart[i].getTotalPrice();
        }
        return total;
    }
}

public class ShoppingCartApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart(5); // max 5 items
        int choice = 0;

        do {
            System.out.println("\n--- Shopping Cart Menu ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout (Exit)");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        try {
                            System.out.print("Enter item name: ");
                            String name = scanner.nextLine();

                            System.out.print("Enter item price: ");
                            double price = Double.parseDouble(scanner.nextLine());

                            System.out.print("Enter quantity: ");
                            int quantity = Integer.parseInt(scanner.nextLine());

                            cart.addItem(name, price, quantity);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter numeric values for price and quantity.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        cart.displayCart();
                        break;

                    case 3:
                        System.out.println("Final cart:");
                        cart.displayCart();
                        System.out.println("Thank you for shopping!");
                        break;

                    default:
                        System.out.println("Invalid menu choice. Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Please enter a valid number.");
            }

        } while (choice != 3);

        scanner.close();
    }
}
