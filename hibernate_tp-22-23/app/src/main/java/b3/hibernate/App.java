package b3.hibernate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import org.hibernate.query.Query;
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
        System.out.println("Modification d'un produit");
        System.out.println("-------------------------");
        System.out.println("Entrez l'ID du produit à modifier (pour le savoir, il faut prendre l'ordre chronologique dans lequel vous avez créé vos produits. par exemple : j'ai créé patate / radis / pizza, alors dans l'ordre ça donne 1 / 2 / 3) :");
        int productId = scanner.nextInt();
        scanner.nextLine(); // consommer la ligne vide laissée par nextInt()

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Products products = session.get(Products.class, productId);
            if (products != null) {
                System.out.println("Entrez le nouveau nom (ou appuyez sur entrée pour ne pas le modifier) :");
                String nom = scanner.nextLine();
                if (!nom.isEmpty()) {
                    products.setNom(nom);
                }
                //System.out.println("Entrez le nouveau code (ou appuyez sur entrée pour ne pas le modifier) :");
                //String code = scanner.nextLine();
                //if (!code.isEmpty()) {
                //    products.setCode(code);
                //}
                System.out.println("Entrez la nouvelle quantité (ou appuyez sur entrée pour ne pas la modifier) :");
                String quantiteStr = scanner.nextLine();
                if (!quantiteStr.isEmpty()) {
                    int quantite = Integer.parseInt(quantiteStr);
                    products.setQuantite(quantite);
                }
                System.out.println("Entrez la nouvelle date de péremption (ou appuyez sur entrée pour ne pas la modifier, format: yyyy-mm-dd) :");
                String datePeremptionStr = scanner.nextLine();
                if (!datePeremptionStr.isEmpty()) {
                    LocalDate datePeremption = LocalDate.parse(datePeremptionStr);
                    products.setDatePeremption(datePeremption);
                }
                session.update(products);
                transaction.commit();
                System.out.println("Le produit a été modifié avec succès !");
            } else {
                System.out.println("Le produit avec l'ID " + productId + " n'existe pas.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la modification du produit : " + e.getMessage());
        }
    }
    private static void deleteProduct(){
        System.out.println("Suppression d'un produit");
        System.out.println("-----------------------");
        System.out.println("Entrez l'ID du produit à supprimer (pour le savoir, il faut prendre l'ordre chronologique dans lequel vous avez créé vos produits. par exemple : j'ai créé patate / radis / pizza, alors dans l'ordre ça donne 1 / 2 / 3)");
        int productId = scanner.nextInt();
        scanner.nextLine(); // consommer la ligne vide laissée par nextInt()

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Products products = session.get(Products.class, productId);
            if (products != null) {
                session.delete(products);
                transaction.commit();
                System.out.println("Le produit a été supprimé avec succès !");
            } else {
                System.out.println("Le produit avec l'ID " + productId + " n'existe pas.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la suppression du produit : " + e.getMessage());
        }
    }
    private static void listProducts(){
        System.out.println("Liste des produits :");
        System.out.println("--------------------");

        try (Session session = sessionFactory.openSession()) {
            Query<Products> query = session.createQuery("from Products", Products.class);
            List<Products> productsList = query.list();

            for (Products products : productsList) {
                System.out.println("Nom : " + products.getNom());
                System.out.println("Code : " + products.getCode());
                System.out.println("Quantité : " + products.getQuantite());
                System.out.println("Date de péremption : " + products.getDatePeremption());
                System.out.println("----------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la récupération des produits : " + e.getMessage());
        }
    }
}
