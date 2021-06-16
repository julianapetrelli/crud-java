package br.com.albertoferes.springcontacts.repositories;

import br.com.albertoferes.springcontacts.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
