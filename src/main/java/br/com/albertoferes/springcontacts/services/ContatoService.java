package br.com.albertoferes.springcontacts.services;

import br.com.albertoferes.springcontacts.domain.Contato;
import br.com.albertoferes.springcontacts.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public List<Contato> buscarTodos() {
        return repository.findAll();
    }

    public Contato buscarPorId(Integer id) {
        Optional<Contato> contatoOptional = repository.findById(id);
        return contatoOptional.orElse(new Contato());
    }

    public Contato salvar(Contato contato) {
        Contato contatoSaved = repository.save(contato);
        contato.setId(contatoSaved.getId());
        return contato;
    }

    public void remover(Integer id) {
        repository.deleteById(id);
    }

    public Contato atualizar(Contato contato) {
        return repository.save(contato);
    }
}
