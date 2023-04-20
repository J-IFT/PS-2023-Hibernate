package b3.hibernate;
import java.time.LocalDate;
import java.util.Scanner;

import b3.hibernate.model.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        System.out.println("Ajout d'un produit");
        System.out.println("-------------------");
        System.out.println("Entrez le nom du produit :");
        String nom = scanner.nextLine();
        System.out.println("Entrez le code du produit :");
        String code = scanner.nextLine();
        System.out.println("Entrez la quantité :");
        int quantite = scanner.nextInt();
        scanner.nextLine(); // consommer la ligne vide laissée par nextInt()
        System.out.println("Entrez la date de péremption (format: yyyy-mm-dd) :");
        String datePeremptionStr = scanner.nextLine();
        LocalDate datePeremption = LocalDate.parse(datePeremptionStr);

        Products products = new Products();
        products.setNom(nom);
        products.setCode(code);
        products.setQuantite(quantite);
        products.setDatePeremption(datePeremption);

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(products);
            transaction.commit();
            System.out.println("Le produit a été créé avec succès !");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la création du produit : " + e.getMessage());
        }
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
