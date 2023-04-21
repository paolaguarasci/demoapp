package it.pingflood.demoapp.data.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
@NoArgsConstructor(force = true)
public class Email {
  @NotBlank(message = "Email is mandatory")
  @Length(max = 45, min = 3, message = "Email must be length between 3 and 45 chars")
  @Pattern(regexp = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$", message = "Email format is wrong")
  String email;
  
  public Email(String email) {
    this.email = email;
  }
  
}
