package paolopellizzari.progettosettimana18.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolopellizzari.progettosettimana18.entities.Prenotazione;
import paolopellizzari.progettosettimana18.entities.Viaggio;
import paolopellizzari.progettosettimana18.exceptions.NotFoundException;
import paolopellizzari.progettosettimana18.repositories.ViaggioRepository;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepo;

    public Viaggio findById(long id){
        return this.viaggioRepo.findById(id).orElseThrow(()-> new NotFoundException(Viaggio.class));
    }

    public Page<Viaggio> findAll(int page, int size, String sortBy){

        if(size > 10) size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Viaggio> res =  this.viaggioRepo.findAll(pageable);

        if (res.isEmpty()) throw new NotFoundException(Viaggio.class);

        return res;
    }

}
