package platform.code;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @GetMapping("/code/{id}")
    public ModelAndView getCode(@PathVariable String id) {
        Map<String, Object> params = new HashMap<>();
        Code code = codeService.get(id);
        params.put("code", code);
        return new ModelAndView("getCode", params);
    }

    @GetMapping("/code/latest")
    public ModelAndView getLatestCodes() {
        Map<String, Object> params = new HashMap<>();
        List<Code> latest = codeService.getLatest();
        params.put("latest", latest);
        return new ModelAndView("getLatestCodes", params);
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCode() {
        return new ModelAndView("getNewCode");
    }
}


