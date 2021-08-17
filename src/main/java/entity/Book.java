package entity;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Objects;

public class Book {

    public static class Size {
        double height;
        double width;
        double length;

        public Size(double height, double width, double length) {
            this.height = height;
            this.width = width;
            this.length = length;
        }
        public Size(){}

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }
    }

    public static class Additional {

        int pageCount;
        Size size;

        public Additional(int pageCount, Size size) {
            this.pageCount = pageCount;
            this.size = size;
        }

        public Additional(){}

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public Size getSize() {
            return size;
        }

        public void setSize(Size size) {
            this.size = size;
        }
    }

    private int bookId;
    private String bookName;
    private String bookLanguage;
    private String bookDescription;
    private Additional additional;
    private int publicationYear;

    public Book(int bookId, String bookName, String bookLanguage, String bookDescription, Additional additional, int publicationYear) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookLanguage = bookLanguage;
        this.bookDescription = bookDescription;
        this.additional = additional;
        this.publicationYear = publicationYear;
    }

    public Book(){}

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public Additional getAdditional() {
        return additional;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && publicationYear == book.publicationYear && Objects.equals(bookName, book.bookName) && Objects.equals(bookLanguage, book.bookLanguage) && Objects.equals(bookDescription, book.bookDescription) && Objects.equals(additional, book.additional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, bookLanguage, bookDescription, additional, publicationYear);
    }
}


