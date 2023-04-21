package it.pingflood.demoapp.repository.specs;

import it.pingflood.demoapp.data.User;
import it.pingflood.demoapp.data.meta.User_;
import it.pingflood.demoapp.data.vo.FirstName;
import it.pingflood.demoapp.data.vo.LastName;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecs {
  public static Specification<User> firstNameContainsIgnoreCase(FirstName firstName) {
    return ((root, query, criteriaBuilder) -> {
      String containsLikePattern = getContainsLikePattern(firstName.getFirstName());
      return criteriaBuilder.like(criteriaBuilder.lower(root.get(User_.FIRSTNAME)), containsLikePattern);
    });
  }
  
  public static Specification<User> lastNameContainsIgnoreCase(LastName lastName) {
    return ((root, query, criteriaBuilder) -> {
      String containsLikePattern = getContainsLikePattern(lastName.getLastName());
      return criteriaBuilder.like(criteriaBuilder.lower(root.get(User_.LASTNAME)), containsLikePattern);
    });
  }
  
  public static Specification<User> firstNameOrlastNameContainsIgnoreCase(FirstName firstName, LastName lastName) {
    return ((root, query, criteriaBuilder) -> {
      String containsLikePattern1 = getContainsLikePattern(firstName.getFirstName());
      String containsLikePattern2 = getContainsLikePattern(lastName.getLastName());
      return criteriaBuilder.or(
        criteriaBuilder.like(criteriaBuilder.lower(root.get(User_.FIRSTNAME)), containsLikePattern1),
        criteriaBuilder.like(criteriaBuilder.lower(root.get(User_.LASTNAME)), containsLikePattern2)
      );
    });
  }
  
  private static String getContainsLikePattern(String searchTerm) {
    if (searchTerm == null || searchTerm.isEmpty()) {
      return "%";
    } else {
      return "%" + searchTerm + "%";
    }
  }
}
