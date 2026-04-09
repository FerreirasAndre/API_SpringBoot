package APISpring.services;

import org.springframework.stereotype.Service;

import APISpring.entities.Usuario;
import APISpring.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository repository;
	
	public UsuarioService(UsuarioRepository repository) {
	this.repository = repository;
	}
	
	public String salvarUsuario (Usuario usuario) {
		repository.save(usuario);
		return "Usuário cadastrado com sucesso!!!";
	}
	
	public Usuario buscaUsuarioId (Integer id) {
		return repository.findById(id).orElseThrow(
				()  -> new RuntimeException("Id não foi encontrado"));
	}
	
	public Usuario buscaUsuarioEmail (String email) {
		return repository.findByEmail(email).orElseThrow(
				() ->  new RuntimeException("E-mail não encontrado"));
				
	}
	

}
