package paolopellizzari.progettosettimana18.payloads;

import jakarta.validation.constraints.NotEmpty;
import paolopellizzari.progettosettimana18.enums.PreferenzaViaggio;

import java.time.LocalDate;

public record PrenotazioneDTO(

        @NotEmpty(message = "La preferenza di viaggio è obbligatoria")
        PreferenzaViaggio preferenzaViaggio,
        @NotEmpty(message = "la data di richiesta è obbligatoria")
        LocalDate dataRichiesta,
        String note,
        @NotEmpty(message = "l'id del viaggio è obbligatorio")
        long idViaggio,
        @NotEmpty(message = "l'id del dipendente è obbligatorio")
        long idDipendente
) {
}
