package com.resttechsolutions.contactapi6.resource;

import com.resttechsolutions.contactapi6.entity.Address;
import com.resttechsolutions.contactapi6.entity.Contact;
import com.resttechsolutions.contactapi6.entity.Phone;
import com.resttechsolutions.contactapi6.exception.contact.*;
import com.resttechsolutions.contactapi6.resource.dto.Response;
import com.resttechsolutions.contactapi6.resource.dto.request.ContactRequestDTO;
import com.resttechsolutions.contactapi6.resource.dto.response.ContactResponseDTO;
import com.resttechsolutions.contactapi6.service.IService;
import com.resttechsolutions.contactapi6.util.GenericMapper;
import com.resttechsolutions.contactapi6.util.ResponseDictionary;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactResource extends AbstractResource<ContactRequestDTO, Long> {

    private Logger log = LoggerFactory.getLogger(ContactResource.class);

    private ModelMapper modelMapper;
    private IService<Contact, Long> contactService;
    private IService<Phone, Long> phoneService;
    private IService<Address, Long> addressService;

    public ContactResource(ModelMapper modelMapper, IService<Contact, Long> contactService, IService<Phone, Long> phoneService, IService<Address, Long> addressService) {
        this.modelMapper = modelMapper;
        this.contactService = contactService;
        this.phoneService = phoneService;
        this.addressService = addressService;
    }

    @Override
    public ResponseEntity<Response> create(@RequestBody ContactRequestDTO request, Response response) {
        log.info("ContactResource create init");

        try{
            Contact contact = GenericMapper.map(request, Contact.class, modelMapper);

            contact.setPhones(GenericMapper.mapCollection(request.getPhones(), Phone.class, modelMapper));
            contact.setAddresses(GenericMapper.mapCollection(request.getAddresses(), Address.class, modelMapper));

            response.response.put("data", GenericMapper.map(this.contactService.create(contact), ContactResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        } catch (ContactAlReadyExistException e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();
        } catch (Exception e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> findById(@PathVariable Long id, Response response) {
        log.info("ContactResource findById init");

        try{

            response.response.put("data", GenericMapper.map(this.contactService.findById(id), ContactResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.FOUND);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ContactNotFoundException e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();
        } catch (Exception e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> findAll(Response response) {
        log.info("ContactResource findAll init");

        try{

            response.response.put("data", GenericMapper.mapCollection(this.contactService.findAll(), ContactResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.FOUND);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ContactListEmptyException e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();


        } catch (Exception e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();
        }

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> update(@RequestBody ContactRequestDTO request, Response response) {
        log.info("ContactResource update init");

        try{

            Contact contact = GenericMapper.map(request, Contact.class, this.modelMapper);

            response.response.put("data", GenericMapper.map(this.contactService.update(contact), ContactResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.FOUND);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ContactUpdatedException e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();


        } catch (Exception e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();
        }

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> delete(@PathVariable Long id, Response response) {

        log.info("ContactResource delete init");

        try{

            this.contactService.delete(id);

            response.response.put("data", null);
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.GONE);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ContactDeleteException e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();


        } catch (Exception e) {
            response.response.put("code", ResponseDictionary.ERROR_CODE);
            response.response.put("error", true);
            response.response.put("message", e.getMessage());

            e.printStackTrace();
        }

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
