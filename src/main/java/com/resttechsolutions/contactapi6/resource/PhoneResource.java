package com.resttechsolutions.contactapi6.resource;

import com.resttechsolutions.contactapi6.entity.Address;
import com.resttechsolutions.contactapi6.entity.Contact;
import com.resttechsolutions.contactapi6.entity.Phone;
import com.resttechsolutions.contactapi6.exception.phone.*;
import com.resttechsolutions.contactapi6.resource.dto.Response;
import com.resttechsolutions.contactapi6.resource.dto.request.PhoneRequestDTO;
import com.resttechsolutions.contactapi6.resource.dto.response.PhoneResponseDTO;
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
@RequestMapping("/phone")
public class PhoneResource extends AbstractResource<PhoneRequestDTO, Long> {
    private Logger log = LoggerFactory.getLogger(PhoneResource.class);

    private ModelMapper modelMapper;
    private IService<Contact, Long> contactServie;
    private IService<Phone, Long> phoneService;
    private IService<Address, Long> addressService;

    public PhoneResource(ModelMapper modelMapper, IService<Contact, Long> contactServie, IService<Phone, Long> phoneService, IService<Address, Long> addressService) {
        this.modelMapper = modelMapper;
        this.contactServie = contactServie;
        this.phoneService = phoneService;
        this.addressService = addressService;
    }

    @Override
    public ResponseEntity<Response> create(@RequestBody PhoneRequestDTO request, Response response) {
        log.info("PhoneResource create init");

        try {
            Phone phone = GenericMapper.map(request, Phone.class, this.modelMapper);

            response.response.put("data", GenericMapper.map(this.phoneService.create(phone), PhoneResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneAlreadyExistException e) {
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
        log.info("PhoneResource findById init");

        try {
            response.response.put("data", GenericMapper.map(this.phoneService.findById(id), PhoneResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneNotFoundException e) {
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
        log.info("PhoneResource findAll init");

        try {

            response.response.put("data", GenericMapper.mapCollection(this.phoneService.findAll(), PhoneResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneListEmptyException e) {
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
    public ResponseEntity<Response> update(@RequestBody PhoneRequestDTO request, Response response) {
        log.info("PhoneResource update init");

        try {
            Phone phone = GenericMapper.map(request, Phone.class, this.modelMapper);

            response.response.put("data", GenericMapper.map(this.phoneService.update(phone), PhoneResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneUpdatedException e) {
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
        log.info("PhoneResource delete init");

        try {

            this.phoneService.delete(id);

            response.response.put("data", null);
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneDeleteException e) {
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
