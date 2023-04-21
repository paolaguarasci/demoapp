package it.pingflood.demoapp.config;

import it.pingflood.demoapp.data.User;
import it.pingflood.demoapp.data.vo.Address;
import it.pingflood.demoapp.data.vo.Email;
import it.pingflood.demoapp.data.vo.FirstName;
import it.pingflood.demoapp.data.vo.LastName;
import it.pingflood.demoapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@ConditionalOnProperty(name = "app.config.init.db", havingValue = "true")
@Transactional
public class InitDB implements CommandLineRunner {
  private final UserRepository userRepository;
  
  public InitDB(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Override
  public void run(String... args) throws Exception {
    log.info("Init DB - Start");
    createUser();
    log.info("Init DB - End");
  }
  
  private void createUser() {
    log.info("  User creating...");
    
    userRepository.saveAndFlush(User.builder()
      .firstName(new FirstName("Paola"))
      .lastName(new LastName("Rossi"))
      .email(new Email("a@b.com"))
      .address(Address.builder()
        .line1("Via dei Mille, 0")
        .postalCode("00100")
        .city("Roma")
        .state("Italia")
        .build())
      .build());
    
    log.info("  User create!");
  }
}
