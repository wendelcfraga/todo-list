import java.io.*;
import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Tarefa> tarefas;
    private static final String DB = "tarefas.txt";

    public ToDoList()
    {
        this.tarefas = new ArrayList<>();
        criarArquivoSeNaoExiste();
    }
    private void criarArquivoSeNaoExiste()
    {
        File arquivo = new File(DB);
        if (!arquivo.exists())
        {
            try {
                arquivo.createNewFile();
                //System.out.println("arquivo criado com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        ToDoList todolist = new ToDoList();

    }
}