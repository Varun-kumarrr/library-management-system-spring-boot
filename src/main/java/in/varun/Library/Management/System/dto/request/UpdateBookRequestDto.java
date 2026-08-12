package in.varun.Library.Management.System.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateBookRequestDto {

    @NotBlank(message = "Title can't be Empty")
    @Size(min = 1 , max = 50)
    private String title;

    @Size(min = 1 , max = 50)
    @NotBlank(message = "Publisher can't be Empty")
    private String publisher;

    @Size(min = 1 , max = 50)
    @NotBlank(message = "Category can't be Empty")
    private String category;

    @Size(min = 1 , max = 100)
    @NotBlank(message = "Description can't be Empty")
    private String description;

    @Size(min = 1 , max = 50)
    @NotBlank(message = "Author can't be Empty")
    private String author;

    @NotNull(message = "Price can't be Empty")
    @Min(value = 1, message = "Price can't be less than 1 INR")
    private Integer price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
