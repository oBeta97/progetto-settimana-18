package paolopellizzari.progettosettimana18.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolopellizzari.progettosettimana18.entities.Dipendente;
import paolopellizzari.progettosettimana18.entities.Prenotazione;
import paolopellizzari.progettosettimana18.exceptions.NotFoundException;
import paolopellizzari.progettosettimana18.repositories.PrenotazioneRepository;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepo;

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

}
