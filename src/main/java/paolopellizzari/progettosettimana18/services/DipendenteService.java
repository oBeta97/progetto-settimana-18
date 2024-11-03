package paolopellizzari.progettosettimana18.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paolopellizzari.progettosettimana18.repositories.DipendenteRepository;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepo;


}
