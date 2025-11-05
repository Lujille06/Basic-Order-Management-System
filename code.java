import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main
{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Order order = new Order();
		int orm_operation;
		boolean repetition = true;

		while(repetition) {
			try {
				System.out.println("-------------------------------");
				System.out.println("    Order Management System    ");
				System.out.println("-------------------------------");
				System.out.println("1. Add order");
				System.out.println("2. See order");
				System.out.println("3. Buy order");
				System.out.println("4. Exit");
				System.out.print("Choose an operation: ");
				orm_operation = scanner.nextInt();
				scanner.nextLine();

				switch (orm_operation) {
				case 1:
					System.out.println("--- Available Items ---");
					System.out.println("1. Laptop ($11,000.99)");
					System.out.println("2. Iphone ($5,999.98)");
					System.out.println("3. Earphone ($3,000.00)");
					System.out.print("Select an Item to add in your cart (1-3): ");
					int addOrder = scanner.nextInt();
					scanner.nextLine();

					switch(addOrder) {
					case 1:
						order.addProduct("Laptop", 11000.99);

						break;
					case 2:
						order.addProduct("Iphone", 5999.98);

						break;
					case 3:
						order.addProduct("Earphone", 3000.00);
						break;
					default:
						System.out.println("[Invalid Order]");
						break;
					}
					break;

				case 2:
					System.out.println("--- Your Cart ---");
					order.displayOrder();
					break;
				case 3:
					if(order.isEmpty()) {
						System.out.println("Your cart is empty!");
						break;
					}
					System.out.println("--- Total Order ---");
					order.displayOrder();
					System.out.printf("Total: %,.2f%n", order.calculateTotal());
					order.clearCart();
					System.out.println("[Purchased Complete!]");
					break;
				case 4:
					System.out.println("[Thank you for visiting!]");
					repetition = false;
					break;
				default:
					System.out.println("[Invalid Operation]");
					break;
				}
				System.out.println();
			} 
      catch(InputMismatchException e) {
				System.out.println("[Error] Enter a valid operation!\n");
				scanner.nextLine();
			}
		}


	}
}

class Order {
	private ArrayList<Product> productList;

	Order() {
		this.productList = new ArrayList<>();
	}

	public void addProduct(String name, double price) {
		this.productList.add(new Product(name, price));
		System.out.println("[Order added...]");
	}

	public boolean isEmpty() {
		return this.productList.isEmpty();
	}
	public void clearCart() {
		this.productList.clear();
	}

	public void displayOrder() {
		if(this.productList.isEmpty()) {
			System.out.println("Your cart is empty!");
			return;
		} else {
			for(Product products : productList) {
				System.out.printf("- %s ($%,.2f)%n", products.getName(), products.getPrice());
			}
		}

	}

	public double calculateTotal() {
		double total = 0;
		for(Product products : productList) {
			total += products.getPrice();
		}
		return total;
	}

}

class Product {
	private String name;
	private double price;

	Product(String name, double price) {
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
}

