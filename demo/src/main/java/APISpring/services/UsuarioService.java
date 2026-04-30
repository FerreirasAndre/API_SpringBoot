package APISpring.services;

import java.util.List;
import java.util.Optional;
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
    
    // CORREÇÃO: Usar .orElse(null) para não quebrar o servidor
    public Usuario buscaUsuarioId (Integer id) {
        return repository.findById(id).orElse(null);
    }
    
    public Usuario buscaUsuarioEmail (String email) {
        return repository.findByEmail(email).orElse(null);
    }
    
    public void deletaPorId (Integer id) {
        repository.deleteById(id);
    }
    
    public void deletaPorEmail (String email) {
        repository.deleteByEmail(email);
    }
    
    public List<Usuario> procuratodos (){
        return repository.findAll();
    }
    
    // CORREÇÃO: Verificação de segurança antes de editar
    public String editarusuario(Integer id, Usuario usuario) {
        Optional<Usuario> usuarioExistente = repository.findById(id);
        
        if (usuarioExistente.isPresent()) {
            Usuario response = usuarioExistente.get();
            response.setNome(usuario.getNome());
            response.setEmail(usuario.getEmail());
            response.setSenha(usuario.getSenha());
            
            repository.save(response);
            return "Usuário editado com sucesso!";
        }
        
        return "Usuário não encontrado para edição.";
    }
}