package dicasdev.model.domain;

public class Dica {
    public Integer id;
    public String titulo;
    public String descricao;
    @Override
    public String toString() {
        return "Dica [id=" + id + ", titulo='" + titulo + "', descricao='" + descricao + "']";
    }
}
