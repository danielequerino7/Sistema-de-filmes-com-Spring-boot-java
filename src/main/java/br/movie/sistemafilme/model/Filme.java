package br.movie.sistemafilme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@Entity(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date isDeleted;
    private String imageUri;

    @NotNull(message = "O título do filme não pode ser nulo.")
    @NotBlank (message = "O título não deve incluir espaços em branco.")
    @Size(min = 5, max = 60, message = "O título precisa conter mais do que 5 caracteres e menos do que 40 caracteres.")
    private String titulo;

    @NotNull(message = "A classificação do filme não pode ser nula.")
    @Size(min = 1, max = 2, message = "A classificação precisa conter mais do que 1 caractere e menos do que 2 caracteres.")
    private String classificacao;

    @NotNull(message = "O idioma do filme não pode ser nulo.")
    @NotBlank (message = "O idioma não deve incluir espaços em branco.")
    @Size(min = 2, max = 25, message = "O idioma precisa conter mais do que 2 caracteres e menos do que 25 caracteres.")
    private String idiomaOriginal;

    @NotNull(message = "O ano de lançamento do filme não pode ser nulo.")
    @DecimalMin(value = "1950")
    @DecimalMax(value = "2025", message = "O ano está inválido.")
    private Integer anoLancamento;

    @NotNull(message = "A duração do filme não pode ser nula.")
    @Min( value = 60)
    @Max( value = 200)
    private Float duracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Date isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Float getDuracao() {
        return duracao;
    }

    public void setDuracao(Float duracao) {
        this.duracao = duracao;
    }
// Getters e Setters
}
