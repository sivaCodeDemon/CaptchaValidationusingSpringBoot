package com.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.NewUser;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;

/**
 * 
 * @author SivaDev
 * Creating the captch for the validation 
 */
public class CaptchaUtil {

	private static final Logger logger= LoggerFactory.getLogger(CaptchaUtil.class);
	
	
	 //Captcha class object
    public static Captcha createCaptcha(int width, int height) {
	logger.info("INSIDE : createCaptcha() : CaptchaUtil.class");
	
        return new Captcha.Builder(width,height)
                .addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();
       
    }
    
    
    //convert to binary String
    public static String encodeBase64(Captcha captcha) {
	
	logger.info("START : encodeBase64() : CaptchaUtil.class");
	
        String imageData = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(),"png",os);
            byte[] arr = Base64.getEncoder().encode(os.toByteArray());
            imageData = new String(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        logger.info("END   : encodeBase64() : CaptchaUtil.class");
        
        return imageData;
    }
    
    public  static void setCaptchaForNewUser(NewUser user) {
    	 logger.info("START   : setCaptchaForNewUser() : CaptchaUtil.class");
	Captcha cap= createCaptcha(250, 80);
	user.setImage(encodeBase64(cap));
	user.setCaptcha("");
	user.setHidden(cap.getAnswer());
	 logger.info("START   : setCaptchaForNewUser() : CaptchaUtil.class");

    }

}
