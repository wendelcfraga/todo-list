public class Tarefa {

    private String nome;
    private String descricao;
    private String status;
    private String dataVencimento;

    private String categoria;
    private int prioridade;

    public Tarefa(String nome, String descricao, String status, String dataVencimento, String categoria, int prioridade)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.dataVencimento = dataVencimento;
        this.categoria = categoria;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status='" + status + '\'' +
                ", dataVencimento='" + dataVencimento + '\'' +
                ", categoria='" + categoria + '\'' +
                ", prioridade=" + prioridade +
                '}';
    }
}
