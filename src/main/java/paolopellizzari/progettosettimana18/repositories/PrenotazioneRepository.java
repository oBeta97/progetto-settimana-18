package paolopellizzari.progettosettimana18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import paolopellizzari.progettosettimana18.entities.Prenotazione;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    @Query("SELECT p FROM Prenotazione p JOIN p.dipendente d JOIN p.viaggio v WHERE v.dataViaggio = :dataViaggio AND d.id = :dipendenteId")
    Optional<Prenotazione> findByDipendenteIdAndDataViaggio(long dipendenteId, LocalDate dataViaggio);


}
