package dev.esron;

import javax.validation.constraints.*;

public class Income {

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
