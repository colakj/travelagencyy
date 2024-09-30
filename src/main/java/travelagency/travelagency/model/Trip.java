package travelagency.travelagency.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Place name is required.")
    private String placeName;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotNull(message = "Start date is required.")
    private LocalDate dateFrom;

    @NotNull(message = "End date is required.")
    private LocalDate dateTo;

    @NotNull(message = "Price is required.")
    private Double price;

    private String imageUrl;

    @Transient
    private String formattedDateFrom;

    @Transient
    private String formattedDateTo;

    // Constructors
    public Trip() {
    }

    public Trip(String placeName, String description, LocalDate dateFrom, LocalDate dateTo, Double price, String imageUrl) {
        this.placeName = placeName;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Computed getter for number of days
    public long getNumberOfDays() {
        if (dateFrom != null && dateTo != null) {
            return ChronoUnit.DAYS.between(dateFrom, dateTo);
        }
        return 0;
    }
    public String getFormattedDateFrom() {
        return formattedDateFrom;
    }

    public void setFormattedDateFrom(String formattedDateFrom) {
        this.formattedDateFrom = formattedDateFrom;
    }

    public String getFormattedDateTo() {
        return formattedDateTo;
    }

    public void setFormattedDateTo(String formattedDateTo) {
        this.formattedDateTo = formattedDateTo;
    }
}