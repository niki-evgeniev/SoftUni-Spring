package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstImportDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private static String CONSTELLATIONS_FILE_PATH = "src/main/resources/files/json/constellations.json";

    private final ConstellationRepository constellationRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, ModelMapper modelMapper,
                                    ValidationUtil validationUtil, Gson gson) {
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of(CONSTELLATIONS_FILE_PATH));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder  sb = new StringBuilder();

        ConstImportDto[] constImportDtos = gson.fromJson(readConstellationsFromFile(), ConstImportDto[].class);

        for (ConstImportDto dto : constImportDtos) {
            sb.append(System.lineSeparator());

            Optional<Constellation> findName = constellationRepository.findByName(dto.getName());

            if (findName.isPresent() || !validationUtil.isValid(dto)) {
                sb.append("Invalid constellation");
            } else {
                Constellation map = modelMapper.map(dto, Constellation.class);
                sb.append(String.format("Successfully imported constellation %s - %s",
                        dto.getName(), dto.getDescription()));

                constellationRepository.save(map);
            }
        }
        return sb.toString().trim();
    }
}
