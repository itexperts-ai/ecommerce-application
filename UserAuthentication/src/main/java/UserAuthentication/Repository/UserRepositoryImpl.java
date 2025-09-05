package UserAuthentication.Repository;

//import UserAuthentication.DTO.SearchRequest;
//import UserAuthentication.Entity.UserEntity;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Repository;

//import java.util.ArrayList;
//import java.util.List;

//@Repository
//public class UserRepositoryImpl implements UserRepositoryCustom{
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Page<UserEntity> searchUsers(SearchRequest request) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
//        Root<UserEntity> root = cq.from(UserEntity.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if(request.getFilters() != null){
//
//        }
//
//        return null;
//    }
//}
