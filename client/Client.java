import java.rmi.registry.LocateRegistry; //!! To use the LocateRegistry
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void viewProducts(Registry registry) throws Exception { //!! use for case 1 and view all products
        // Get all available products from the server
        Product[] products = {
            (Product) registry.lookup("Assault"),
            (Product) registry.lookup("LMG"),
            (Product) registry.lookup("SMG"),
            (Product) registry.lookup("Semi Pistol")
        };

        // Display information for each product
        for (Product product : products) {
            System.out.println("Name: " + product.Getname());
            System.out.println("Price: " + product.Getprice());
            System.out.println("Description: " + product.Getdesc());
            System.out.println();
        }
    }

    

    
    public static void main(String[] args) {
        try {
            // !! Here we call from the Server and get the objects from the RMI registry

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9200);

            Scanner scanner = new Scanner(System.in); // ! Ignore error here...
            int userInput = 0;
            do {    
                System.out.println("\n\n ==== Menu ==== \n");
                System.out.println("[1] Display all guns");
                System.out.println("[2] Add gun");
                System.out.println("[3] Buy gun");
                System.out.println("[0] Exit");
                System.out.print("Input command: ");
                userInput = scanner.nextInt();
                // !! Switch case
                switch (userInput) {
                    case 1:
                        System.out.println("\n\nDisplaying guns\n");
                        viewProducts(registry); 
                        break;
                    case 2:
                        System.out.println("Adding a new product to the cart:");
                        
                        break;
                    case 3:
                       System.out.println("Viewing all added products in the cart:");
                        break;
                    case 0:
                        System.out.println("Exiting...");
                    break;
                }
                // !! End of switch case

            } while (userInput != 0);

            System.out.println("Client side done...");

        } catch (Exception e) {
            System.out.println("Client side error... " + e);
        }
    }

}