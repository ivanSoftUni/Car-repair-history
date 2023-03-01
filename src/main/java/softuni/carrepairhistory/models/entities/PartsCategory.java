package softuni.carrepairhistory.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "parts_category")
public class PartsCategory extends BaseEntity {
    private String name;

    public PartsCategory() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
