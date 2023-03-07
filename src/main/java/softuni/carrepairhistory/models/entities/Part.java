package softuni.carrepairhistory.models.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(targetEntity = PartsCategory.class)
    private PartsCategory partsCategory;

    public PartsCategory getPartsCategory() {
        return partsCategory;
    }

    public void setPartsCategory(PartsCategory partsCategory) {
        this.partsCategory = partsCategory;
    }

    public Part() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
