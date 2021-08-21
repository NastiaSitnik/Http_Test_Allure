package service;

import client.HttpClient;
import com.google.gson.Gson;
import entity.Author;
import entity.Genre;
import entity.ListOptions;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import response.BaseResponse;
import utils.EndpointBuilder;

import java.util.logging.Logger;

public class AuthorService {
    Gson gson = new Gson();
    private static final Logger LOG = Logger.getLogger(String.valueOf(AuthorService.class));

    @Step("Get Author by AuthorId")
    public BaseResponse<Object> getAuthor(int authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        LOG.info("Get Author By Id");
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
    }

    @Step("Get Author by AuthorName")
    public BaseResponse<Object> getAuthorByName(String authorName) {
        String endpoint = new EndpointBuilder().pathParameter("authors").pathParameter("search")
                .queryParam("query", authorName).get();
        LOG.info("Get Author By Name");
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
    }

    @Step("Get All Authors")
    public BaseResponse<Object> getAuthors(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("authors");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        LOG.info("Get All Authors ");
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Create new Author")
    public BaseResponse<Author> createAuthor(Author author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        LOG.info("Create Author");
        return new BaseResponse<>(HttpClient.post(endpoint, gson.toJson(author)), Author.class);
    }

    @Step("Delete Author by AuthorId")
    public BaseResponse<Author> deleteAuthor(Integer authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author/" + authorId).get();
        LOG.info("Delete Author");
        return new BaseResponse<>(HttpClient.delete(endpoint), Author.class);
    }

    @Step("Update existed Author")
    public BaseResponse<Author> updateAuthor(Author author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        LOG.info("Update Author");
        return new BaseResponse<>(HttpClient.put(endpoint, gson.toJson(author)), Author.class);
    }

    @Step("Get not existed AuthorId")
    public int getNotExistedId() {
        Response response = RestAssured.get(new EndpointBuilder().pathParameter("authors").get());
        Author[] authors = response.as(Author[].class);
        int max = 0;
        for (Author e : authors) {
            if (e.getAuthorId() > max) {
                max = e.getAuthorId();
            }
        }
        LOG.info("Get not existed Author Id");
        return ++max;
    }

    @Step("Get existed AuthorId")
    public int getExistedAuthorId() {
        Response response = RestAssured.get(new EndpointBuilder().pathParameter("authors").get());
        Author[] authors = response.as(Author[].class);
        int someId = 0;
        for (Author e : authors) {
            someId = e.getAuthorId();
        }
        return someId;
    }
}
