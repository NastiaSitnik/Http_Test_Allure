import entity.Book;
import entity.Genre;
import entity.ListOptions;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
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

    @Test(description = "Get All Books")
    public void getAllBooks() {
        ListOptions list = new ListOptions();
        BaseResponse<Object> bookBaseResponse = new BookService().getBooks(list);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
    }


    @Test(description = "Get Book By Id")
    @Step("Create Book {0} then Get Book by BookId {1} and Delete Book by BookId {2}")
    public void getBookById() {
        int id = new BookService().getNotExistedId();
        int genreId = new GenreService().getExistedGenreId();
        int authorId = new AuthorService().getExistedAuthorId();
        Book createBook = new Book(id, "Nastya", "Ukraine", "Smth", new Book.Additional(20, new Book.Size(23, 34, 23)), 1096);

        BaseResponse<Book> bookBaseResponse = new BookService().createBook(createBook, authorId, genreId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 201);
        BaseResponse<Book> bookBaseResponseGetId = new BookService().getBook(id);
        Assert.assertEquals(bookBaseResponseGetId.getStatusCode(), 200);
        BaseResponse<Book> bookBaseResponseDelete = new BookService().deleteBook(id);
        Assert.assertEquals(204, bookBaseResponseDelete.getStatusCode());
    }

    @Test(description = "Get Book By Name")
    @Step("Create Book {0} then Get Book by BookName {1} and Delete Book by BookId {2}")
    public void getBookByName() {
        int id = new BookService().getNotExistedId();
        int genreId = new GenreService().getExistedGenreId();
        int authorId = new AuthorService().getExistedAuthorId();
        Book createBook = new Book(id, "Nastya", "Ukraine", "Smth", new Book.Additional(20, new Book.Size(23, 34, 23)), 1096);

        BaseResponse<Book> bookBaseResponse = new BookService().createBook(createBook, authorId, genreId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 201);
        BaseResponse<Book> bookBaseResponseGetByName = new BookService().getBookByName("Nastya");
        Assert.assertEquals(bookBaseResponseGetByName.getStatusCode(), 200);
        BaseResponse<Book> bookBaseResponseDelete = new BookService().deleteBook(id);
        Assert.assertEquals(204, bookBaseResponseDelete.getStatusCode());
    }

    @Test(description = "Get Book By Author Id")
    @Step("Get Book of Specific Author ")
    public void getBookByAuthorId() {
        int authorId = new AuthorService().getExistedAuthorId();
        BaseResponse<Book> bookBaseResponse = new BookService().getAllBooksOFSpecialAuthor(authorId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
    }

    @Test(description = "Get All Books Of Special Genre")
    @Step("Get Book of Specific Genre ")
    public void getBookByGenreId() {
        int genreId = new GenreService().getExistedGenreId();
        BaseResponse<Book> bookBaseResponse = new BookService().getAllBooksOFSpecialGenre(genreId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
    }

    @Test(description = "Get All Books Of Special Author and Genre ")
    @Step("Get Book of Specific Author and Genre ")
    public void getBookByAuthorIdAndGenreId() {
        int authorId = new AuthorService().getExistedAuthorId();
        int genreId = new GenreService().getExistedGenreId();
        BaseResponse<Book> bookBaseResponse = new BookService().getAllBooksOFSpecialAuthorAndSpecialGenre(authorId, genreId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
    }

    @Test(description = "Create new Book")
    @Step("Create Book {0} then Delete this Book {1}")
    public void createBookTest() {
        int id = new BookService().getNotExistedId();
        int genreId = new GenreService().getExistedGenreId();
        int authorId = new AuthorService().getExistedAuthorId();
        Book createBook = new Book(id, "Nastya", "Ukraine", "Smth", new Book.Additional(20, new Book.Size(23, 34, 23)), 1096);

        BaseResponse<Book> bookBaseResponse = new BookService().createBook(createBook, authorId, genreId);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 201);
        BaseResponse<Book> bookBaseResponseDelete = new BookService().deleteBook(id);
        Assert.assertEquals(204, bookBaseResponseDelete.getStatusCode());
    }

    @Test(description = "Update existing Book")
    @Step("Create Book {0} then Update this Book {1} and Delete it {2}")
    public void updateBook() {
        int id = new BookService().getNotExistedId();
        int genreId = new GenreService().getExistedGenreId();
        int authorId = new AuthorService().getExistedAuthorId();
        Book createBook = new Book(id, "Nastya", "Ukraine", "Smth", new Book.Additional(20, new Book.Size(23, 34, 23)), 1096);


        BaseResponse<Book> bookBaseResponseCreate = new BookService().createBook(createBook, authorId, genreId);
        Assert.assertEquals(bookBaseResponseCreate.getStatusCode(), 201);
        Book updateBook = new Book(id, "UpdateNasty", "UpdateUkrainian", "Smth", new Book.Additional(20, new Book.Size(23, 34, 23)), 1096);
        BaseResponse<Book> bookBaseResponse = new BookService().updateBook(updateBook);
        Assert.assertEquals(bookBaseResponse.getStatusCode(), 200);
        BaseResponse<Book> bookBaseResponseDelete = new BookService().deleteBook(id);
        Assert.assertEquals(204, bookBaseResponseDelete.getStatusCode());
    }

}
