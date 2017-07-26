package personal.timeless.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;

import personal.timeless.Application;

public class FileUtil {
	
	public static List<String> readFile() {
		try {
			FileReader fr = new FileReader("/home/user/workspace/MailWeb/src/main/resources/jay.lyric");
			BufferedReader bf = new BufferedReader(fr);
			
			List<String> lyrics = new ArrayList<String>();
			
			String lyric = null;
			
		    while ((lyric = bf.readLine()) != null) {
		    	if (!lyric.equals("")) {
		    		lyrics.add(lyric);
		    	}
		    }
		    
		    return lyrics;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
