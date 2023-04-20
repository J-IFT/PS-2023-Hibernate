package b3.hibernate;
import java.util.Scanner;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static SessionFactory sessionFactory;

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu :");
            System.out.println("1. Créer un produit");
            System.out.println("2. Modifier un produit");
            System.out.println("3. Supprimer un produit");
            System.out.println("4. Lister les produits");
            System.out.println("5. Quitter");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createProduct();
                    break;
                case "2":
                    updateProduct();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "4":
                    listProducts();
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer");
                    break;
            }
        }
        scanner.close();
        System.exit(0);
    }
    private static void createProduct(){
        System.out.println("On ajoute");
    }
    private static void updateProduct(){
        System.out.println("On modifie");
    }
    private static void deleteProduct(){
        System.out.println("On supprime");
    }
    private static void listProducts(){
        System.out.println("On regarde");
    }

}
