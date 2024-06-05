
import java.util.ArrayList;
import java.util.Scanner;

public class ElectricityBillingSystem {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static final double TARIFF_RATE = 0.15; // Example rate per kWh

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nElectricity Billing System");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Enter Meter Reading");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    viewCustomers();
                    break;
                case 3:
                    enterMeterReading(scanner);
                    break;
                case 4:
                    generateBill(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Customer newCustomer = new Customer(customerId, name, address);
        customers.add(newCustomer);
        System.out.println("Customer added successfully.");
    }

    private static void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
                System.out.println();
            }
        }
    }

    private static void enterMeterReading(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            System.out.print("Enter Current Meter Reading: ");
            double currentReading = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            customer.setLastReading(customer.getCurrentReading());
            customer.setCurrentReading(currentReading);

            System.out.println("Meter reading updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void generateBill(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            double usage = customer.getCurrentReading() - customer.getLastReading();
            double billAmount = usage * TARIFF_RATE;

            System.out.println("Generating bill for " + customer.getName());
            System.out.println("Usage: " + usage + " kWh");
            System.out.println("Amount Due: $" + billAmount);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(ArrayList<Customer> customers) {
        ElectricityBillingSystem.customers = customers;
    }

    public static double getTariffRate() {
        return TARIFF_RATE;
    }
}
