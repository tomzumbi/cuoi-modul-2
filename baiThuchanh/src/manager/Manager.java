package manager;

import io.IOStudent;
import model.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Student> students = new ArrayList<>();
    public final String REGEX_STRING = "[ny]";

    //Thông tin sinh viên
    public Student infProduct() {

        System.out.println("Enter the Student's code: ");
        String code = scanner.nextLine();

        System.out.println("Enter the Student's Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Student's Age: ");
        int age = checkLoiNhap();
        while (age < 18){
            System.out.println("Please! Enter Student's Age more than 18 years old ");
            age = checkLoiNhap();
        }

        System.out.println("Enter the Student's gender: ");
        String gender = scanner.nextLine();

        System.out.println("Enter the Student's Adress: ");
        String address = scanner.nextLine();

        System.out.println("Enter the student's average score: ");
        double GPA;

        while (true) {
            try {
                GPA = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("\n" +
                        "Wrong format! Please choose again");
            }
        }

        Student product = new Student(code, name, age, gender, address, GPA);
        System.out.println(product);
        return product;
    }

    //Hiển thị
    public void show() {
        System.out.println("-----------------------------------------------List Students-----------------------------------------------");
        System.out.printf("| %-25s| %-15s| %-15s| %-15s| %-15s| %-15s", "Code", "Name", "Age", "Gender", "Adress", "Average score");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("| %-25s| %-15s| %-15s| %-15s| %-15s| %-15s", student.getCode(), student.getName(), student.getAge(), student.getGender(), student.getAddress(), student.getGPA());
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }

    }

    //Thêm
    public void add() {
        Student product = infProduct();
        students.add(product);
    }

    //Sửa
    public void update() {
            System.out.println("Nhập mã sinh viên bạn muốn sửa: ");
        String code = scanner.nextLine();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getCode().equals(code)) {
                students.set(i, infProduct());
            }
        }
    }

    //Xóa
    public void delete() {
        System.out.println("Nhập mã sinh viên bạn muốn xóa:  ");
        String code = scanner.nextLine();
        System.out.println("Bạn có chắc sẽ xóa sinh viên (yes:y hoặc no:n): ");
        String choice = validateString(REGEX_STRING);
        if (choice.equals("y")) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getCode().equalsIgnoreCase(code)) {
                    students.remove(i);
                    System.out.println("Xóa thành công");
                    break;
                }
            }
        } else {
            System.out.println("Hủy bỏ xóa!");
        }

    }

    //Sắp xếp
    public void sort() {
        int choose = 3;
        System.out.println("1. Sắp xếp điểm trung bình tăng dần.");
        System.out.println("2. Sắp xếp điểm trung bình giảm dần.");
        System.out.println("3. Thoát.");
        System.out.println("Nhập số để lựa chọn: ");
        do {
            if (choose > 3) System.out.println("Vui lòng nhập lại");
            choose = Integer.parseInt(scanner.nextLine());
        } while (choose > 3);

        switch (choose) {
            case 1:
                ascending();
                break;
            case 2:
                decrease();
                break;
            case 3:
                System.out.println("oke");
                break;
        }
    }

    //Tăng dần
    public void ascending() {
        Student temp;
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getGPA() > students.get(j).getGPA()) {
                    temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }
        show();

    }

    //Giảm dần
    public void decrease() {

        Student temp;
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getGPA() < students.get(j).getGPA()) {
                    temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }

        show();

    }


    //Đọc từ file
    public void ReadFromFile() {
        students = IOStudent.read();
        System.out.println("Đọc file thành công, chọn chức năng xem danh sách để kiểm tra.");
    }

    //Ghi từ file
    public void WriteToFile() {
        IOStudent.write(students);
        System.out.println("Ghi file thành công, chạy lại chương trình và chọn chức năng đọc file để kiểm tra.");

    }


    //Check lỗi
    public int checkLoiNhap() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("Sai định dạng! Vui lòng chọn lại");
            }
        }
        return choice;
    }

    public String validateString(String regex) {
        while (true) {
            String name = scanner.nextLine();
            if (name.matches(regex)) {
                return name;
            }
            System.err.println("Sai định dạng, vui lòng nhập lại.");
        }
    }
}
