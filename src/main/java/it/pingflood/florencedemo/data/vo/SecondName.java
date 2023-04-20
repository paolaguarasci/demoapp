package it.pingflood.florencedemo.data.vo;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
public class SecondName {
  String secondName;
  
  public SecondName(String secondName) {
    // TODO Validation
    this.secondName = secondName;
  }
}
