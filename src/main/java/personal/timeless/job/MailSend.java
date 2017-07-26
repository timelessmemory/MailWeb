package personal.timeless.job;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import personal.timeless.service.MailService;
import personal.timeless.utils.FileUtil;
/*
 Task
 */
@Component
public class MailSend {
	private static int i = 0;
	private List<String> ls = null;
	
	@Autowired
	private MailService mailService;
	
	private static Logger logger = LogManager.getLogger(MailSend.class.getName());

	public MailSend(){
		ls = FileUtil.readFile();
		i = ls.size() - 1;
	}
	
	public void execute() {
		if (i >= 0) {
			logger.info(ls.get(i));
			mailService.send(ls.get(0), "<p>" + ls.get(i) + "</p>", "target@qq.com");
			i--;
		} else {
			logger.info("over");
			i = ls.size() - 1;
		}
	}
}
