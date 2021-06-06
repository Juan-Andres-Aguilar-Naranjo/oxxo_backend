package mx.edu.itlp.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devops")
class Mensaje {

	@GetMapping("/hola")
	public String mostrarHola() {
		return "Hola GitHub Actions"; 
	}
}
