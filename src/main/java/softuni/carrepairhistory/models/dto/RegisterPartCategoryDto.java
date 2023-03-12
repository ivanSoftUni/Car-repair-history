package softuni.carrepairhistory.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterPartCategoryDto {
    @NotNull
    @Size(min = 3)
    private String name;

    public RegisterPartCategoryDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
