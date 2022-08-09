package dev.esron;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
public class Income extends PanacheEntity {
    @NotNull
    @NotBlank
    public String description;

    @NotNull
    @PositiveOrZero(message = "value must be zero or positive")
    public Integer value;

    @NotNull
    @NotBlank
    @Pattern(regexp = "([0-2][0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/[0-9]{4}", message = "invalid date format")
    public String date;
}
