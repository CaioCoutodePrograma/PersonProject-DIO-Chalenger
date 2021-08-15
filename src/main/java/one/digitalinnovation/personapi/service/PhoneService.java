package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;

import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Phone;
import one.digitalinnovation.personapi.exception.PhoneNotFoundException;
import one.digitalinnovation.personapi.mapper.PhoneMapper;
import one.digitalinnovation.personapi.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    private final one.digitalinnovation.personapi.mapper.PhoneMapper phoneMapper = PhoneMapper.INSTANCE;

    public MessageResponseDTO createPhone(PhoneDTO PhoneDTO) {
        Phone PhoneToSave = phoneMapper.toModel(PhoneDTO);

        Phone savedPhone = phoneRepository.save(PhoneToSave);
        return createMessageResponse(savedPhone.getId(), "Created Phone with ID ");
    }

    public List<PhoneDTO> listAll() {
        List<Phone> allPeople = phoneRepository.findAll();
        return allPeople.stream()
                .map(phoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PhoneDTO findById(Long id) throws PhoneNotFoundException {
        Phone Phone = verifyIfExists(id);

        return phoneMapper.toDTO(Phone);
    }

    public void delete(Long id) throws PhoneNotFoundException {
        verifyIfExists(id);
        phoneRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PhoneDTO PhoneDTO) throws PhoneNotFoundException {
        verifyIfExists(id);

        Phone PhoneToUpdate = phoneMapper.toModel(PhoneDTO);

        Phone updatedPhone = phoneRepository.save(PhoneToUpdate);
        return createMessageResponse(updatedPhone.getId(), "Updated Phone with ID ");
    }

    private Phone verifyIfExists(Long id) throws PhoneNotFoundException {
        return phoneRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
