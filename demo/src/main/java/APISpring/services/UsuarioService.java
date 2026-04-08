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
	
	public Usuario cadastrar (Usuario usuario) {
		if (repository.findByEmail(usuario.getEmail()).isPresent()){
			throw new IllegalArgumentException("E-mail já cadastrado");			
		}
		
		return repository.save(usuario);
	
	}
}
