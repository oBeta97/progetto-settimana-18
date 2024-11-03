package paolopellizzari.progettosettimana18.entities;


import jakarta.persistence.*;
import lombok.*;
import paolopellizzari.progettosettimana18.enums.PreferenzaViaggio;
import paolopellizzari.progettosettimana18.payloads.PrenotazioneDTO;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "preferenza_viaggio",nullable = false)
    @Enumerated(EnumType.STRING)
    private PreferenzaViaggio preferenzaViaggio;

    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataRichiesta;
    private String note;

    @ManyToOne
    @JoinColumn(name = "id_viaggio")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "id_diendente")
    private Dipendente dipendente;

    public Prenotazione(PreferenzaViaggio preferenzaViaggio, LocalDate dataRichiesta, String note, Viaggio viaggio, Dipendente dipendente) {
        this.preferenzaViaggio = preferenzaViaggio;
        this.dataRichiesta = dataRichiesta;
        this.note = note;
        this.viaggio = viaggio;
        this.dipendente = dipendente;
    }

    public Prenotazione(PrenotazioneDTO dto, Viaggio viaggio, Dipendente dipendente) {
        this.preferenzaViaggio = dto.preferenzaViaggio();
        this.dataRichiesta = dto.dataRichiesta();
        this.note = dto.note();
        this.viaggio = viaggio;
        this.dipendente = dipendente;
    }
}
