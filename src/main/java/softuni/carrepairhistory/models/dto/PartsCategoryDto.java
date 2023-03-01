package softuni.carrepairhistory.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PartsCategoryDto {

    @NotNull
    @Size(min = 5)
    private String name;

    public PartsCategoryDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
