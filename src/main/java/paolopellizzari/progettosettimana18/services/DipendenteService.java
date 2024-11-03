package paolopellizzari.progettosettimana18.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import paolopellizzari.progettosettimana18.entities.Dipendente;
import paolopellizzari.progettosettimana18.exceptions.BadRequestException;
import paolopellizzari.progettosettimana18.exceptions.NotFoundException;
import paolopellizzari.progettosettimana18.payloads.DipendenteDTO;
import paolopellizzari.progettosettimana18.repositories.DipendenteRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepo;


    @Autowired
    private Cloudinary cloudinaryUploader;

    private void checkUsernameAndEmail (String username, String email){
        this.dipendenteRepo.
                findByEmail(email).
                ifPresent(dipendente -> {
                            throw new BadRequestException("Email " + email + " già in uso");
                        }
                );

        this.dipendenteRepo.
                findByUsername(username).
                ifPresent(dipendente -> {
                            throw new BadRequestException("Username " + username + " già in uso");
                        }
                );
    }

    public Dipendente findById(long id){
        return this.dipendenteRepo.findById(id).orElseThrow(()-> new NotFoundException(Dipendente.class));
    }

    public Page<Dipendente> findAll(int page, int size, String sortBy){

        if(size > 10) size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Dipendente> res =  this.dipendenteRepo.findAll(pageable);

        if (res.isEmpty()) throw new NotFoundException(Dipendente.class);

        return res;
    }

    public Dipendente save (DipendenteDTO dto){

        this.checkUsernameAndEmail(dto.username(), dto.email());

        return dipendenteRepo.save(new Dipendente(dto));
    }

    public List<Dipendente> saveList (List<DipendenteDTO> dtoList){

        List<Dipendente> res = new ArrayList<>();

        for (DipendenteDTO d : dtoList){
            res.add(this.save(d));
        }

        return res;

    }

    public Dipendente update (DipendenteDTO dto, long idToUpdate){

        this.findById(idToUpdate);
        this.checkUsernameAndEmail(dto.username(), dto.email());

        return this.dipendenteRepo.save(new Dipendente(idToUpdate, dto));
    }


    public Dipendente delete (long idToDelete){

        Dipendente res =this.findById(idToDelete);

        this.dipendenteRepo.delete(res);

        return res;
    }

    public String uploadAvatar(MultipartFile file, long idToUpdate) {

        this.findById(idToUpdate);

        String url = null;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }

        this.dipendenteRepo.updateAvatarById(url, idToUpdate);

        return url;


    }


}
