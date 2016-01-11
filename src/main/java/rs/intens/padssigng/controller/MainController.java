package rs.intens.padssigng.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.intens.padssigng.model.ResponseModel;

@Controller
public class MainController {
	
	private final static String EXTENSION = "PNG";
	
	private final static String PATH = "D:\\out.png";
	
	private final static String STATUS_SUCCESS = "Success";
	
	private final static String STATUS_ERROR = "Error";
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	ResponseModel handleFileUpload(@RequestBody String jsonImg) {
		byte[] imageBytes = extracted(jsonImg);
		ResponseModel resModel = new ResponseModel();
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(
					imageBytes));
			ImageIO.write(img, EXTENSION, new File(PATH));
			resModel.setResponse(STATUS_SUCCESS);
		} catch (IOException e) {
			resModel.setResponse(STATUS_ERROR);
			e.printStackTrace();
		}
		return resModel;
	}

	@SuppressWarnings("restriction")
	private byte[] extracted(String jsonImg) {
		return javax.xml.bind.DatatypeConverter.parseBase64Binary(jsonImg);
	}

}
