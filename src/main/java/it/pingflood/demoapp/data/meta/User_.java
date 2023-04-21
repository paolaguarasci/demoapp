package it.pingflood.demoapp.data.meta;

import it.pingflood.demoapp.data.User;
import it.pingflood.demoapp.data.vo.Address;
import it.pingflood.demoapp.data.vo.Email;
import it.pingflood.demoapp.data.vo.FirstName;
import it.pingflood.demoapp.data.vo.LastName;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ {
  
  public static final String ID = "id";
  public static final String FIRSTNAME = "firstName";
  public static final String LASTNAME = "lastName";
  public static final String EMAIL = "email";
  public static final String ADDRESS = "address";
  public static volatile SingularAttribute<User, Long> id;
  public static volatile SingularAttribute<User, FirstName> firstName;
  public static volatile SingularAttribute<User, LastName> lastName;
  public static volatile SingularAttribute<User, Email> email;
  public static volatile SingularAttribute<User, Address> address;
}
