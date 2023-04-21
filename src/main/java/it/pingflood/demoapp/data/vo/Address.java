package it.pingflood.demoapp.data.vo;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
public class Address {
  String line1;
  String line2;
  String postalCode;
  String city;
  String state;
  
  public Address(String line1, String line2, String postalCode, String city, String state) {
    
    // TODO Validation
    
    this.line1 = line1;
    this.line2 = line2;
    this.postalCode = postalCode;
    this.city = city;
    this.state = state;
  }
}
