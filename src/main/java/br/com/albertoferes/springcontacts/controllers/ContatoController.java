package br.com.albertoferes.springcontacts.controllers;

import br.com.albertoferes.springcontacts.domain.Contato;
import br.com.albertoferes.springcontacts.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping("/contatos")
    public String getHomePage(Model model) {
        List<Contato> contatos = service.buscarTodos();
        model.addAttribute("contatos", contatos);
        return "contatos";
    }

    @GetMapping("/delete/{id}")
    public String apagarUsuario(@PathVariable("id") int id, Model model){
        service.remover(id);
        model.addAttribute("contatos", service.buscarTodos());
        return "contatos";
    }

    @GetMapping("/cadastrar")
    public String exibirFormularioCadastro(Contato contato) {
        return "adicionar";
    }

    @PostMapping("/add")
    public String adicionarContato(@Validated Contato contato, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "adicionar";
        }
        service.salvar(contato);
        model.addAttribute("contatos", service.buscarTodos());
        return "contatos";
    }

    @GetMapping("/edit/{id}")
    public String abrirFormularioEdicao(@PathVariable("id") Integer id, Model model) {
        var contato = service.buscarPorId(id);
        model.addAttribute("contato", contato);

        return "atualizar";
    }

    @PostMapping("/update/{id}")
    public String atualizarContato(@PathVariable("id") Integer id, Contato contato, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            contato.setId(id);
            return "atualizar";
        }

        service.salvar(contato);
        model.addAttribute("contatos", service.buscarTodos());
        return "contatos";
    }
}
