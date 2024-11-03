package paolopellizzari.progettosettimana18.payloads;

import java.time.LocalDateTime;

public record ExceptionResponseDTO(String message, LocalDateTime dt) {
}
