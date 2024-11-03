package paolopellizzari.progettosettimana18.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolopellizzari.progettosettimana18.entities.Prenotazione;
import paolopellizzari.progettosettimana18.exceptions.BadRequestException;
import paolopellizzari.progettosettimana18.payloads.PrenotazioneDTO;
import paolopellizzari.progettosettimana18.services.PrenotazioneService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public Page<Prenotazione> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return prenotazioneService.findAll(page, size, sortBy);
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione findById (@PathVariable long prenotazioneId){
        return this.prenotazioneService.findById(prenotazioneId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione addDipendete(@RequestBody @Validated PrenotazioneDTO newDipendete, BindingResult validationResult){

        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.prenotazioneService.save(newDipendete);
    }

    @PutMapping("/{prenotazioneId}")
    public Prenotazione updatePrenotazione(@PathVariable long prenotazioneId, @RequestBody @Validated PrenotazioneDTO dto, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.prenotazioneService.update(dto, prenotazioneId);
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione (@PathVariable long prenotazioneId){
        this.prenotazioneService.delete(prenotazioneId);
    }

}
