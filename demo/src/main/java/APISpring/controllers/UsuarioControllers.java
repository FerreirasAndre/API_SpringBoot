package APISpring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public List<Usuario> procuraTodos(){
		return service.procuratodos();
	}
	
	@GetMapping(value = "/{id}")
	public Usuario procuraPorId(@PathVariable Integer id) {
		return service.buscaUsuarioId(id);
	}
	
	@PostMapping
	public String adicionaUsuario (@RequestBody Usuario usuario) {
		String response = service.salvarUsuario(usuario);
		return response;
	}
	
	@GetMapping(value ="email")
	public Usuario procuraPorEmail(@PathVariable String email) {
		return service.buscaUsuarioEmail(email);
	}

	@DeleteMapping
	public void excluirUsuario(@PathVariable Integer id) {
		service.deletaPorId(id);
	}
	
	@PutMapping
	public String editaUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
		String response = service.editarusuario(id, usuario);
		return response;
	}
		
}
