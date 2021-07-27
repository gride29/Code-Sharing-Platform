package platform.code;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    public Map<String, ?> add(Code code) {
        codeRepository.save(code);
        return Map.of(
                "id", "" + code.getId() + ""
        );
    }

    public Code get(long id) {
        return codeRepository.findById(id).orElseThrow(CodeNotFoundException::new);
    }

    public List<Code> getLatest() {
        List<Code> latest = codeRepository.findAllByOrderByDateDesc();
        if (latest.size() > 10) {
            return latest.subList(0,10);
        } else {
            return latest;
        }
    }
}
