package it.pingflood.demoapp.data.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@ToString
@Builder
@NoArgsConstructor(force = true)
public class FirstName {
  @NotBlank(message = "First name is mandatory")
  @Length(max = 25, min = 3, message = "First name must be length between 3 and 25 chars")
  @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "First name must be only alphanumeric")
  String firstName;
  
  public FirstName(String firstName) {
    this.firstName = firstName;
  }
}
