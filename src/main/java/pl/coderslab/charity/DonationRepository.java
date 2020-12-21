package pl.coderslab.charity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.security.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findAllByUser(User user);

    @Query(value = "SELECT SUM(quantity) FROM donation WHERE quantity;", nativeQuery = true)
    Integer getQuantity();

    @Query(value = "SELECT count(id) FROM donation;", nativeQuery = true)
    Integer getDonations();
}
