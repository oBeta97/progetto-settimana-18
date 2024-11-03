package paolopellizzari.progettosettimana18.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(

        @NotEmpty
        @Size(min = 5, max = 20, message = "Lo username deve avere tra i 5 e 20 caratteri")
        String username,
        String nome,
        String cognome,
        @NotEmpty
        @Email(message = "Email non valida")
//        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")
        String email
) {}