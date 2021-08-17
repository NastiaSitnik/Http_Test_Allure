package service;

import client.HttpClient;
import com.google.gson.Gson;
import entity.Book;
import entity.Genre;
import entity.ListOptions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import response.BaseResponse;
import utils.EndpointBuilder;

import java.util.List;
import java.util.logging.Logger;

public class BookService {
    Gson gson = new Gson();
    private static final Logger LOG = Logger.getLogger(String.valueOf(BookService.class));

    public BaseResponse<Book> getBook(int bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(bookId).get();
        LOG.info("Get Book By Id");
        return new BaseResponse<Book>(HttpClient.get(endpoint), Book.class);
    }

    public BaseResponse<Object> getBooks(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("books");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        LOG.info("Get All Books ");
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    public BaseResponse<Book> getBookByName(String bookName) {
        String endpoint = new EndpointBuilder().pathParameter("books").pathParameter("search").queryParam("q",bookName).get();
        LOG.info("Request Get Book By Name");
        return new BaseResponse<>(HttpClient.get(endpoint), Book.class);
    }

    public BaseResponse<Book> createBook(Book book, int authorId, int genreId) {
        String endpoint = new EndpointBuilder().pathParameter("book")
                .pathParameter(authorId).pathParameter(genreId).get();
        LOG.info("Create Book");
        return new BaseResponse<>(HttpClient.post(endpoint, gson.toJson(book)), Book.class);
    }

    public BaseResponse<Book> deleteBook(Integer bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book/" + bookId).get();
        LOG.info("Delete Book");
        return new BaseResponse<>(HttpClient.delete(endpoint), Book.class);
    }

    public BaseResponse<Book> updateBook(Book book) {
        String endpoint = new EndpointBuilder().pathParameter("book").get();
        LOG.info("Update Book");
        return new BaseResponse<>(HttpClient.put(endpoint, gson.toJson(book)), Book.class);
    }
//  return list
    public BaseResponse<Book> getAllBooksOFSpecialAuthor(int authorId){
        String endpoint = new  EndpointBuilder().pathParameter("author")
                .pathParameter(authorId).pathParameter("books").get();
        LOG.info("Get All Books Of Special Author");
        return new BaseResponse<>(HttpClient.get(endpoint),Book.class);

    }
    public BaseResponse<Book> getAllBooksOFSpecialGenre(int genreId){
        String endpoint = new  EndpointBuilder().pathParameter("genre")
                .pathParameter(genreId).pathParameter("books").get();
        LOG.info("Get All Books Of Special Genre");
        return new BaseResponse<>(HttpClient.get(endpoint),Book.class);

    }
    public BaseResponse<Book> getAllBooksOFSpecialAuthorAndSpecialGenre(int authorId, int genreId){
        String endpoint = new  EndpointBuilder().pathParameter("author")
                .pathParameter(authorId).pathParameter("genre").pathParameter(genreId).pathParameter("books").get();
        LOG.info("Get All Books Of Special Author and Special Genre");
        return new BaseResponse<>(HttpClient.get(endpoint),Book.class);

    }

    public int getNotExistedId() {
        int max = 0;
        Response response = RestAssured.get(new EndpointBuilder().pathParameter("books").get());
        List<Book> parsResponse = response.jsonPath().getList("$", Book.class);
        for (Book e : parsResponse) {
            if (e.getBookId() > max) {
                max = e.getBookId();
            }
        }
        return ++max;
    }

}


