public class Tarefa {

    private String nome;
    private String descricao;
    private String status;
    private String dataVencimento;

    public Tarefa(String nome, String descricao, String status, String dataVencimento)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.dataVencimento = dataVencimento;
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

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status='" + status + '\'' +
                ", dataVencimento='" + dataVencimento + '\'' +
                '}';
    }
}
