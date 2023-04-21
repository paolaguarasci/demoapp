package it.pingflood.demoapp.data.vo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@Builder
@NoArgsConstructor(force = true)
public class LastName {
  @NotBlank(message = "Lastname is mandatory")
  @Length(max = 25, min = 3, message = "Lastname must be length between 3 and 25 chars")
  @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "Lastname must be alphanumeric")
  String lastName;
  
  public LastName(String lastName) {
    this.lastName = lastName;
  }
}
