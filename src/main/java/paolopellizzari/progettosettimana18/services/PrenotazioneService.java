package paolopellizzari.progettosettimana18.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolopellizzari.progettosettimana18.entities.Dipendente;
import paolopellizzari.progettosettimana18.entities.Prenotazione;
import paolopellizzari.progettosettimana18.entities.Prenotazione;
import paolopellizzari.progettosettimana18.entities.Viaggio;
import paolopellizzari.progettosettimana18.exceptions.BadRequestException;
import paolopellizzari.progettosettimana18.exceptions.NotFoundException;
import paolopellizzari.progettosettimana18.payloads.PrenotazioneDTO;
import paolopellizzari.progettosettimana18.repositories.PrenotazioneRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepo;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private DipendenteService dipendenteService;

    public Prenotazione findById(long id){
        return this.prenotazioneRepo.findById(id).orElseThrow(()-> new NotFoundException(Prenotazione.class));
    }

    public Page<Prenotazione> findAll(int page, int size, String sortBy){

        if(size > 10) size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Prenotazione> res =  this.prenotazioneRepo.findAll(pageable);

        if (res.isEmpty()) throw new NotFoundException(Prenotazione.class);

        return res;
    }


    public Prenotazione save (PrenotazioneDTO dto){

        Viaggio v = this.viaggioService.findById(dto.idViaggio());

        if(this.prenotazioneRepo.findByDipendenteIdAndDataViaggio(dto.idDipendente(), v.getDataViaggio()).isPresent())
            throw new BadRequestException("Il dipendente è già occupato in questa data");

        Dipendente d = this.dipendenteService.findById(dto.idDipendente());

        return prenotazioneRepo.save(new Prenotazione(dto, v, d));
    }

    public Prenotazione update (PrenotazioneDTO dto, long idToUpdate){

        this.findById(idToUpdate);
        Viaggio v = this.viaggioService.findById(dto.idViaggio());

        if(this.prenotazioneRepo.findByDipendenteIdAndDataViaggio(dto.idDipendente(), v.getDataViaggio()).isPresent())
            throw new BadRequestException("Il dipendente è già occupato in questa data");

        Dipendente d = this.dipendenteService.findById(dto.idDipendente());

        return prenotazioneRepo.save(new Prenotazione(dto, v, d));
    }


    public Prenotazione delete (long idToDelete){

        Prenotazione res =this.findById(idToDelete);

        this.prenotazioneRepo.delete(res);

        return res;
    }


}
