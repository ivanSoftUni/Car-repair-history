package softuni.carrepairhistory.models.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "repairs")
public class Repair extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @Column
    private LocalDate date;

    @Column
    private String comment;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany
    private List<Part> parts;



    public Repair(){

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }



}
