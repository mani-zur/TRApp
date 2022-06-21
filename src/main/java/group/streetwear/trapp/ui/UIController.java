package group.streetwear.trapp.ui;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/")
    public String showUserList(Model model, Authentication authentication) {
//        model.addAttribute("username", authentication.getName());
        return "index";
    }

    @GetMapping("/report")
    public String reportTime(Model model) {;
        return "report_time";
    }
}
