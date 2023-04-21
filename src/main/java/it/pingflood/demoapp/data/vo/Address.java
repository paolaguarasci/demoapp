package it.pingflood.demoapp.data.vo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
public class Address {
  @NotBlank(message = "Address Line 1 is mandatory")
  @Length(max = 25, min = 3, message = "Address line1 length min 3 - max 25 chars")
  @Pattern(regexp = "[a-zA-Z0-9, -]+", message = "Address line2 only alphanumeric")
  String line1;
  
  @Length(max = 25, min = 0, message = "Address line2 length min 0 - max 25 chars")
  @Pattern(regexp = "[a-zA-Z0-9, -]*", message = "Address line2 only alphanumeric")
  String line2;
  
  @NotBlank(message = "Address Postal Code is mandatory")
  @Length(max = 5, min = 3, message = "Address Postal Code length min 3 - max 5 chars")
  @Pattern(regexp = "[0-9]+", message = "Address Postal Code only digit")
  String postalCode;
  
  @NotBlank(message = "Address City is mandatory")
  @Length(max = 25, min = 3, message = "Address City length min 3 - max 25 chars")
  @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "Address City only alphanumeric")
  String city;
  
  @NotBlank(message = "Address State is mandatory")
  @Length(max = 25, min = 3, message = "Address State length min 3 - max 25 chars")
  @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "Address State only alphanumeric")
  String state;
  
  @ValidateOnExecution
  public Address(String line1, String line2, String postalCode, String city, String state) {
    this.line1 = line1;
    this.line2 = line2;
    this.postalCode = postalCode;
    this.city = city;
    this.state = state;
  }
}
