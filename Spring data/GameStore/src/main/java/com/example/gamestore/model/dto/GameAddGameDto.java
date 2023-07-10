package com.example.gamestore.model.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddGameDto {

    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String thubnailURL;
    private String description;
    private LocalDate releaseDate;


    public GameAddGameDto() {
    }

    public GameAddGameDto(String title, BigDecimal price, Double size, String trailer,
                          String thubnailURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thubnailURL = thubnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
    }


    @Pattern(regexp = "^[A-Z].{2,100}$", message = "Invalid title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DecimalMin(value = "0", message = "Price must be positive")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Min(value = 0, message = "Size must be positive")
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Size(min = 11, max = 11, message = "Need 11 symbol")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "(https?).+", message = "URL must be start with http/s")
    public String getThubnailURL() {
        return thubnailURL;
    }

    public void setThubnailURL(String thubnailURL) {
        this.thubnailURL = thubnailURL;
    }

    @Size(min = 20, message = "Min symbol is 20")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
