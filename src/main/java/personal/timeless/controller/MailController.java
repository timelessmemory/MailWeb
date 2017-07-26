package personal.timeless.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mail")
@Controller
public class MailController {
	
	@Resource(name="scheduler")
	private Scheduler scheduler;//注入配置类中的scheduler
	
	@GetMapping("/send")
    public String mail(Model model) throws SchedulerException {
		scheduler.start();
		model.addAttribute("date", new Date());
        return "result";
    }
	
	@GetMapping("/stop")
    public String stop(Model model) throws SchedulerException {
		scheduler.shutdown();
		model.addAttribute("date", new Date());
        return "result";
    }
}
