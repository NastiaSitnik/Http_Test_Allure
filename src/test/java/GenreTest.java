import entity.Genre;
import entity.ListOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import utils.Listener;
@Listeners(Listener.class)
public class GenreTest {

    public Genre createGenre(){
        int id = new GenreService().getNotExistedId();
        Genre newGenre = new Genre(id, "Nasty", "Sitnik");
    return newGenre;
    }

    @Test(description = "Create Genre")
    @Step("Create Genre {0} and Delete Genre {1}")
    public void createGenreTest() {
        Genre createGenre = createGenre();
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(createGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        Genre response = genreBaseResponse.getBody();
        Assert.assertEquals(response,createGenre);
        BaseResponse<Genre> genreBaseResponseDelete = new GenreService().deleteGenre(createGenre.getGenreId()); //?
        Assert.assertEquals(204,genreBaseResponseDelete.getStatusCode());
    }

    @Test(description = "Get All Genres")
    public void getAllGenres() {
        ListOptions list = new ListOptions();
        BaseResponse<Genre> genresBaseResponse = new GenreService().getGenres(list);
        Assert.assertEquals(genresBaseResponse.getStatusCode(), 200);

    }
    @Test(description = "Get Genre By ID")
    @Step("Create Genre {0} then Get Genre by Id {1} and Delete Genre by Id {2} ")
    public void getGenreById() {
        Genre createGenre = createGenre();
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(createGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        BaseResponse<Object> genresBaseResponse = new GenreService().getGenre(createGenre.getGenreId());//?
        Assert.assertEquals(genresBaseResponse.getStatusCode(), 200);
        BaseResponse<Genre> genreBaseResponseDelete = new GenreService().deleteGenre(createGenre.getGenreId());//?
        Assert.assertEquals(genreBaseResponseDelete.getStatusCode(), 204);
    }

    @Test(description = "Get Genre By Name")
    @Step("Create Genre {0} then Get Genre by Name {1} and Delete Genre by Id {2} ")
    public void getGenreByName() {
        Genre createGenre = createGenre();
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(createGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        BaseResponse<Object> genresBaseResponse = new GenreService().getGenreByName("Nastya");
        Assert.assertEquals(genresBaseResponse.getStatusCode(), 200);
        BaseResponse<Genre> genreBaseResponseDelete = new GenreService().deleteGenre(createGenre.getGenreId());
        Assert.assertEquals(genreBaseResponseDelete.getStatusCode(), 204);
    }

    @Test(description = "Delete Genre By Id")
    @Step("Create Genre {0} and Delete Genre by Id {1} ")
    public void deleteGenreById() {
        Genre createGenre = createGenre();
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(createGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        BaseResponse<Genre> genreBaseResponseDelete = new GenreService().deleteGenre(createGenre.getGenreId());
        Assert.assertEquals(genreBaseResponseDelete.getStatusCode(), 204);
    }

    @Test(description = "Update Genre")
    @Step("Create Genre {0} then Update this Genre {1} and Delete Genre by Id {2} ")
    public void updateGenre() {
        Genre createGenre = createGenre();
        BaseResponse<Genre> genreBaseResponse = new GenreService().createGenre(createGenre);
        Assert.assertEquals(genreBaseResponse.getStatusCode(), 201);
        Genre updateGenre = new Genre(createGenre.getGenreId(),"UpdatedNasty","UpdatedSitnik");
        BaseResponse<Genre> genreBaseResponseUpdate = new GenreService().updateGenre(updateGenre);
        Assert.assertEquals(genreBaseResponseUpdate.getStatusCode(), 200);
        Genre response = genreBaseResponseUpdate.getBody();
        Assert.assertEquals(response,updateGenre);
        BaseResponse<Genre> genreBaseResponseDelete = new GenreService().deleteGenre(createGenre.getGenreId());
        Assert.assertEquals(genreBaseResponseDelete.getStatusCode(), 204);
    }

}
