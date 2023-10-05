package ra.run;

import ra.entity.Product;

import java.util.*;

import ra.entity.Categories;

public class ShopManagement {
    public static List<Categories> categories = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        shopManagementMenu();
    }

    public static void shopManagementMenu() {
        do {
            System.out.println("********************SHOP MANAGEMENT********************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Nhập vào lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    catalogManagementMenu();
                    break;
                case 2:
                    productManagementMenu();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Nhập vào lựa chọn từ 1-3");
            }
        } while (true);

    }

    //CATEGORY
    public static void catalogManagementMenu() {
        do {
            System.out.println("********************CATALOG MANAGEMENT********************");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh mục theo mã danh mục (Danh mục chưa chứa sản phẩm)");
            System.out.println("5. Thoát (Quay lại Shop Management");
            System.out.print("Nhập vào lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    showCategory();
                    break;
                case 3:
                    updateCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:
                    shopManagementMenu();
                    break;
                default:
                    System.err.println("Nhập vào lựa chọn từ 1-5");
            }
        } while (true);
    }

    public static void addCategory() {
//        catalogId > 0, không trùng lặp
        boolean isExit = true;
        boolean isDuplicate;
        int catalogId;
        String catalogName;
//        TODO: xem lại logic để set status
        boolean status = false;
        do {
            System.out.println("Nhập vào catalogId:");
            catalogId = Integer.parseInt(sc.nextLine());
//            check > 0
            if (catalogId > 0) {
//                check for duplication
                isDuplicate = false;
                for (Categories category : categories
                ) {
                    if (category.getCatalogId() == catalogId) {
                        isDuplicate = true;
                    }
                }
                if (isDuplicate) {
                    System.err.println("catalogId đã tồn tại");
                } else {
                    break;
                }

            } else {
                System.err.println("catalogId phải là một số dương");
            }
        } while (isExit);
//        catalogName không trùng lặp
        do {
            System.out.println("Nhập vào catalogName:");
            catalogName = sc.nextLine();
//            check không trùng lặp
            isDuplicate = false;
            for (Categories category : categories
            ) {
                if (category.getCatalogName().equals(catalogName)) {
                    isDuplicate = true;
                }
            }
            if (isDuplicate) {
                System.err.println("catalogName đã tồn tại");
            } else {
                break;
            }

        } while (isExit);
        //    status
        do {
            System.out.println("Nhập vào trạng thái danh mục true: có chứa sản phẩm/false: chưa chứa sản phẩm");
            String userInput = sc.nextLine();
            if (userInput.equals("true") || userInput.equals("false")) {
                status = Boolean.parseBoolean(userInput);
                break;
            } else {
                System.err.println("Phải nhập vào là 'true' hoặc 'false'");
            }
        } while (isExit);
        Categories category = new Categories();
        category.setCatalogId(catalogId);
        category.setCatalogName(catalogName);
        category.setStatus(status);
        categories.add(category);
    }

    public static void showCategory() {
        if (categories.isEmpty()) {
            System.out.println("Danh mục trống");
        } else {
            for (Categories category : categories) {
                category.displayData();
            }
        }
    }

    public static void updateCategory() {
//        TODO: thêm vào chức năng thêm vào để bỏ qua
//        TODO: fix lỗi thêm xong phần cập nhật thì không phá vòng lặp mà lặp lại từ đầu
        int catalogId;
        boolean idFound = false;
        do {
            System.out.println("Nhập vào mã danh mục:");
            catalogId = Integer.parseInt(sc.nextLine());
            for (Categories category : categories) {
                if (catalogId == category.getCatalogId()) {
                    idFound = true;
                    break;
                }
            }
            if (idFound) {
                boolean isExit = true;
                String catalogName;
                String userInput;
                boolean isDuplicate;
//                TODO: check lại xem set = false ở đây có ổn không
                boolean status = false;
                do {
                    System.out.println("Nhập vào catalogName:");
                    catalogName = sc.nextLine();
//            check không trùng lặp
                    isDuplicate = false;
                    for (Categories category : categories
                    ) {
                        if (category.getCatalogName().equals(catalogName)) {
                            isDuplicate = true;
                        }
                    }
                    if (isDuplicate) {
                        System.err.println("catalogName đã tồn tại");
                    } else {
                        break;
                    }

                } while (isExit);
                //    status
                do {
                    System.out.println("Nhập vào trạng thái danh mục true: có chứa sản phẩm/false: chưa chứa sản phẩm");
                    userInput = sc.nextLine();
                    if (userInput.equals("true") || userInput.equals("false")) {
                        status = Boolean.parseBoolean(userInput);
                        break;
                    } else {
                        System.err.println("Phải nhập vào là 'true' hoặc 'false'");
                    }
                } while (isExit);
                for (Categories category : categories) {
                    if (catalogId == category.getCatalogId()) {
                        if (!catalogName.isEmpty()) {
                            category.setCatalogName(catalogName);
                        }
                        if (!userInput.isEmpty()) {
                            category.setStatus(status);
                        }
                    }
                }
                break;
            } else {
                System.err.println("Mã danh mục không tồn tại");
            }

        } while (true);
    }

    public static void deleteCategory() {
//        TODO: xóa các categories có status là false, khi có sản phẩm thêm vào categories này thì set là true
//        Cập nhật trạng thái các category
        for(Categories category : categories){
            int count = 0;
            for(Product product : products){
                if(product.getCatalogId() == category.getCatalogId()){
                    count++;
                }
            }
            if(count == 0){
                category.setStatus(false);
            } else {
                category.setStatus(true);
            }
        }
//        Xóa cóc categories có status là false
        int catalogId;
        System.out.println("Nhập vào mã danh mục");
        catalogId = Integer.parseInt(sc.nextLine());
        int index = -1;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCatalogId() == catalogId) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("Mã danh mục không tồn tại");
        } else {
            if (categories.get(index).isStatus()) {
                categories.remove(index);
            } else {
                System.out.println("Mã danh mục đang được sử dụng. Không thể xóa");
            }
        }
    }

    //    PRODUCT
    public static void productManagementMenu() {
        do {
            System.out.println("********************CATALOG MANAGEMENT********************");
            System.out.println("1. Thêm mới sản phẩm (Khi thêm cho phép chọn danh mục sản phẩm mà sản phẩm thuộc về)");
            System.out.println("2. Hiển thị thông tin sản phẩm");
            System.out.println("3. Cập nhật giá sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("5. Sắp xếp sản phẩm theo giá sản phẩm tăng dần");
            System.out.println("6. Sắp xếp sản phẩm theo tên tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục sản phẩm");
            System.out.println("8. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("9. Thoát");
            System.out.print("Nhập vào lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
//                    cập nhật category status
                    addProduct();
                    break;
                case 2:
                    showProduct();
                    break;
                case 3:
                    updateProductPrice();
                    break;
                case 4:
//                    cập nhật category status
                    deleteProduct();
                    break;
                case 5:
                    sortProductByPrice();
                    break;
                case 6:
                    sortProductByName();
                    break;
                case 7:
                    subTotalProductQuantityByCategory();
                    break;
                case 8:
                    searchProductByName();
                    break;
                case 9:
                    shopManagementMenu();
                    break;
                default:
                    System.err.println("Nhập vào lựa chọn từ 1-9");
            }
        } while (true);
    }

    public static void addProduct() {
        boolean isExit = true;
        boolean isDuplicate, isCategoryExists;
        String productId;
        String productName, title;
        float price;
        int catalogId;
        boolean status = false;
//        productId
//        productId có length = 5, startsWith "P", unique
        do {
            System.out.println("Nhập vào productId");
            productId = sc.nextLine();
            if (productId.length() == 5) {
                if (productId.startsWith("P")) {
                    isDuplicate = false;
                    for (Product product : products) {
                        if (product.getProductId().equals(productId)) {
                            isDuplicate = true;
                        }
                    }
                    if (isDuplicate) {
                        System.err.println("productId đã tồn tại");
                    } else {
                        break;
                    }
                } else {
                    System.err.println("Mã sản phẩm phải bắt đầu bằng chữ cái 'P'");
                }
            } else {
                System.err.println("Mã sản phẩm phải gồm 5 ký tự");
            }

        } while (isExit);
        do {
            System.out.println("Nhập vào productName:");
            productName = sc.nextLine();
            isDuplicate = false;
            for (Product product : products) {
                if (product.getProductName().equals(productName)) {
                    isDuplicate = true;
                }
            }
            if (isDuplicate) {
                System.err.println("productName đã tồn tại");
            } else {
                break;
            }
        } while (isExit);
        do {
            System.out.println("Nhập vào giá sản phẩm: ");
            price = Float.parseFloat(sc.nextLine());
            if (price > 0) {
                break;
            } else {
                System.err.println("Giá sản phẩm phải lớn hơn 0");
            }
        } while (isExit);
//        title
        System.out.println("Nhập vào tiêu đề sản phẩm");
        title = sc.nextLine();

        do {
            //        hiện danh mục mã sản phẩm để lựa chọn
            System.out.println("Vui lòng lựa chọn mã sản phẩm từ danh mục dưới đây");
            showCategory();
            System.out.println("Nhập vào mã danh mục cho sản phẩm");
            catalogId = Integer.parseInt(sc.nextLine());
            isCategoryExists = false;
            for (Categories category : categories) {
                if (category.getCatalogId() == catalogId) {
                    isCategoryExists = true;
                }

            }
            if (!isCategoryExists) {
                System.out.println("Mã danh mục chưa tồn tại. Vui lòng tạo mới mã sản phẩm trước khi tiếp tục.");
                addCategory();
                System.out.println("Đã tạo xong mã danh mục. Xin mời tiếp tục");
            } else {
                break;
            }
        } while (isExit);
//                status
        do {
            System.out.println("Nhập vào trạng thái sản phẩm true: có hàng/false: không có hàng");
            String userInput = sc.nextLine();
            if (userInput.equals("true") || userInput.equals("false")) {
                status = Boolean.parseBoolean(userInput);
                break;
            } else {
                System.err.println("Phải nhập vào là 'true' hoặc 'false'");
            }
        } while (isExit);
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setPrice(price);
        product.setTitle(title);
        product.setCatalogId(catalogId);
        product.setStatus(status);
        products.add(product);
    }

    public static void showProduct() {
        for (Product product : products) {
            product.displayData();
        }
    }

    public static void updateProductPrice() {
        String productId;
        float price;
        boolean idFound = false;
        do {
            System.out.println("Nhập vào mã sản phẩm:");
            productId = sc.nextLine();
            for (Product product : products) {
                if (product.getProductId().equals(productId)) {
                    idFound = true;
                    System.out.println("Nhập vào giá mới cho sản phẩm");
                    price = Float.parseFloat(sc.nextLine());
                    if (price > 0) {
                        product.setPrice(price);
                    } else {
                        System.out.println("Giá sản phẩm phải lớn hơn 0");
                    }
                    break;
                }
            }
            if (!idFound) {
                System.out.println("Không tồn tại mã sản phẩm này");
            }
        } while (!idFound);
    }

    public static void deleteProduct() {
        String productId;
        int deleteIndex = -1;
        do {
            System.out.println("Nhập vào mã sản phẩm:");
            productId = sc.nextLine();

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductId().equals(productId)) {
                    deleteIndex = i;
                    break;
                }
            }
            if (deleteIndex == -1) {
                System.out.println("Không tồn tại mã sản phẩm này");
            } else {
                products.remove(deleteIndex);
            }
        } while (deleteIndex == -1);
    }
    public static void sortProductByPrice(){
        Comparator<Product> comparator = new Comparator<Product>(){

            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() - o2.getPrice() > 0 ? 1 : o1.getPrice() - o2.getPrice() < 0 ? -1 : 0;
            }
        };
        Collections.sort(products, comparator);
        for(Product product : products){
            product.displayData();
        }
    }
    public static void sortProductByName(){
        Comparator<Product> comparator = new Comparator<Product>(){

            @Override
            public int compare(Product o1, Product o2) {
                return o1.getProductName().compareTo(o2.getProductName());
            }
        };
        Collections.sort(products, comparator);
        for(Product product : products){
            product.displayData();
        }
    }
    public static void subTotalProductQuantityByCategory(){
        for(Categories category : categories){
            int count = 0;
            for(Product product : products){
                if(product.getCatalogId() == category.getCatalogId()){
                    count++;
                }
            }
            System.out.println("Mã sản phẩm: " + category.getCatalogId() + "Số lượng sản phẩm: " + count);
        }
    }
    public static void searchProductByName(){
        System.out.println("Nhập vào tên sản phẩm muốn tìm kiếm:");
        String searchTerm = sc.nextLine();
        boolean isProductFound = false;
        for(Product product : products){
            if(product.getProductName().contains(searchTerm)){
                isProductFound = true;
                product.displayData();
            }
        }
        if(!isProductFound){
            System.out.println("Không tồn tại sản phẩm với tên đã nhập");
        }
    }
}
