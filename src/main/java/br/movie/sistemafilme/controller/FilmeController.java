package br.movie.sistemafilme.controller;

import br.movie.sistemafilme.model.Filme;
import br.movie.sistemafilme.service.FileStorageService;
import br.movie.sistemafilme.service.FilmeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping(value = "/index")
    public String listarFilmes(Model model, HttpServletResponse response) {
        model.addAttribute("filmes", filmeService.listarFilmes());

        // formatando a data e pegando a data e horario para adicionar no cookie
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // retirando os espaços vazios do cookie e substituindo por um caractere
        String data = LocalDateTime.now().format(formatoData).replace(" ", "-");
        Cookie c = new Cookie("visita", data);
        c.setMaxAge(24 * 60 * 60);
        c.setPath("/");
        response.addCookie(c);

        return "index";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model) {
        model.addAttribute("filmes",filmeService.listarFilmes());
        return "paginaAdmin";
    }



    //ERRO 400, Required part 'file' is not present.

    @GetMapping (value = "/editar/{id}")
    public ModelAndView editarFilme(@PathVariable Long id, Model model) {
        Optional<Filme> filme = filmeService.buscarIdFilme(id);

        if(filme.isPresent()) {
            ModelAndView modeloVisao = new ModelAndView("paginaEditarFilme");
            modeloVisao.addObject("filme", filme.get());
            return modeloVisao;
        }
        else {
            ModelAndView modeloVisao = new ModelAndView("redirect:/admin");
            modeloVisao.addObject("message", "Filme atualizado com sucesso!");
            return modeloVisao;
        }
    }

    @GetMapping(value = "/deletar/{id}")
    public String deletarFilme(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        Optional<Filme> filme = filmeService.buscarIdFilme(id);
        if (filme.isPresent()) {
            Date dataDelete = new Date();
            java.sql.Date sqlDate = new java.sql.Date(dataDelete.getTime());
            filme.get().setIsDeleted(sqlDate);
            filmeService.atualizarFilme(filme.get());

            redirectAttributes.addFlashAttribute("message", "Remoção ocorreu com sucesso!");
        }
        return "redirect:/index";
    }

    @GetMapping(value = "/cadastro")
    public String cadastrarFilme(Model model) {
        Filme filme = new Filme();
        model.addAttribute("filme", filme);
        return "paginaCadastroFilme";
    }

    @PostMapping(value = "/salvar")
    public ModelAndView salvarFilme(@ModelAttribute @Valid Filme filme, Errors erro, @RequestParam("file") MultipartFile file) {


        if(erro.hasErrors()) {
            return new ModelAndView("paginaCadastroFilme");
        }

        filme.setImageUri(file.getOriginalFilename());
        fileStorageService.save(file);

        filmeService.criarFilme(filme);
        //filmeService.salvarFilme(filme);
        ModelAndView modeloVisao = new ModelAndView("redirect:/admin");
        //modeloVisao.addObject("message", "Filme atualizado com sucesso!");
        modeloVisao.addObject("filmes", filmeService.listarFilmes());
        return modeloVisao;
    }

    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {

        return filmeService.salvarFilme(filme);

    }


}
