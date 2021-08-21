package service;

import client.HttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import entity.Genre;
import entity.ListOptions;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import response.BaseResponse;
import utils.EndpointBuilder;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GenreService {
    private static final Logger LOG = Logger.getLogger(String.valueOf(GenreService.class));
    Gson gson = new Gson();

    @Step()
    public BaseResponse<Object> getGenre(int genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        LOG.info("Request Get Genre By Id");
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
    }

    public BaseResponse<Object> getGenreByName(String genreName) {
        String endpoint = new EndpointBuilder().pathParameter("genres").pathParameter("search").queryParam("query", genreName).get();
        LOG.info("Request Get Genre By Name");
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
    }
    @Step("Get all Genres")
    public BaseResponse<Genre> getGenres(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("genres");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        LOG.info("Request Get Genres");
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Genre.class);
    }

    @Step
    public int getNotExistedId() {
        Response response = RestAssured.get(new EndpointBuilder().pathParameter("genres").get());
        Genre[] genres = response.as(Genre[].class);
        int max = 0;
        for (Genre e : genres) {
            if (e.getGenreId() > max) {
                max = e.getGenreId();
            }
        }
        LOG.info("Get not existed Genre Id");
        return ++max;
    }

    @Step
    public BaseResponse<Genre> createGenre(Genre genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        LOG.info("Create Genre");
        return new BaseResponse(HttpClient.post(endpoint, gson.toJson(genre)), Genre.class);
    }

    @Step
    public BaseResponse<Genre> deleteGenre(Integer genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre/" + genre).get();
        LOG.info("Delete Genre");
        return new BaseResponse<>(HttpClient.delete(endpoint), Genre.class);
    }

    @Step
    public BaseResponse<Genre> updateGenre(Genre genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        LOG.info("Update Genre");
        return new BaseResponse(HttpClient.put(endpoint, gson.toJson(genre)), Genre.class);
    }

    @Step
    public int getExistedGenreId() {
        Response response = RestAssured.get(new EndpointBuilder().pathParameter("genres").get());
        Genre[] genres = response.as(Genre[].class);
        int someId = 0;
        for (Genre e : genres) {
            someId = e.getGenreId();
        }
        return someId;
    }


}
