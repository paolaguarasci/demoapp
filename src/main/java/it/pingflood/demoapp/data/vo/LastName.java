package it.pingflood.demoapp.data.vo;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
public class LastName {
  String lastName;
  
  public LastName(String lastName) {
    // TODO Validation
    this.lastName = lastName;
  }
}
