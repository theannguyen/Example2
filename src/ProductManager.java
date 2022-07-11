import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductManager {
    ArrayList<Product> products;
    Scanner scanner = new Scanner(System.in);

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void writeFile(ArrayList<Product> list){
        File file = new File("Product.csv");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Product product : list){
                writer.write(product.getId() + ", " + product.getName() + ", " + product.getPrice() + ", " +
                        product.getAmount() + ", " + product.getDescribe() + "\n");
            }
            writer.close();
        }catch (Exception e){
            System.err.println("Không tìm thấy file yêu cầu !!!");
        }
    }


    public ArrayList<Product> readFile(){
        File file = new File("Product.csv");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String result;
            while ((result = reader.readLine()) != null){
                String [] strings = result.split(", ");
                Product product = new Product(Integer.parseInt(strings[0]),strings[1],Integer.parseInt(strings[2]),
                        Integer.parseInt(strings[3]),strings[4]);
                products.add(product);
            }
        }catch (Exception e){
            System.err.println("Không tìm thấy file yêu cầu !!!");
        }
        return null;
    }

    public void addProduct(){
        Product product = creatProduct();
        products.add(product);
    }

    public Product creatProduct(){
        try{
            int id;
            do {
                System.out.println("Nhập mã sản phẩm: ");
                id = Integer.parseInt(scanner.nextLine());
            }while (checkID(id));
            System.out.println("Nhập tên sản phẩm: ");
            String name = scanner.nextLine();
            System.out.println("Nhập giá sản phẩm: ");
            int price = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập số lượng sản phẩm: ");
            int amount = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập mô tả cho sản phẩm: ");
            String describe = scanner.nextLine();
            return  new Product(id,name,price,amount,describe);
        }catch (InputMismatchException e){
            System.out.println("---------------------------------");
            System.err.println("Bạn đã nhập sai kiểu dữ liệu. Vui lòng tạo lại");
            creatProduct();
        }
        return null;
    }


    public Product getProductById(int id){
        for (Product product : products){
            if (id == product.getId()){
                return product;
            }
        }
        return null;
    }

    public void update(Product product){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()){
                products.set(i,product);
            }
        }
        try {
            writeFile(products);
        }catch (Exception e){
            System.err.println("Không lưu được file !!!");
        }
    }

    public void updateProductByID(){
        displayAll();
        try{
            System.out.println("Nhập mã sản phẩm bạn cần cập nhật: ");
            int id = Integer.parseInt(scanner.nextLine());
            Product product = getProductById(id);
            if (product != null){
                System.out.println("Nhập mã sản phẩm cần sửa: ");
                int idProduct = Integer.parseInt(scanner.nextLine());
                product.setId(idProduct);
                System.out.println("Nhập tên cần sửa: ");
                String name = scanner.nextLine();
                product.setName(name);
                System.out.println("Nhập giá sản phẩm cần sửa: ");
                int price = Integer.parseInt(scanner.nextLine());
                product.setPrice(price);
                System.out.println("Nhập số lượng sản phẩm cần sửa: ");
                int amount = Integer.parseInt(scanner.nextLine());
                product.setAmount(amount);
                System.out.println("Nhập mô tả cho sản phẩm: ");
                String describe = scanner.nextLine();
                product.setDescribe(describe);
                update(product);
            }else {
                System.out.println("Không tìm thấy mã sản phẩm theo yêu cầu !!!");
            }
        }catch (InputMismatchException e){
            System.err.println("Nhập sai kiểu dữ liệu. Vui lòng nhập lại !!!");
            updateProductByID();
        }

    }

    public void deleteProductByID(){
        Main main = new Main();
        String check = "";
        displayAll();
        try {
            System.out.println("Nhập mã sản phẩm bạn cần xóa: ");
            String id = scanner.nextLine();
            Product product = getProductById(Integer.parseInt(id));
            if (product != null){
                System.out.println("Xóa sản phẩm theo mã sản phẩm...");
                System.out.println("------------------------------------");
                System.out.println("Bạn có muốn xóa hay không ?");
                System.out.println("Y. Có");
                System.out.println("N. Không");
                String choice = scanner.nextLine();
                if (choice.equals("Y")){
                    products.remove(product);
                    System.out.println(" Bạn đã xóa sản phẩm thành công !!!");
                    displayAll();
                }
            }else {
                System.out.println("Không tìm thấy mã sản phẩm theo yêu cầu !!!");
                deleteProductByID();
            }
        }catch (InputMismatchException e){
            System.out.println("------------------------------------");
            System.err.println("Bạn nhập sai kiểu dữ liệu !!!");
            deleteProductByID();
        }catch (Exception e){
            System.out.println("------------------------------------");
            System.err.println("Bạn đã thoát khỏi phần Xóa của chương trình !!!");
            main.Menu();
        }
    }


    Comparator<Product> compareUp = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return (int) (o1.getPrice() - o2.getPrice());
        }
    } ;

    Comparator<Product> compareDown = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return (int) (o2.getPrice() - o1.getPrice());
        }
    } ;


    // Sắp xếp tăng dần
    public void displayUp(){
        System.out.println("Sản phẩm được sắp xếp tăng dần: ");
        products.sort(compareUp);
        displayAll();
    }

    public void displayDown(){
        System.out.println("Sản phẩm được sắp xếp giảm dần: ");
        products.sort(compareDown);
        displayAll();
    }


    // Hiển thị sản phẩm đắt nhất.

    public void displayMax(){
        Product productMax = products.get(0);
        for (Product product : products){
            if (productMax.getPrice() < product.getPrice()){
                productMax = product;
            }
        }
        System.out.println(productMax);
    }

    public void displayAll(){
        for (Product product : products){
            System.out.println(product);
        }
    }

    public boolean checkID(int id){
        for (Product product : products){
            if (product.getId() == id ){
                return true;
            }
        }
        return false;
    }
}