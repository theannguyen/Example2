
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    ProductManager manager = new ProductManager();
    Scanner scanner = new Scanner(System.in);

    public Main() {
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.Menu();
    }
    public  void Menu(){
        try {
            String choice;
            do {
                System.out.println("CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM");
                System.out.println("1. Xem danh sách");
                System.out.println("2. Thêm mới");
                System.out.println("3. Cập nhật");
                System.out.println("4. Xóa");
                System.out.println("5. Sắp xếp");
                System.out.println("6. Tìm kiếm sản phẩm đắt nhất");
                System.out.println("7. Đọc từ file");
                System.out.println("8. Ghi vào file");
                System.out.println("9. Thoát");
                choice = scanner.nextLine();
                switch (choice){
                    case "1":
                        manager.displayAll();
                        break;
                    case "2":
                        manager.addProduct();
                        break;
                    case "3":
                        manager.updateProductByID();
                        break;
                    case "4":
                        manager.deleteProductByID();
                        break;
                    case "5":
                        displaySort();
                        break;
                    case "6":
                        manager.displayMax();
                        break;
                    case "7":

                    case "8":

                }
            }while (!choice.equals("9"));
        }catch (InputMismatchException e){
            System.out.println("Nhập sai lựa chọn vui lòng nhập lại !!!");
            Menu();
        }
    }


    public void displaySort(){
        try {
            String choice;
            do {
                System.out.println("Sắp xếp sản phẩm");
                System.out.println("1. Giá sản phẩm tăng dần.");
                System.out.println("2. Giá sản phẩm giảm dần.");
                System.out.println("9. Trở lại Menu");
                choice = scanner.nextLine();
                switch (choice){
                    case "1":
                        manager.displayUp();
                        break;
                    case "2":
                        manager.displayDown();
                        break;
                    case "9":
                        Menu();
                        break;
                }
            }while (true);
        }catch (InputMismatchException e){
            System.out.println("Nhập sai lựa chọn vui lòng nhập lại !!!");
            displaySort();
        }
    }


}