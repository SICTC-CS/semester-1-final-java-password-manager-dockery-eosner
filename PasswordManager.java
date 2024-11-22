import java.util.*; // Imports all classes from java.util

class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Password: " + password;
    }

}

public class PasswordManager { // Renamed class to match Java conventions
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, List<Account>> categories = new HashMap<>();
    private static final Random random = new Random();

    public static void main(String[] args) { // Main method
        System.out.println("Welcome to the Java Password Manager.");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a User");
            System.out.println("2. Add an Account");
            System.out.println("3. Add an Account to a Category");
            System.out.println("4. Delete an Account");
            System.out.println("5. View Categories");
            System.out.println("6. Modify an Account");
            System.out.println("7. Save to .txt File");
            System.out.println("8. Generate a Password");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    addAccount();
                    break;
                case 3:
                    addAccountToCategory();
                    break;
                case 4:
                    deleteAccount();
                    break;
                case 5:
                    viewCategories();
                    break;
                case 6:
                    modifyAccount();
                    break;
                case 7:
                    saveToFile();
                    break;
                case 8:
                    System.out.println("Generated Password: " + generatePassword());
                    break;
                case 9:
                    System.out.println("Exiting Password Manager. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addUser() {
        System.out.println("To be added.");
    }

    private static void addAccount() {
        System.out.println("Enter category name: ");
        String category = scanner.nextLine();

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        categories.putIfAbsent(category, new ArrayList<>());
        categories.get(category).add(new Account(username, password));

        System.out.println("Account added successfully to category: " + category);
    }

    private static void addAccountToCategory() {
        System.out.println("Enter category name to add an account: ");
        String category = scanner.nextLine();

        if (!categories.containsKey(category)) {
            System.out.println("Category does not exist.");
            return;
        }

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        categories.get(category).add(new Account(username, password));
        System.out.println("Account added to category: " + category);
    }

    private static void deleteAccount() {
        System.out.println("Enter category name to delete an account from: ");
        String category = scanner.nextLine();

        if (!categories.containsKey(category)) {
            System.out.println("Category does not exist.");
            return;
        }

        System.out.println("Enter username of account to delete: ");
        String username = scanner.nextLine();

        List<Account> accounts = categories.get(category);
        accounts.removeIf(account -> account.getUsername().equals(username));

        System.out.println("Account deleted (if it existed).");
    }

    private static void viewCategories() {
        System.out.println("Categories and Accounts:");
        for (var entry : categories.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            for (Account account : entry.getValue()) {
                System.out.println("\t" + account);
            }
        }
    }

    private static void modifyAccount() {
        System.out.println("Enter category name to modify an account: ");
        String category = scanner.nextLine();

        if (!categories.containsKey(category)) {
            System.out.println("Category does not exist.");
            return;
        }

        System.out.println("Enter username of account to modify: ");
        String username = scanner.nextLine();

        for (Account account : categories.get(category)) {
            if (account.getUsername().equals(username)) {
                System.out.println("Enter new username: ");
                account.setUsername(scanner.nextLine());

                System.out.println("Enter new password: ");
                account.setPassword(scanner.nextLine());

                System.out.println("Account updated successfully.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void saveToFile() {
        System.out.println("Save to file functionality here...");
    }

    private static String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }
}