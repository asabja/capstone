package com.claim.capstone.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.claim.capstone.model.Users;
import com.claim.capstone.repository.OrderRepository;
import com.claim.capstone.repository.UserRepository;

@Component
public class WebUtils {
	
	private JavaMailSender sender; 
	
	
	private UserRepository userRepository; 
	
	
	@Autowired
	HttpServletRequest request;
	
	private static final String UPLOADED_FOLDER = "static" + File.separator + "img";
	
	@Autowired
	public WebUtils(JavaMailSender sender, UserRepository userRepository) {
		super();
		this.sender = sender;
		this.userRepository = userRepository;
		
	}

	public void sendMail(String to, String msg, String subject) {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setTo(to);
			helper.setTo(msg);
			helper.setTo(subject);
			
		}catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void addProfilePhoto(MultipartFile file, long id, String folder)throws IllegalStateException, IOException {
				
			try {
				
				//save dir 
				String destDir = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER) + File.separator + folder + File.separator + id + File.separator + "profile";
				
				// initialize file
				File dir = new File(destDir);
				 
				 // if folder does no exist- create it 
				 if(!dir.exists()) {
					  new File(destDir).mkdirs();
					 
				 }
				 
				 //get file from form 
				 MultipartFile multipartFile = (MultipartFile) file;
				 String fileName = file.getOriginalFilename(); 
				 File doc = new File(destDir + File.separator + fileName);
				 
				 //rename file
				 File rename = new File(destDir + File.separator + "user_" + id + ".jpg");
				 
				 //save to file system 
				 multipartFile.transferTo(rename);
				 Users user = userRepository.findById(id).get();
				 user.setImage("user"+ id + ".jpg");
				 userRepository.save(user);
				 
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public void multipleSave(MultipartFile[] files, long id)throws IllegalStateException, IOException {
		
		try {
			
			String realPathToUploads= request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER);
			
			File dir = new File(realPathToUploads + File.separator + id);
			
			List<String> fileNames = new ArrayList<String>();
			
			if(!dir.exists()) {
				dir.mkdirs(); 
			}
			if (files != null && files.length > 0) {
				for(MultipartFile file : files) {
					String fileName= file.getOriginalFilename();
					String filePath = realPathToUploads + File.separator + id + File.separator + fileName;
				 
					File destination = new File (filePath);
					try {
						file.transferTo(destination);
					}catch(Exception e ) {
						e.printStackTrace();
					}
					fileNames.add(fileName);			
				}		
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeFiles (long id, String image) {
		
		try {
			
			String realPathToUploads= request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER);

			File file = new File(realPathToUploads + File.separator + id + File.separator + image + ""); 
			
			if (file.delete()) {
				if(userRepository.findById(id).get().getImage() != null) {
					Users cus = userRepository.findById(id).get();
					cus.setImage("");
					userRepository.save(cus);
				}
				System.out.print(file.getName()+ "is deleted");
			}else {
				System.out.print("Delete operation failed"); 
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getFiles (Model model, long id ) {
		
		List<String> results = new ArrayList<String>();
		
		try {
			String realPathToUploads= request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER);

			File[] files = new File(realPathToUploads + File.separator + id).listFiles();
				for (File file: files) {
					if (file.isFile()) {
						results.add(file.getName());
						model.addAttribute("filename", results);
					}
					
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
