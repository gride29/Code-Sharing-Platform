package platform.code;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CodeRestController {

    private final CodeService codeService;

    @GetMapping(path = "/api/code/latest", produces = "application/json")
    public List<Code> getLatestApiCodes() {
        return codeService.getLatest();
    }

    @GetMapping(path = "/api/code/{id}")
    public Code getApiCode(@PathVariable long id) {
        return codeService.get(id);
    }

    @PostMapping(path = "/api/code/new", consumes = "application/json")
    public Map<String, ?> addNewCode(@RequestBody Code newCode) {
        return codeService.add(newCode);
    }
}
