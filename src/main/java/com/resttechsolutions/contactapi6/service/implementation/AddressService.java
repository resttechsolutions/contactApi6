package com.resttechsolutions.contactapi6.service.implementation;

import com.resttechsolutions.contactapi6.entity.Address;
import com.resttechsolutions.contactapi6.exception.address.*;
import com.resttechsolutions.contactapi6.repository.AddressRepository;
import com.resttechsolutions.contactapi6.service.IService;
import com.resttechsolutions.contactapi6.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IService<Address, Long> {

    private Logger log = LoggerFactory.getLogger(AddressService.class);
    private AddressRepository service;
    private MailService mailService;

    public AddressService(AddressRepository service, MailService mailService) {
        this.service = service;
        this.mailService = mailService;
    }

    @Override
    public Address create(Address address) throws Exception {
        log.info("AddressService create init");
        try{

            if (address.getId() != null)
                throw new AddressAlreadyExistException("The address already exist");
//            if (address.getName() == null || address.getAddressName() == null || address.getContact() == null)
//                throw new AddressEmptyFieldException("All the fields must be filled up");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi address created", "The address was created");
            log.info("saving address");
            return service.save(address);
        } catch (Exception e){
            log.error("The address couldn\'t be created");
            e.printStackTrace();
            throw new AddressException(e.getMessage());
        }
    }

    @Override
    public Address findById(Long aLong) throws Exception {
        log.info("AddressService findById init");

        try{

            if (aLong == null)
                throw new AddressIdEmptyException("The address id must exist");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi looking for an address", "The address was found");

            return service.findById(aLong).orElse(new Address());
        } catch (Exception e){
            log.error("The address couldn\'t be found");
            e.printStackTrace();
            throw new AddressException(e.getMessage());
        }
    }

    @Override
    public List<Address> findAll() throws Exception {
        log.info("AddressService findAll init");

        try{
            List<Address> addresses = service.findAll();

            if (addresses.isEmpty())
                throw new AddressListEmptyException("The address list couldn\'t be loaded");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi address list", "The address list was loaded");

            return addresses;
        } catch (Exception e){
            log.error("The address list couldn\'t be loaded");
            e.printStackTrace();
            throw new AddressException(e.getMessage());
        }
    }

    @Override
    public Address update(Address address) throws Exception {
        log.info("AddressService update init");

        try{

            Address addressUpdated = service.save(address);

            if (addressUpdated.isNew())
                throw new AddressUpdatedException("The address couldn\'t be updated");

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi address updated", "The address was updated");

            return addressUpdated;
        } catch (Exception e){
            log.error("The address couldn\'t be updated");
            e.printStackTrace();
            throw new AddressException(e.getMessage());
        }
    }

    @Override
    public void delete(Long aLong) throws Exception {
        log.info("AddressService delete init");

        try{

            if (aLong == null)
                throw new AddressIdEmptyException("The id can\'t be null");

            service.delete(service.findById(aLong).get());

            mailService.sendMailNotification("rafaelalfonso82@gmail.com", "ContactApi address deleted", "The address was deleted");

        } catch (Exception e){
            log.error("The address couldn\'t be deleted");
            e.printStackTrace();
            throw new AddressException(e.getMessage());
        }
    }
}
