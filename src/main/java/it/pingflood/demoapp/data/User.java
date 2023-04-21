package it.pingflood.demoapp.data;

import it.pingflood.demoapp.data.vo.Address;
import it.pingflood.demoapp.data.vo.Email;
import it.pingflood.demoapp.data.vo.FirstName;
import it.pingflood.demoapp.data.vo.LastName;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  @Valid
  private Long id;
  
  @Embedded
  @Valid
  @AttributeOverrides(@AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME")))
  private FirstName firstName;
  
  @Embedded
  @Valid
  @AttributeOverrides(@AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME")))
  private LastName lastName;
  
  @Embedded
  @Valid
  @AttributeOverrides(@AttributeOverride(name = "email", column = @Column(name = "EMAIL")))
  private Email email;
  
  @Embedded
  @Valid
  @AttributeOverrides(value = {
    @AttributeOverride(name = "line1", column = @Column(name = "ADDRESS_LINE1")),
    @AttributeOverride(name = "line2", column = @Column(name = "ADDRESS_LINE2")),
    @AttributeOverride(name = "postalCode", column = @Column(name = "ADDRESS_POSTALCODE")),
    @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
    @AttributeOverride(name = "state", column = @Column(name = "ADDRESS_STATE"))
  })
  private Address address;
  
  public void update(@Valid User other) {
    this.firstName = other.firstName;
    this.lastName = other.lastName;
    this.email = other.email;
    this.address = other.address;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    User user = (User) o;
    return getId() != null && Objects.equals(getId(), user.getId());
  }
  
  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
