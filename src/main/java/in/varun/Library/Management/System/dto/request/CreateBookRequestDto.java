package in.varun.Library.Management.System.dto.request;

import jakarta.validation.constraints.*;

public class CreateBookRequestDto {

    @Pattern(
            regexp = "^(978|979)[0-9]{10}$",
            message = "ISBN must be a valid 13-digit ISBN starting with 978 or 979"
    )
    @NotBlank(message = "ISBN can't be Empty")
    private String isbn;

    @Size(min = 1 , max = 50)
    @NotBlank(message = "Title can't be Empty")
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
    @Min(value = 1,message = "price can not be less than 1 INR")
    private Integer price;

    @NotNull(message = "Total Copies can't be Empty")
    @Min(value = 1 , message = "Minimum 1 Copy is needed")
    private Integer totalCopies;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

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

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }
}
