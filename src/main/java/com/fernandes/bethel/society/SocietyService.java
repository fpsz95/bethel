package com.fernandes.bethel.society;

import com.fernandes.bethel.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocietyService {

    private final SocietyRepository societyRepository;

    @Autowired
    public SocietyService(SocietyRepository societyRepository) {
        this.societyRepository = societyRepository;
    }

    public String register(Society society) {
        societyRepository.save(society);
        return "Society has been Registered Successfully";
    }

//    public List<Society> getAllSocieties() {
//        return societyRepository.findAllSocietyNames();
//    }

}
