import java.util.*;

public class data {
    private static List<Map<String, String>> data = new ArrayList<>();
    private static List<String> availableFields = Arrays.asList("ID", "Name", "Department", "Salary");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("\nDynamic Report Generation System");
            System.out.println("1. Enter New Data");
            System.out.println("2. Generate Custom Report");
            System.out.println("3. Display Data");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    enterNewData(sc);
                    break;
                case 2:
                    generateCustomReport(sc);
                    break;
                case 3:
                    displayData();
                    break;
                case 4:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    private static void enterNewData(Scanner sc) {
        Map<String, String> newRow = new HashMap<>();
        sc.nextLine();

        for (String field : availableFields) {
            System.out.print("Enter " + field + ": ");
            String value = sc.nextLine();
            newRow.put(field, value);
        }

        data.add(newRow);
        System.out.println("New data added successfully.");
    }

    private static void displayData() {
        if (data.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        System.out.println("Available Data:");
        for (Map<String, String> row : data) {
            System.out.println(row);
        }
    }

    private static void generateCustomReport(Scanner sc) {
        if (data.isEmpty()) {
            System.out.println("No data available to generate a report.");
            return;
        }

        System.out.println("Available fields: " + availableFields);
        System.out.println("Enter the fields you want in the report (comma-separated, e.g., ID, Name): ");
        sc.nextLine();
        String inputFields = sc.nextLine();
        String[] selectedFields = inputFields.split(",");

        List<String> fieldsToInclude = new ArrayList<>();
        for (String field : selectedFields) {
            field = field.trim();
            if (availableFields.contains(field)) {
                fieldsToInclude.add(field);
            } else {
                System.out.println("Invalid field: " + field);
            }
        }

        if (fieldsToInclude.isEmpty()) {
            System.out.println("No valid fields selected. Please try again.");
            return;
        }

        generateReport(fieldsToInclude);
    }

    private static void generateReport(List<String> fieldsToInclude) {
        System.out.println("Generating Custom Report...");
        StringBuilder report = new StringBuilder();

        for (String field : fieldsToInclude) {
            report.append(field).append("\t");
        }
        report.append("\n").append("-------------------------------------------------\n");

        for (Map<String, String> row : data) {
            for (String field : fieldsToInclude) {
                report.append(row.get(field)).append("\t");
            }
            report.append("\n");
        }

        System.out.println("\nCustom Report Generated:\n");
        System.out.println(report);
    }
}
