package com.resttechsolutions.contactapi6.service.implementation;

import com.resttechsolutions.contactapi6.entity.Phone;
import com.resttechsolutions.contactapi6.exception.phone.*;
import com.resttechsolutions.contactapi6.repository.PhoneRepository;
import com.resttechsolutions.contactapi6.service.IService;
import com.resttechsolutions.contactapi6.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService implements IService<Phone, Long> {

    private PhoneRepository service;
    private Logger log = LoggerFactory.getLogger(PhoneService.class);
    private MailService mailService;

    public PhoneService(PhoneRepository service, MailService mailService) {
        this.service = service;
        this.mailService = mailService;
    }

    @Override
    public Phone create(Phone phone) throws Exception {
        log.info("PhoneService create init");

        try{

            if (phone.getId() != null)
                throw new PhoneAlreadyExistException("The phone already exist");
//            if (phone.getName() == null || phone.getPhoneNumber() == null || phone.getContact() == null)
//                throw new PhoneEmptyFieldException("All the fields must be filled up");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi phone created", "The phone was created");

            return service.save(phone);
        } catch (Exception e){
            log.error("The phone couldn\'t be created");
            e.printStackTrace();
            throw new PhoneException(e.getMessage());
        }
    }

    @Override
    public Phone findById(Long aLong) throws Exception {
        log.info("PhoneService findById init");

        try{

            if (aLong == null)
                throw new PhoneIdEmptyException("The phone id must exist");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi looking for a phone", "The phone was found");

            return service.findById(aLong).orElse(new Phone());
        } catch (Exception e){
            log.error("The phone couldn\'t be found");
            e.printStackTrace();
            throw new PhoneException(e.getMessage());
        }
    }

    @Override
    public List<Phone> findAll() throws Exception {
        log.info("PhoneService findAll init");

        try{
            List<Phone> phones = service.findAll();

            if (phones.isEmpty())
                throw new PhoneListEmptyException("The phone list couldn\'t be loaded");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi phone list", "The phone list was loaded");

            return phones;
        } catch (Exception e){
            log.error("The phone list couldn\'t be loaded");
            e.printStackTrace();
            throw new PhoneException(e.getMessage());
        }
    }

    @Override
    public Phone update(Phone phone) throws Exception {
        log.info("PhoneService update init");

        try{

           Phone phoneUpdated = service.save(phone);

           if (phoneUpdated.isNew())
               throw new PhoneUpdatedException("The phone couldn\'t be updated");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi phone updated", "The phone was updated");

            return phoneUpdated;
        } catch (Exception e){
            log.error("The phone couldn\'t be updated");
            e.printStackTrace();
            throw new PhoneException(e.getMessage());
        }
    }

    @Override
    public void delete(Long aLong) throws Exception {
        log.info("PhoneService delete init");

        try{

            if (aLong == null)
                throw new PhoneIdEmptyException("The id can\'t be null");

            service.delete(service.findById(aLong).get());

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi phone deleted", "The phone was deleted");

        } catch (Exception e){
            log.error("The phone couldn\'t be deleted");
            e.printStackTrace();
            throw new PhoneException(e.getMessage());
        }
    }
}
