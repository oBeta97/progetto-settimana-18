package paolopellizzari.progettosettimana18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import paolopellizzari.progettosettimana18.entities.Dipendente;

import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {

    Optional<Dipendente> findByEmail(String email);

    Optional<Dipendente> findByUsername(String username);

    @Query("UPDATE Dipendente d SET d.avatar = :url WHERE d.id = :id")
    void updateAvatarById(String url, long id);

}
