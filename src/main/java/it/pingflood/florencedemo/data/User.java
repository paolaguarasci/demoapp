package it.pingflood.florencedemo.data;

import it.pingflood.florencedemo.data.vo.Address;
import it.pingflood.florencedemo.data.vo.Email;
import it.pingflood.florencedemo.data.vo.FirstName;
import it.pingflood.florencedemo.data.vo.SecondName;
import jakarta.persistence.*;
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
  private Long id;
  
  @Embedded
  @AttributeOverrides(@AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME")))
  private FirstName firstName;
  
  @Embedded
  @AttributeOverrides(@AttributeOverride(name = "secondName", column = @Column(name = "SECOND_NAME")))
  private SecondName secondName;
  
  @Embedded
  @AttributeOverrides(@AttributeOverride(name = "email", column = @Column(name = "EMAIL")))
  private Email email;
  
  @Embedded
  @AttributeOverrides(value = {
    @AttributeOverride(name = "line1", column = @Column(name = "ADDRESS_LINE1")),
    @AttributeOverride(name = "line2", column = @Column(name = "ADDRESS_LINE2")),
    @AttributeOverride(name = "postalCode", column = @Column(name = "ADDRESS_POSTALCODE")),
    @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
    @AttributeOverride(name = "state", column = @Column(name = "ADDRESS_STATE"))
  })
  private Address address;
  
  public void update(User other) {
    this.firstName = other.firstName;
    this.secondName = other.secondName;
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
