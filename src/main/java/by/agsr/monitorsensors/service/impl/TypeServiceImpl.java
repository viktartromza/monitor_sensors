package by.agsr.monitorsensors.service.impl;

import by.agsr.monitorsensors.mapper.TypeMapper;
import by.agsr.monitorsensors.model.dto.TypeRsDto;
import by.agsr.monitorsensors.repository.TypeRepository;
import by.agsr.monitorsensors.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository repository;
    private final TypeMapper mapper;
    @Override
    public List<TypeRsDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
}
