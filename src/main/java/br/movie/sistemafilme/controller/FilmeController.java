package br.movie.sistemafilme.controller;

import br.movie.sistemafilme.model.Filme;
import br.movie.sistemafilme.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> listarFilmes() {
        return filmeService.listarFilmes();
    }

    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {
        return filmeService.salvarFilme(filme);
    }

    // Outros endpoints
}
