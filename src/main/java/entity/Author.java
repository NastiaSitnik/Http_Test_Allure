package entity;

import org.testng.ITestListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Author  {


    private int authorId;
    private Map<String,String > authorName;
    private String nationality;
    private Map<String,String>birth;
    private String authorDescription;

    public Author(int authorId, String authorName,String authorSurname, String nationality, String date,String country,String city, String authorDescription) {
        this.authorId = authorId;
        this.authorName = new HashMap<>();
        this.authorName.put("first",authorName);
        this.authorName.put("second",authorSurname);
        this.nationality = nationality;
        this.birth = new HashMap<>();
        this.birth.put("date",date);
        this.birth.put("country",country);
        this.birth.put("city",city);
        this.authorDescription = authorDescription;
    }

    public Author(){}

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Map<String, String> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(Map<String, String> authorName) {
        this.authorName = authorName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Map<String, String> getBirth() {
        return birth;
    }

    public void setBirth(Map<String, String> birth) {
        this.birth = birth;
    }

    public String getAuthorDescription() {
        return authorDescription;
    }

    public void setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName=" + authorName +
                ", nationality='" + nationality + '\'' +
                ", birth=" + birth +
                ", authorDescription='" + authorDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && Objects.equals(authorName, author.authorName) && Objects.equals(nationality, author.nationality) && Objects.equals(birth, author.birth) && Objects.equals(authorDescription, author.authorDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorName, nationality, birth, authorDescription);
    }
}

