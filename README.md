# MailWeb
Mail System based on springboot, including quartz + javaMailSender + log4j2 + thymeleaf
spring 配置 quartz, 接收到request开启scheduler.
job 定时1s 间隔 execute 从歌词文本读取一句歌词倒序发送给target mail address.
