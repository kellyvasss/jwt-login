package dev.login.jwtlogin.repository;

import dev.login.jwtlogin.entity.Position;
import dev.login.jwtlogin.entity.PositionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    Optional<Position> findByPositionType(PositionType positionType);
}
