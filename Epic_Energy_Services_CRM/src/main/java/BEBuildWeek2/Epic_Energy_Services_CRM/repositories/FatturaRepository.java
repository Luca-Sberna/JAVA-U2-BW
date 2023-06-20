package BEBuildWeek2.Epic_Energy_Services_CRM.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Fattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

}