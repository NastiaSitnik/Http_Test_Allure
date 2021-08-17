import entity.Genre;
import entity.ListOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import utils.Listener;

import java.util.ArrayList;
@Listeners(Listener.class)
public class GenreTest {

    @Test()
    @Description("Create Genre")
    public void createGenreTest() {
        int id = new GenreService().getNotExistedId();
        Genre newGenre = new Genre(id, "Nasty", "Sitnik");
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(newGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        BaseResponse<Genre> genreBaseResponseDelete =
        new GenreService().deleteGenre(id);
//        Assert.assertEquals("Genre was deleted",204,genreBaseResponseDelete.getStatusCode());
    }

    @Test()
    @Description("Get All Genres")
    public void getAllGenres() {
        ListOptions list = new ListOptions();
        BaseResponse<Genre> genresBaseResponse = new GenreService().getGenres(list);
        Assert.assertEquals(genresBaseResponse.getStatusCode(), 200);

    }
    @Test()
    @Description("Get Genre By ID")
    public void getGenreById() {
        int id = new GenreService().getNotExistedId();
        System.out.println("Id = "+id);
        Genre newGenre = new Genre(id, "Nasty", "Sitnik");
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(newGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        BaseResponse<Object> genresBaseResponse = new GenreService().getGenre(id);
        Assert.assertEquals(genresBaseResponse.getStatusCode(), 200);
        new GenreService().deleteGenre(id);
    }

    @Test()
    @Description("Get Genre By Name")
    public void getGenreByName() {
        int id = new GenreService().getNotExistedId();
        System.out.println("Id = "+id);
        Genre newGenre = new Genre(id, "Nasty", "Sitnik");
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(newGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        BaseResponse<Object> genresBaseResponse = new GenreService().getGenreByName("Nastya");
        Assert.assertEquals(genresBaseResponse.getStatusCode(), 200);
        new GenreService().deleteGenre(id);
    }

    @Test()
    @Description("Delete Genre By Id")
    public void deleteGenreById() {
        int id = new GenreService().getNotExistedId();
        System.out.println("Id = "+id);
        Genre newGenre = new Genre(id, "Nasty", "Sitnik");
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(newGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        BaseResponse<Genre> genreBaseResponseDelete = new GenreService().deleteGenre(id);
        Assert.assertEquals(genreBaseResponseDelete.getStatusCode(), 204);
    }

    @Test()
    @Description("Update Genre")
    public void updateGenre() {
        int id = new GenreService().getNotExistedId();
        System.out.println("Id = "+id);
        Genre newGenre = new Genre(id, "Nasty", "Sitnik");
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(newGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        BaseResponse<Genre> genreBaseResponseUpdate = new GenreService().updateGenre(newGenre);
        Assert.assertEquals(genreBaseResponseUpdate.getStatusCode(), 200);
        new GenreService().deleteGenre(id);
    }

}
