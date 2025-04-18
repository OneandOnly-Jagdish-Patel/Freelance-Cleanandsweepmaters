package ca.sheridancollege.pate5749.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.pate5749.Bean.Contact;
import ca.sheridancollege.pate5749.Email.EmailServiceImpl;
import ca.sheridancollege.pate5749.Repository.ContactRepository;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	public ContactRepository cocrepo;
	
	@Autowired
	EmailServiceImpl esi;
	
	@GetMapping("/")
	public String main() {
		return "index.html";
	}
	@GetMapping("/about")
	public String aboutus() {
		return "about.html";
	}
	@GetMapping("/contactus")
	public String contact(HttpSession session,Model model) {
		model.addAttribute("contactform",new Contact());
		if (session.getAttribute("hasSubmitted") == null) {
            session.setAttribute("hasSubmitted", false);
        }
		return "contacts.html";
	}
	@GetMapping("/typography")
	public String typography() {
		return "typography.html";
	}
	@GetMapping("/employee")
	public String employee() {
		return "employee.html";
	}
	@GetMapping("/service")
	public String services(Model model) {
		model.addAttribute("contactform",new Contact());
		return "contacts.html";
	}
	@PostMapping("/contactus")
	public String contactus(@ModelAttribute Contact contactform, Model model, HttpSession session) {
		
		Boolean hasSubmitted = (Boolean) session.getAttribute("hasSubmitted");
		if (Boolean.TRUE.equals(hasSubmitted)) {
            model.addAttribute("emailsent", false);
            model.addAttribute("contactform",new Contact());
            return "contacts.html";
        }
		try {
			esi.sendMailWithInline(contactform.getName(),
			contactform.getEmail());
			esi.sendMailtoOwner(contactform.getName(),contactform.getAddress(),contactform.getEmail(),contactform.getSubject(),contactform.getMessage(), contactform.getId());
			model.addAttribute("emailsent", true);
			session.setAttribute("hasSubmitted", true);
			} catch (MessagingException e) {
			model.addAttribute("emailsent", false);
			}
		cocrepo.save(contactform);
		model.addAttribute("contactform",new Contact());
		return "contacts.html";
	}
	
}
