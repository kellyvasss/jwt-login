package dev.login.jwtlogin.repository;

import dev.login.jwtlogin.entity.PositionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import dev.login.jwtlogin.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByPosition_PositionType(PositionType positionType);
    @Query("SELECT u.username, u.Salary FROM User u WHERE u.position.positionType = :positionType")
    List<Object[]> findNamesAndSalariesByPosition(@Param("positionType") PositionType positionType);

}
