package APISpring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import APISpring.entities.Usuario;
import APISpring.services.UsuarioService;

@RestController
@RequestMapping(value="usuario")
public class UsuarioControllers {
	
	private final UsuarioService service;
	
	public UsuarioControllers(UsuarioService service) {
		this.service = service;
	}
	
//	@GetMapping
//	public List<Usuario> procuraTodos(){
//		return
//	}
	
	@GetMapping(value = "/{id}")
	public Usuario procuraPorId(@PathVariable Integer id) {
		return service.buscaUsuarioId(id);
	}
	
	@PostMapping
	public String adicionaUsuario (@RequestBody Usuario usuario) {
		String response = service.salvarUsuario(usuario);
		return response;
	}

}
