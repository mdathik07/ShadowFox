import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    // Constructor
    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}


public class ContactManager {
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    private static void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Contact newContact = new Contact(name, phoneNumber, email);
        contacts.add(newContact);
        System.out.println("Contact added successfully!");
    }


    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("\n---- Contact List ----");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }


    private static void updateContact() {
        viewContacts();
        System.out.print("Enter the contact number to update: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < contacts.size()) {
            Contact contact = contacts.get(index);
            System.out.print("Enter new name (current: " + contact.getName() + "): ");
            String name = scanner.nextLine();
            System.out.print("Enter new phone number (current: " + contact.getPhoneNumber() + "): ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter new email (current: " + contact.getEmail() + "): ");
            String email = scanner.nextLine();

            contact.setName(name.isEmpty() ? contact.getName() : name);
            contact.setPhoneNumber(phoneNumber.isEmpty() ? contact.getPhoneNumber() : phoneNumber);
            contact.setEmail(email.isEmpty() ? contact.getEmail() : email);

            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Invalid contact number.");
        }
    }

    private static void deleteContact() {
        viewContacts();
        System.out.print("Enter the contact number to delete: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Invalid contact number.");
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n---- Contact Management System ----");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    //updateContact();
                    break;
                case 4:
                    //deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}