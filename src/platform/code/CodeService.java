package platform.code;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CodeService  {

    private final CodeRepository codeRepository;

    public Map<String, ?> add(Code code) {
        Code newCode = new Code(code.getCode(), code.getViews(), code.getTime());
        code.setPersonalCounter(Code.globalCounter);
        codeRepository.save(newCode);
        Code.globalCounter++;
        return Map.of(
                "id", newCode.getId()
        );
    }

    public Code get(String id) {
        Code code = codeRepository.findById(UUID.fromString(id)).orElseThrow(CodeNotFoundException::new);
        long diffTime = code.getBaseTime() - ChronoUnit.SECONDS.between(code.getDate(), LocalDateTime.now());
        if (code.getViews() == 0 && code.getTime() == 0 && code.getBaseTime() == 0 && code.getBaseViews() == 0) {
            return code;
        } else if (code.getViews() > 0 && code.getTime() == 0) {
            long updatedViews = code.getViews() - 1;
            code.setViews(updatedViews);
            return code;
        } else if (code.getTime() > 0 && code.getViews() == 0 && diffTime > 0) {
            code.setTime(diffTime);
            return code;
        } else if (code.getViews() == 0 || diffTime <= 0) {
            throw new CodeNotFoundException();
        } else {
            long updatedViews = code.getViews() - 1;
            code.setViews(updatedViews);
            code.setTime(diffTime);
            return code;
        }
    }

    public List<Code> getLatest() {
        List<Code> latest = codeRepository.findAll().stream().filter(
                code -> code.getTime() == 0 && code.getViews() == 0
        ).sorted().collect(Collectors.toList());

        if (latest.size() > 10) {
            return latest.subList(0, 10);
        } else {
            return latest;
        }
    }
}
