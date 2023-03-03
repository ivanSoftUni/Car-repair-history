package softuni.carrepairhistory.models.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private PartsCategory categoryName;

    public Part() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PartsCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(PartsCategory categoryName) {
        this.categoryName = categoryName;
    }
}
