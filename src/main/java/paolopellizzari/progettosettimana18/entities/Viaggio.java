package paolopellizzari.progettosettimana18.entities;


import jakarta.persistence.*;
import lombok.*;
import paolopellizzari.progettosettimana18.enums.StatoViaggio;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(name = "data_prenotazione", nullable = false)
    private LocalDate dataPrenotazione;
    @Column(name = "stato_viaggio", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;
    @Column(nullable = false)
    private String destinazione;

    public Viaggio(LocalDate dataPrenotazione, StatoViaggio statoViaggio, String destinazione) {
        this.dataPrenotazione = dataPrenotazione;
        this.statoViaggio = statoViaggio;
        this.destinazione = destinazione;
    }
}
