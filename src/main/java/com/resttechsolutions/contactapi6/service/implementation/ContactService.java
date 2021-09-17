package com.resttechsolutions.contactapi6.service.implementation;

import com.resttechsolutions.contactapi6.entity.Contact;
import com.resttechsolutions.contactapi6.exception.contact.*;
import com.resttechsolutions.contactapi6.repository.ContactRepository;
import com.resttechsolutions.contactapi6.service.IService;
import com.resttechsolutions.contactapi6.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IService<Contact, Long> {

    private Logger log = LoggerFactory.getLogger(ContactService.class);
    private ContactRepository service;
    private MailService mailService;

    public ContactService(ContactRepository service, MailService mailService) {
        this.service = service;
        this.mailService = mailService;
    }

    @Override
    public Contact create(Contact contact) throws Exception {
        log.info("ContactService create init");

        try{
            if (contact.getId() != null)
                throw new ContactAlReadyExistException("This contact already exist");

//            if (contact.getName().isEmpty() || contact.getEmail().isEmpty() || contact.getPhones().isEmpty() || contact.getAddresses().isEmpty())
//                throw new ContactEmptyFieldException("All the fields must to be filled up");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi contact created", "The contact was created");

            return service.save(contact);
        } catch (Exception e){
            log.error("Contact can\'t be created");
            e.printStackTrace();
            throw new ContactException(e.getMessage());
        }
    }

    @Override
    public Contact findById(Long aLong) throws Exception {
        log.info("ContactService findById init");

        try{
            if (aLong == null)
                throw new ContactIdEmptyException("The contact Id can\'t be empty");


            Contact contact = service.findById(aLong).orElse(new Contact());

            if (contact.isNew())
                throw new ContactNotFoundException("The contact couldn\'t be found");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi looking for a contact", "The contact was found");

            return contact;
        } catch (Exception e){
            log.error("Contact can\'t be found");
            e.printStackTrace();
            throw new ContactException(e.getMessage());
        }
    }

    @Override
    public List<Contact> findAll() throws Exception {
        log.info("ContactService findAll init");

        try{
            List<Contact> contacts = service.findAll();

            if (contacts.isEmpty())
                throw new ContactListEmptyException("The contact list couldn\'t be loaded");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi contact list", "The contact list was loaded");

            return contacts;
        } catch (Exception e){
            log.error("Contact list can\'t be loaded");
            e.printStackTrace();
            throw new ContactException(e.getMessage());
        }
    }

    @Override
    public Contact update(Contact contact) throws Exception {
        log.info("ContactService update init");

        try{
            Contact contactUpdated = service.save(contact);

            if (contactUpdated.isNew())
                throw new ContactUpdatedException("The contact couldn\'t be updated");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi contact updated", "The contact was updated");

            return contactUpdated;
        } catch (Exception e){
            log.error("The contact couldn\'t be updated");
            e.printStackTrace();
            throw new ContactException(e.getMessage());
        }
    }

    @Override
    public void delete(Long aLong) throws Exception {
        log.info("ContactService delete init");

        try{

            if (aLong == null)
                throw new ContactIdEmptyException("The id can\'t be null");

            Contact contact = service.findById(aLong).get();

            if (contact == null)
                throw new ContactNotFoundException("The contact to be deleted doesn\'t exist");

            service.delete(contact);

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi contact deleted", "The contact was deleted");
        } catch (Exception e){
            log.error("The contact couldn\'t be deleted");
            e.printStackTrace();
            throw new ContactException(e.getMessage());
        }
    }
}
