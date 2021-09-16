package com.resttechsolutions.contactapi6.resource;

import com.resttechsolutions.contactapi6.entity.Address;
import com.resttechsolutions.contactapi6.entity.Contact;
import com.resttechsolutions.contactapi6.entity.Phone;
import com.resttechsolutions.contactapi6.exception.address.*;
import com.resttechsolutions.contactapi6.resource.dto.Response;
import com.resttechsolutions.contactapi6.resource.dto.request.AddressRequestDTO;
import com.resttechsolutions.contactapi6.resource.dto.response.AddressResponseDTO;
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
@RequestMapping("/address")
public class AddressResource extends AbstractResource<AddressRequestDTO, Long> {

    private Logger log = LoggerFactory.getLogger(PhoneResource.class);

    private ModelMapper modelMapper;
    private IService<Contact, Long> contactServie;
    private IService<Phone, Long> phoneService;
    private IService<Address, Long> addressService;

    @Override
    public ResponseEntity<Response> create(@RequestBody AddressRequestDTO request, Response response) {
        log.info("AddressResource create init");

        try {
            Address address = GenericMapper.map(request, Address.class, this.modelMapper);

            response.response.put("data", GenericMapper.map(this.addressService.create(address), AddressResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (AddressAlreadyExistException e) {
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
        log.info("AddressResource findById init");

        try {

            response.response.put("data", GenericMapper.map(this.addressService.findById(id), AddressResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (AddressNotFoundException e) {
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
        log.info("AddressResource findAll init");

        try {

            response.response.put("data", GenericMapper.map(this.addressService.findAll(), AddressResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (AddressListEmptyException e) {
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
    public ResponseEntity<Response> update(@RequestBody AddressRequestDTO request, Response response) {
        log.info("AddressResource update init");

        try {
            Address address = GenericMapper.map(request, Address.class, this.modelMapper);

            response.response.put("data", GenericMapper.map(this.addressService.update(address), AddressResponseDTO.class, this.modelMapper));
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (AddressUpdatedException e) {
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
        log.info("AddressResource delete init");

        try {
            this.addressService.delete(id);

            response.response.put("data", null);
            response.response.put("code", ResponseDictionary.SUCCESS_CODE);
            response.response.put("message", HttpStatus.CREATED);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (AddressDeleteExcetion e) {
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
