package paolopellizzari.progettosettimana18.entities;


import jakarta.persistence.*;
import lombok.*;
import paolopellizzari.progettosettimana18.payloads.DipendenteDTO;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String nome;
    private String cognome;
    @Column(nullable = false, unique = true)
    private String email;
    private String avatar;


    public Dipendente (long id, DipendenteDTO dto){
        this.id = id;
        this.username = dto.username();
        this.nome = dto.nome();
        this.cognome = dto.cognome();
        this.email = dto.email();
        this.avatar = "https://ui-avatars.com/api/?name=" + dto.nome() + "+" + dto.cognome();
    }

    public Dipendente (DipendenteDTO dto){
        this.username = dto.username();
        this.nome = dto.nome();
        this.cognome = dto.cognome();
        this.email = dto.email();
        this.avatar = "https://ui-avatars.com/api/?name=" + dto.nome() + "+" + dto.cognome();
    }


    public Dipendente(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.avatar = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }
}
