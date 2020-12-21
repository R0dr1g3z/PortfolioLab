package pl.coderslab.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.Donation;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findAllByRoles(Role role);
    User findByToken(String token);
    User findByTokenPassword(String token);

    @Modifying
    @Query(value = "DELETE FROM charityDonations.user_role WHERE user_id = ?1",nativeQuery = true)
    void deleteUserRole(Long id);
}
