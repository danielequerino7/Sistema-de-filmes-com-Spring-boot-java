package br.movie.sistemafilme.service;

import br.movie.sistemafilme.model.Filme;
import br.movie.sistemafilme.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;


    public Filme criarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public List<Filme> listarFilmes() {
        List<Filme> todosOsFilmes = filmeRepository.findAll();

        // retornando apenas filmes que estão com o valor null no atributo isDeleted, caso contrario eles estao "deletados"
        return todosOsFilmes.stream()
                .filter(filme -> filme.getIsDeleted() == null)
                .collect(Collectors.toList());
    }

    public Optional<Filme> buscarIdFilme(Long id){
        return filmeRepository.findById(id);
    }

    public Filme salvarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public Filme atualizarFilme(Filme filme){
        return filmeRepository.saveAndFlush(filme);
    }

    public void deletarFilme(Long id){
        filmeRepository.deleteById(id);
    }

}
