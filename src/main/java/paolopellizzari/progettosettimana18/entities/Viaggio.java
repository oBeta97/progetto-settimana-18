package paolopellizzari.progettosettimana18.entities;


import jakarta.persistence.*;
import lombok.*;
import paolopellizzari.progettosettimana18.enums.StatoViaggio;
import paolopellizzari.progettosettimana18.payloads.DipendenteDTO;
import paolopellizzari.progettosettimana18.payloads.ViaggioDTO;

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
    @Column(name = "data_viaggio", nullable = false)
    private LocalDate dataViaggio;
    @Column(name = "stato_viaggio", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;
    @Column(nullable = false)
    private String destinazione;

    public Viaggio(LocalDate dataViaggio, StatoViaggio statoViaggio, String destinazione) {
        this.dataViaggio = dataViaggio;
        this.statoViaggio = statoViaggio;
        this.destinazione = destinazione;
    }

    public Viaggio (ViaggioDTO dto){
        this.dataViaggio = dto.dataViaggio();
        this.statoViaggio = dto.statoViaggio();
        this.destinazione = dto.destinazione();
    }

    public Viaggio (long id, ViaggioDTO dto){
        this.id = id;
        this.dataViaggio = dto.dataViaggio();
        this.statoViaggio = dto.statoViaggio();
        this.destinazione = dto.destinazione();
    }

}
