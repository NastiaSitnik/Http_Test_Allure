import entity.Author;
import entity.ListOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.ResponseBody;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.Listener;


@Listeners(Listener.class)
public class AuthorTest {

    @Test()
    @Description("Get All Authors")
    public void getAllAuthors() {
        ListOptions list = new ListOptions();
        BaseResponse<Object> authorBaseResponse = new AuthorService().getAuthors(list);
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 200);
        //Allure.addAttachment("Create response", authorBaseResponse.getBody().toString());

    }




    @Test()
    @Description("Get All Author By Id")
    public void getAuthorById() {
        int id = new AuthorService().getNotExistedId();
        System.out.println("Id = " + id);
        Author newAuthor = new Author(id, "Nastia", "Sitnik", "ukrainian", "2001-04-24",
                "Ukraine", "Lviv", "Something");
        new AuthorService().createAuthor(newAuthor);
        BaseResponse<Object> authorBaseResponse = new AuthorService().getAuthor(id);
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 200);
        new AuthorService().deleteAuthor(id);
    }

    @Test()
    @Description("Get All Author By Name")
    public void getAuthorByName() {
        int id = new AuthorService().getNotExistedId();
        System.out.println("Id = " + id);
        Author newAuthor = new Author(id, "Nastia", "Sitnik", "ukrainian", "2001-04-24",
                "Ukraine", "Lviv", "Something");
        new AuthorService().createAuthor(newAuthor);
        BaseResponse<Object> authorBaseResponse = new AuthorService().getAuthorByName("Nastia");
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 200);
        new AuthorService().deleteAuthor(id);
    }

    @Test()
    @Description("Create new Author")
    public void createAuthorTest() {
        int id = new AuthorService().getNotExistedId();
        Author newAuthor = new Author(id, "Nastia", "Sitnik", "ukrainian", "2001-04-24",
                "Ukraine", "Lviv", "Something");
        BaseResponse<Author> authorBaseResponse = new AuthorService().createAuthor(newAuthor);
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 201);
        BaseResponse<Author> authorBaseResponseDelete =
                new AuthorService().deleteAuthor(id);
        Assert.assertEquals(204,authorBaseResponseDelete.getStatusCode());
    }

    @Test()
    @Description("Delete some Author")
    public void deleteAuthorById() {
        int id = new AuthorService().getNotExistedId();
        System.out.println("Id = " + id);
        Author newAuthor = new Author(id, "Nastia", "Sitnik", "ukrainian", "2001-04-24",
                "Ukraine", "Lviv", "Something");
        new AuthorService().createAuthor(newAuthor);
        BaseResponse<Author> authorBaseResponseDelete = new AuthorService().deleteAuthor(id);
        Assert.assertEquals(authorBaseResponseDelete.getStatusCode(), 204);
    }

    @Test()
    @Description("Update Author By Id")
    public void updateGenre() {
        int id = new AuthorService().getNotExistedId();
        Author newAuthor = new Author(id, "Nastia", "Sitnik", "ukrainian", "2001-04-24",
                "Ukraine", "Lviv", "Something");
        new AuthorService().createAuthor(newAuthor);
        BaseResponse<Author> authorBaseResponse = new AuthorService().updateAuthor(newAuthor);
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 200);
        new AuthorService().deleteAuthor(id);
    }


}
