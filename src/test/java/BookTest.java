import entity.Book;
import entity.Genre;
import entity.ListOptions;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import utils.Listener;

@Listeners(Listener.class)
public class BookTest {

    @Test()
    @Description("Get All Books")
    public void getAllBooks() {
        ListOptions list = new ListOptions();
        BaseResponse<Object> bookBaseResponse = new BookService().getBooks(list);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
    }


    @Test()
    @Description("Get Book By Id")
    public void getBookById() {
        int id = new BookService().getNotExistedId();
        int genreId = new GenreService().getExistedGenreId();
        int authorId = new AuthorService().getExistedAuthorId();
        Book newBook = new Book(id, "Nastya", "Ukraine", "Smth", new Book.Additional(20, new Book.Size(23, 34, 23)), 1096);
        BaseResponse<Book> bookBaseResponse = new BookService().createBook(newBook, authorId, genreId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 201);
        BaseResponse<Book> bookBaseResponseGetId = new BookService().getBook(id);
        Assert.assertEquals(bookBaseResponseGetId.getStatusCode(), 200);
        new BookService().deleteBook(id);
    }

    @Test()
    @Description("Get Book By Name")
    public void getBookByName() {
        int id = new BookService().getNotExistedId();
        int genreId = new GenreService().getExistedGenreId();
        int authorId = new AuthorService().getExistedAuthorId();
        Book newBook = new Book(id, "Nastya", "Ukraine", "Smth", new Book.Additional(20, new Book.Size(23, 34, 23)), 1096);
        BaseResponse<Book> bookBaseResponse = new BookService().createBook(newBook, authorId, genreId);
        BaseResponse<Book> bookBaseResponseGetByName = new BookService().getBookByName("Nastya");
        Assert.assertEquals(bookBaseResponseGetByName.getStatusCode(), 200);
        new BookService().deleteBook(id);
    }

    @Test()
    @Description("Get Book By Author Id")
    public void getBookByAuthorId() {
        int authorId = new AuthorService().getExistedAuthorId();
        BaseResponse<Book> bookBaseResponse = new BookService().getAllBooksOFSpecialAuthor(authorId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
    }

    @Test()
    @Description("Get All Books Of Special Genre")
    public void getBookByGenreId() {
        int genreId = new GenreService().getExistedGenreId();
        BaseResponse<Book> bookBaseResponse = new BookService().getAllBooksOFSpecialGenre(genreId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
    }

    @Test()
    @Description("Get All Books Of Special Author and Genre ")
    public void getBookByAuthorIdAndGenreId() {
        int authorId = new AuthorService().getExistedAuthorId();
        int genreId = new GenreService().getExistedGenreId();
        BaseResponse<Book> bookBaseResponse = new BookService().getAllBooksOFSpecialAuthorAndSpecialGenre(authorId, genreId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
    }

    @Test()
    @Description("Create new Book")
    public void createBookTest() {
        int id = new BookService().getNotExistedId();
        int genreId = new GenreService().getExistedGenreId();
        int authorId = new AuthorService().getExistedAuthorId();
        Book newBook = new Book(id, "Nasty", "Ukrainian", "Smth", new Book.Additional(20, new Book.Size(23, 34, 23)), 1096);
        BaseResponse<Book> bookBaseResponse = new BookService().createBook(newBook, authorId, genreId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 201);
//        Book getResponse = bookBaseResponse.getBody();
//        Assert.assertEquals(getResponse, newBook);
        BaseResponse<Book> bookBaseResponseDelete =
                new BookService().deleteBook(id);
        Assert.assertEquals(204, bookBaseResponseDelete.getStatusCode());
    }

    @Test()
    @Description("Update existing Book")
    public void updateBook() {
        int id = new BookService().getNotExistedId();
        int genreId = new GenreService().getExistedGenreId();
        int authorId = new AuthorService().getExistedAuthorId();
        Book newBook = new Book(id, "Nasty", "Ukrainian", "Smth", new Book.Additional(20, new Book.Size(23, 34, 23)), 1096);
        new BookService().createBook(newBook, authorId, genreId);
        BaseResponse<Book> bookBaseResponse = new BookService().updateBook(newBook);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
        new BookService().deleteBook(id);
    }

}
