package menu;


import manager.Manager;

public class MenuManager {
    Manager manager = new Manager();

    public void menu() {
        int choice;
        do {
            String menu = """
                    ______________________________________________
                    ---- MANAGER STUDENTS ----
                    YOUR CHOOSE (CONTINUE)
                    1. View Student list
                    2. Add a student
                    3. Update
                    4. Delete
                    5. Arrange
                    6. Read from file
                    7. Write from file
                    8. Exit  
                    Choose:
                    """;
            System.out.println(menu);

            choice = manager.checkLoiNhap();

            switch (choice) {
                case 1 -> manager.show();
                case 2 -> manager.add();
                case 3 -> manager.update();
                case 4 -> manager.delete();
                case 5 -> manager.sort();
                case 6 -> manager.ReadFromFile();
                case 7 -> manager.WriteToFile();
                case 8 -> System.exit(0);
                default -> System.out.println("Chọn lại!");
            }
        } while (true);
    }
}


