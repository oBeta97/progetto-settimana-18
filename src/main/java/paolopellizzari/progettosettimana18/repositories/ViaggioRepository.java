package paolopellizzari.progettosettimana18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paolopellizzari.progettosettimana18.entities.Viaggio;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {


    List<Viaggio> findByDataViaggioAndDestinazione(LocalDate date, String destinazione);


}
