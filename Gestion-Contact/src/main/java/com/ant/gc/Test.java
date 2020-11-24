package com.ant.gc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Component;

import com.ant.gc.entities.Users;
import com.ant.gc.models.MessageResponse;
import com.ant.gc.repositories.UsersRepository;
import com.ant.gc.services.UsersService;

@Component
public class Test implements CommandLineRunner  {

		@Autowired
		private UsersService usersService;
		@Autowired
		private UsersRepository usersRepository;
		
		@Override
		public void run(String...args ) throws Exception{
			
//			Users user = new Users();
//			user.setNom("Ali");
//			user.setPassword("funnny");
//			user.setEmail("hello@tn.com");
//			user.setPrenom("fff");
//			user.setUsername("ADMIN");
//			user.setPassword("ADMIN");
//			user.setRole("ADMIN");
//			
			Users user = new Users();
			user.setNom("Ali");
			user.setPassword("funnny");
			user.setEmail("hello@tn.com");
			user.setPrenom("fff");
			user.setUsername("ADMIN");
			user.setPassword("ADMIN");
			user.setRole("ADMIN");
			
//			
//			Users user1 = new Users();
//			user1.setNom("Mohamed Ali");
//			user1.setPassword("fun2nnysss");
//			user1.setEmail("hello2@tn.com");
//			user1.setPrenom("fffaa");
//			user1.setUsername("USER");
//			user1.setPassword("USER");
//			user1.setRole("USER");
//			
//		
			
//			
			usersService.save(user);
//			usersService.save(user1);
//					usersRepository.deleteAllInBatch();	
		
			 
		//MessageResponse result = usersService.save(user);
//	System.out.println(result);
			
			
		}
		
	
	
	
	
	
	
//	@Autowired
//	private ContactRepository contactRepository;
//	
//	@Autowired
//	private ClientRepository clientRepository;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//	List<Contact>list = contactRepository.hetByNom("ali");
//	
//	Client clt =new Client();
//	clt.setNom("hamma");
//	clt.setEmail("fsdds");
//	clt.setId(5);
//	clt.setPrenom("ddd");
//	
//	clientRepository.save(clt);
//	
//	Users user = new Users();
//	user.setEmail("dsdssddsez");
//	user.setUsername("eee");
//	user.setId(8);
//	user.setPrenom("prenom");
//	user.setPassword("qsd");
//	user.setNom("qsd");
//	}

}
