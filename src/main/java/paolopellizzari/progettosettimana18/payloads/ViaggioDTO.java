package paolopellizzari.progettosettimana18.payloads;

import jakarta.validation.constraints.NotEmpty;
import paolopellizzari.progettosettimana18.enums.StatoViaggio;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotEmpty(message = "La data di prenotazione è obbligatoria")
        LocalDate dataPrenotazione,
        @NotEmpty(message = "Lo stato del viaggio è obbligatorio")
        StatoViaggio statoViaggio,
        @NotEmpty(message = "La destinazione è obbligatoria")
        String destinazione
) {
}
