package APISpring.services;

import java.util.List;

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
	
	public void deletaPorId (Integer id) {
		repository.deleteById(id);
	}
	
	public void deletaPorEmail (String email) {
		repository.deleteByEmail(email);
	}
	
	public List<Usuario> procuratodos (){
		List<Usuario> ListaUsuarios = repository.findAll();
		return ListaUsuarios;
	}
	
	public String editarusuario(Integer id, Usuario usuario) {
		Usuario response = repository.findById(id).get();
		
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setSenha(usuario.getSenha());
				
		repository.save(response);
		return "Usuário editado com sucesso!";
	}
}
