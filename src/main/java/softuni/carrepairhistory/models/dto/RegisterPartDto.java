package softuni.carrepairhistory.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class RegisterPartDto {
    @NotNull
    @Size(min = 3)
    private String name;
    @NotNull
    private String category;

    public RegisterPartDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
