import java.io.Serializable;
public class Book implements Serializable {
    private String title;
    private String author;
    private int price;

    private Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public void setAuthor(String author) {
        this.author = author;
    }



    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }

    public static BookBuilder builder(){ //入口
        return new BookBuilder();
    }

    public static class BookBuilder{
        private String title;
        private String author;
        private int price;
        private BookBuilder(){} //不让构造这个类

        public BookBuilder title(String title){
            this.title = title;
            return this;
        }
        public BookBuilder author(String author){
            this.author = author;
            return this;
        }
        public BookBuilder price(int price){
            this.price = price;
            return this;
        }

        public Book build(){
            return new Book(title,author,price);
        }
    }
}
