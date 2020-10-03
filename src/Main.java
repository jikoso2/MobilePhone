import java.io.*;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone();

    public static void main(String[] args) throws IOException {
        boolean quit = false;
        int choice;
        String line;

        try (BufferedReader fileReader = new BufferedReader(new FileReader("contacts.txt"))) {
            while ((line = fileReader.readLine()) != null) {
                String[] Contacts = line.split(" ");
                mobilePhone.add(new Contact(Integer.parseInt(Contacts[1]), Contacts[0]));
            }

        }

        printInstructions();

        while (!quit) {
            System.out.print("Enter your choice: (9 to show available actions)");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    quit = true;
                    System.out.println("Shutting down");
                    scanner.close();
                    break;
                case 2:
                    printContactList();
                    break;
                case 3:
                    addContact();
                    break;
                case 4:
                    modifyContact();
                    break;
                case 5:
                    removeContact();
                    break;
                case 6:
                    searchContact();
                    break;
                case 9:
                    printInstructions();
                default:
                    System.out.println("Wrong option");
            }
        }
    }


    private static void printContactList() {
        mobilePhone.print();
    }

    private static void addContact() {
        int ContactNumber;

        while(true) {
            System.out.print("Please enter the telephone number: ");
            boolean isInt = scanner.hasNextInt();
            if (isInt) {
                ContactNumber = scanner.nextInt();
                scanner.nextLine();
                if (ContactNumber >= 100_000_000 && ContactNumber <= 999_999_999)
                    break;
                else if (ContactNumber <= 100_000_000)
                    System.out.println("Too short number");
                else
                    System.out.println("Too long number");
            }
            else {
                System.out.println("Wrong number");
                scanner.nextLine();
            }
        }

        System.out.print("Please enter the contact name: ");
        String ContactName = scanner.nextLine();
        mobilePhone.add(new Contact(ContactNumber,ContactName));
    }


    private static void modifyContact() {
        System.out.print("Enter name to modify contact: ");
        String ContactName = scanner.nextLine();
        if(mobilePhone.isExist(ContactName)) {
            System.out.println("What you wanna modify? 1 - contact number, 2 - contact name");
            int number = scanner.nextInt();
            scanner.nextLine();

            if (number == 1) {
                System.out.print("Enter new number: ");
                int newNumber = scanner.nextInt();
                mobilePhone.modifyNumber(ContactName, newNumber);
            } else if (number == 2) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                mobilePhone.modifyName(ContactName,newName);
            } else
                System.out.println("Wrong option, select 1 or 2");
        }
        else
            System.out.println("Contact doesn't exist");
    }

    private static void removeContact() {
        System.out.print("What you wanna remove ? Enter name: ");
        String ContactName = scanner.nextLine();
        mobilePhone.remove(ContactName);
    }

    private static void searchContact() {
        System.out.print("Please enter the contact name: ");
        String ContactName = scanner.nextLine();
        mobilePhone.search(ContactName);
    }


    public static void printInstructions() {
        System.out.println("\nPress ");
        System.out.println("\t 1 - To quit application.");
        System.out.println("\t 2 - To print contacts.");
        System.out.println("\t 3 - To add contact.");
        System.out.println("\t 4 - To modify contact.");
        System.out.println("\t 5 - To remove contact.");
        System.out.println("\t 6 - To search contact.");
        System.out.println("\t 9 - To print instructions.");
    }

}
