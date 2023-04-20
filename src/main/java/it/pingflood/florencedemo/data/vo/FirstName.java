package it.pingflood.florencedemo.data.vo;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
public class FirstName {
  String firstName;
  
  public FirstName(String firstName) {
    // TODO Validation
    this.firstName = firstName;
  }
}
