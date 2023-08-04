package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstroImportDto;
import softuni.exam.models.dto.AstroRootImportDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    private static String ASTRONOMERS_FILE_PATH = "src/main/resources/files/xml/astronomers.xml";

    private final AstronomerRepository astronomerRepository;
    private final StarRepository starRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository,
                                 ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {

        return astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of(ASTRONOMERS_FILE_PATH));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        List<AstroImportDto> astronomers = xmlParser.fromFile(ASTRONOMERS_FILE_PATH, AstroRootImportDto.class).getAstronomers();

        for (AstroImportDto dto : astronomers) {
            sb.append(System.lineSeparator());

            Optional<Astronomer> findFirstAndLastName = astronomerRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
            Star star = starRepository.findById(dto.getStar()).orElse(null);


            if (findFirstAndLastName.isPresent()|| star == null || !validationUtil.isValid(dto)) {
                sb.append("Invalid astronomer");

            } else {
                Astronomer map = modelMapper.map(dto, Astronomer.class);

                Locale.setDefault(new Locale("en", "US"));

                map.setStar(star);
                sb.append(String.format("Successfully imported astronomer %s %s - %.2f",
                        dto.getFirstName(), dto.getLastName(), dto.getAverageObservationHours()));

                astronomerRepository.save(map);
            }
        }
        return sb.toString().trim();
    }
}
