import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Book> LIST = null;

    public static void main(String[] args) {
        System.out.println("正在初始化图书管理系统");
        load();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1.录入书籍信息");
            System.out.println("2.查阅书籍信息");
            System.out.println("3.删除书籍信息");
            System.out.println("4.修改书籍信息");
            System.out.println("5.退出系统");

            switch (scanner.nextInt()){
                case 1:
                    insert(scanner);
                    break;
                case 2:
                    query();
                    break;
                case 3:
                    delete(scanner);
                    break;
                case 4:
                    modify(scanner);
                    break;
                case 5:
                    save();
                    System.out.println("感谢你的使用，再见！");
                    return;
            }
        }
    }
    @SuppressWarnings("unchecked")
    private static void load(){
        File file = new File("data");
        if(file.exists()){
            try(ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(Paths.get("data")))) {
                LIST =(List<Book>) stream.readObject();
            } catch (IOException|ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            LIST = new LinkedList<>();
        }
    }
    
    
    private static void save(){
        try(ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(Paths.get("data")))) {
            stream.writeObject(LIST);
            stream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static void insert(Scanner scanner){
        scanner.nextLine();

        System.out.print("请输入书籍的标题:");
        String title = scanner.nextLine();
        System.out.print("请输入书籍的信息:");
        String author = scanner.nextLine();
        System.out.print("请输入书籍的价格:");
        int price = scanner.nextInt();

        Book book =Book.builder()
                .title(title)
                .author(author)
                .price(price)
                .build();
        scanner.nextLine();
        LIST.add(book);
        System.out.println(LIST);
    }

    private static void query(){
        for (Book book : LIST) {
            System.out.println(book);
        }
    }

    private static void delete(Scanner scanner){
        scanner.nextLine();
        int index = scanner.nextInt();
        scanner.nextLine();

        while (index>LIST.size()-1||index<0){
            System.out.println("没有对应的书籍，请重新输入:");
            index = scanner.nextInt();
        }
        LIST.remove(index);
        System.out.println("书籍删除成功");
    }


    private static void modify(Scanner scanner){
        scanner.nextLine();
        int index = scanner.nextInt();
        scanner.nextLine();

        while (index>LIST.size()-1||index<0){
            System.out.println("没有对应的书籍，请重新输入:");
            index = scanner.nextInt();
        }
        Book book = LIST.get(index);
        System.out.print("请输入书籍的标题:");
        book.setTitle(scanner.nextLine());
        System.out.print("请输入书籍的信息:");
        book.setAuthor(scanner.nextLine());
        System.out.print("请输入书籍的价格:");
        book.setPrice(scanner.nextInt());
    }
}