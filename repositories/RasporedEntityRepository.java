package com.example.PetWalkback.repositories;

import com.example.PetWalkback.models.dto.Korisnik;
import com.example.PetWalkback.models.dto.MonthlyTotal;
import com.example.PetWalkback.models.entities.KorisnikEntity;
import com.example.PetWalkback.models.entities.RasporedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface RasporedEntityRepository extends JpaRepository<RasporedEntity, Integer> {
    @Query(value = "SELECT MONTH(datum) AS month, YEAR(datum) AS year, SUM(ukupnaCijena) AS total_price, SUM(vrijemeCuvanja) AS total_time " +
            "FROM RasporedEntity " +
            "WHERE korisnik.id = :id " +
            "AND ((YEAR(datum) = YEAR(CURRENT_DATE()) - 1 AND MONTH(datum) >= MONTH(CURRENT_DATE())) " +
            "OR (YEAR(datum) = YEAR(CURRENT_DATE()) AND MONTH(datum) < MONTH(CURRENT_DATE()))) " +
            "GROUP BY MONTH(datum), YEAR(datum)" +
            "ORDER BY YEAR(datum), MONTH(datum)")
    List<Object[]> calculateMonthlyTotals(@Param("id") Integer id);

    @Query("SELECT k FROM KorisnikEntity k WHERE k.role = :role")
    List<KorisnikEntity> findByRole(@Param("role") String role);
}
