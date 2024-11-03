package paolopellizzari.progettosettimana18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paolopellizzari.progettosettimana18.entities.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
}
