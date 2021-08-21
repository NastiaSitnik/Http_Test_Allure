import entity.Author;
import entity.ListOptions;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.Listener;


@Listeners(Listener.class)
public class AuthorTest {

    public Author createAuthor(){
        int id = new AuthorService().getNotExistedId();
        Author createAuthor = new Author(id, "Nastia", "Sitnik", "ukrainian", "2001-04-24",
                "Ukraine", "Lviv", "Something");
        return createAuthor;
    }

    @Test(description = "Get All Authors")
    public void testGetAllAuthors() {
        ListOptions list = new ListOptions();
        BaseResponse<Object> authorBaseResponse = new AuthorService().getAuthors(list);
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 200);
    }

    @Test(description = "Get All Author By Id")
    @Step("Create Author {0} then Get Author by Id {1} and Delete Author {2}")
    public void testGetAuthorById() {
        Author createAuthor = createAuthor();
        new AuthorService().createAuthor(createAuthor);
        BaseResponse<Object> authorBaseResponse = new AuthorService().getAuthor(createAuthor.getAuthorId());
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 200);
        BaseResponse<Author> authorBaseResponseDelete = new AuthorService().deleteAuthor(createAuthor.getAuthorId());
        Assert.assertEquals(authorBaseResponseDelete.getStatusCode(), 204);
    }

    @Test(description = "Get Author By Name")
    @Step("Create Author {0} then Get Author by Name {1} and Delete Author {2}")
    public void testGetAuthorByName() {
        Author createAuthor = createAuthor();
        new AuthorService().createAuthor(createAuthor);
        BaseResponse<Object> authorBaseResponse = new AuthorService().getAuthorByName("Nastia");
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 200);
        BaseResponse<Author> authorBaseResponseDelete = new AuthorService().deleteAuthor(createAuthor.getAuthorId());
        Assert.assertEquals(authorBaseResponseDelete.getStatusCode(), 204);
    }

    @Test( description = "Create new Author")
    @Step("Create Author {0} and Delete Author {1}")
    public void testCreateAuthorTest() {
         Author createAuthor = createAuthor();
        BaseResponse<Author> authorBaseResponse = new AuthorService().createAuthor(createAuthor);
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 201);
        Author response = authorBaseResponse.getBody();
        Assert.assertEquals(response,createAuthor);
        BaseResponse<Author> authorBaseResponseDelete = new AuthorService().deleteAuthor(createAuthor.getAuthorId());
        Assert.assertEquals(204,authorBaseResponseDelete.getStatusCode());
    }

    @Test(description = "Delete some Author")
    @Step("Create Author {0} Delete Author {1}")
    public void testDeleteAuthorById() {
        Author createAuthor = createAuthor();
        BaseResponse<Author> authorBaseResponseCreate = new AuthorService().createAuthor(createAuthor);
        Assert.assertEquals(authorBaseResponseCreate.getStatusCode(),201);
        BaseResponse<Author> authorBaseResponseDelete = new AuthorService().deleteAuthor(createAuthor.getAuthorId());
        Assert.assertEquals(authorBaseResponseDelete.getStatusCode(), 204);
    }

    @Test(description = "Update Author By Id")
    @Step("Create Author {0} then Update this Author {1} and Delete Author {2}")
    public void testUpdateAuthor() {
        Author createAuthor = createAuthor();
        BaseResponse<Author> authorBaseResponseCreate = new AuthorService().createAuthor(createAuthor);
        Assert.assertEquals(authorBaseResponseCreate.getStatusCode(),201);
        Author updateAuthor = new Author(createAuthor.getAuthorId(), "UpdateNastia", "UpdateSitnik", "ukrainian", "2001-04-24",
                "UpdateUkraine", "UpdateLviv", "Something");
        BaseResponse<Author> authorBaseResponse = new AuthorService().updateAuthor(updateAuthor);
        Assert.assertEquals(authorBaseResponse.getStatusCode(), 200);
        BaseResponse<Author> authorBaseResponseDelete = new AuthorService().deleteAuthor(createAuthor.getAuthorId());
        Assert.assertEquals(authorBaseResponseDelete.getStatusCode(), 204);
    }


}
