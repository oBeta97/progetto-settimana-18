package paolopellizzari.progettosettimana18.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolopellizzari.progettosettimana18.entities.Dipendente;
import paolopellizzari.progettosettimana18.entities.Viaggio;
import paolopellizzari.progettosettimana18.exceptions.BadRequestException;
import paolopellizzari.progettosettimana18.payloads.ViaggioDTO;
import paolopellizzari.progettosettimana18.services.ViaggioService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public Page<Viaggio> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return viaggioService.findAll(page, size, sortBy);
    }

    @GetMapping("/{viaggioId}")
    public Viaggio findById (@PathVariable long viaggioId){
        return this.viaggioService.findById(viaggioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio addViaggio(@RequestBody @Validated ViaggioDTO newViaggio, BindingResult validationResult){

        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.viaggioService.save(newViaggio);
    }


    @PutMapping("/{viaggioId}")
    public Viaggio updateViaggio(@PathVariable long viaggioId, @RequestBody @Validated ViaggioDTO dto, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.viaggioService.update(dto, viaggioId);
    }

    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio (@PathVariable long viaggioId){
        this.viaggioService.delete(viaggioId);
    }


}
