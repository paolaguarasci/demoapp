package it.pingflood.demoapp.data.vo;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
public class Email {
  String email;
  
  public Email(String email) {
    
    // TODO Validazione
    
    this.email = email;
  }
  
}
