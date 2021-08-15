package one.digitalinnovation.personapi.mapper;


import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    Phone toModel(PhoneDTO phoneDTO);

    PhoneDTO toDTO(Phone phone);
}
