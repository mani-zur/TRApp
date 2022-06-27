package group.streetwear.trapp.ui;

import group.streetwear.trapp.ldap.Authority;
import group.streetwear.trapp.timeRecord.TimeRecordDto;
import group.streetwear.trapp.timeRecord.TimeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@Validated
public class UIController {

    @Autowired
    TimeRecordService timeRecordService;

    @GetMapping("/")
    public String showUserList(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "index";
    }

    @GetMapping("/reportTime")
    public String reportTimeForm(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("timeRecord",
                new TimeRecordDto(LocalDate.now(), LocalTime.of(07,00), LocalTime.of(15, 00))
        );
        return "reportTime";
    }

    @PostMapping("/reportTime")
    public String addReportedTime(@Valid @ModelAttribute("timeRecord") TimeRecordDto timeRecordDto,
                                  BindingResult bindingResult,
                                  Model model,
                                  Authentication authentication) {

        if (bindingResult.hasErrors()) {
            return "reportTime";
        }

        model.addAttribute("username", authentication.getName());
        this.timeRecordService.saveSingleRecord(timeRecordDto, authentication.getName());
        return "redirect:calendar";
    }

    @GetMapping(value = {"/calendar", "calendar/{year}/{month}"})
    public String getCalendar(Model model,
                              @PathVariable(required = false) Integer year,
                              @PathVariable(required = false) @Min(1) @Max(12) Integer month,
                              Authentication authentication
    ) {
        LocalDate requestedMonth = LocalDate.now();

        if (year != null && month != null) {
            requestedMonth = LocalDate.ofYearDay(year, 1).withMonth(month);
        }

        List<TimeRecordDto> timeRecordDtos = timeRecordService.getAllForUserInMonth(authentication.getName(), requestedMonth);

        model.addAttribute("timeRecords", timeRecordDtos);
        model.addAttribute("month", requestedMonth);
        model.addAttribute("username", authentication.getName());

        return "calendar";
    }

    @GetMapping("/admins")
    @ResponseBody
    @PreAuthorize("hasAuthority('" + Authority.ADMIN + "')")
    public String admins(Authentication authentication) {
        return authentication.toString();
    }
}