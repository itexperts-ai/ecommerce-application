package UserAuthentication.Helper;

import UserAuthentication.DTO.FilterRequest;
import UserAuthentication.Entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class UserSpecification {
    public static Specification<UserEntity> buildFilter(List<FilterRequest> filters){
        return (root, query, criteriaBuilder) -> {
            if(filters == null || filters.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return (jakarta.persistence.criteria.Predicate) filters.stream()
                    .map(filter -> {
                        switch (filter.getOperator()){
                            case "eq":
                                return criteriaBuilder.equal(root.get(filter.getField()), filter.getValue());
                            case "gt":
                                return criteriaBuilder.greaterThan(root.get(filter.getField()), (Comparable)filter.getValue());
                            case "lt":
                                return criteriaBuilder.lessThan(root.get(filter.getField()), (Comparable) filter.getValue());
                            case "like":
                                return criteriaBuilder.like(root.get(filter.getField()), "%" + filter.getValue()+ "%");
                            case "oneOf":
                                return root.get(filter.getField()).in(filter.getValues());
                            default:
                                return criteriaBuilder.conjunction();
                        }
                    })
                    .reduce(criteriaBuilder::and).orElse(criteriaBuilder.conjunction());
        };
    }
}
