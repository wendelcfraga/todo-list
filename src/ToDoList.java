import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private static final String DB = "tarefas.txt";
    private final ArrayList<Tarefa> tarefas;

    public ToDoList() {
        this.tarefas = new ArrayList<>();
        criarArquivoSeNaoExiste();
    }

    public static void main(String[] args) {
        ToDoList todolist = new ToDoList();
        todolist.carregarArquivo();
        todolist.ordenarPrioridade();

        while (true) {
            System.out.println("*****************************");
            System.out.println("Menu TODO-List");
            System.out.println("1. Listar minhas tarefas");
            System.out.println("2. Adicionar Tarefa");
            System.out.println("3. Deletar Tarefa");
            System.out.println("*****************************");
            System.out.print("Digite um número para escolher ou 's' para sair: ");

            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                int op = scan.nextInt();
                switch (op) {
                    case 1:
                        todolist.listarTarefas();
                        break;
                    case 2:
                        todolist.adicionarTarefa();
                        break;
                    case 3:
                        todolist.removerTarefa();
                        break;
                    default:
                        System.out.println("Escolha inválida, tente novamente!");
                }
            } else if (scan.next().equalsIgnoreCase("s")) {
                break;
            } else {
                System.out.println("Comando não reconhecido, tente novamente!");
            }

        }
    }

    private void criarArquivoSeNaoExiste() {
        File arquivo = new File(DB);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
                //System.out.println("arquivo criado com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void adicionarTarefa() {
        Scanner pergunta = new Scanner(System.in);
        System.out.print("Coloque o nome da tarefa: ");
        String nome = pergunta.nextLine();
        System.out.print("Coloque a descrição da terefa: ");
        String descricao = pergunta.nextLine();
        boolean valido = false;
        LocalDate data = null;
        while (!valido) {
            System.out.print("Coloque a data de vencimento da tarefa (DD-MM-YYYY): ");
            String dataDigitada = pergunta.nextLine();
            try {
                data = LocalDate.parse(dataDigitada, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                if (data.isBefore(LocalDate.now())) {
                    throw new IllegalArgumentException("Entrada inválida! Essa data já passou.");
                }
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Digite de acordo com esse formato dd-MM-yyyy");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Coloque o status da tarefa (TODO, DOING, DONE): ");
        String status = pergunta.nextLine();
        System.out.print("Coloque a categoria: ");
        String categoria = pergunta.nextLine();
        System.out.print("Coloque a prioridade (1-5): ");
        int prioridade = pergunta.nextInt();

        Tarefa tarefa = new Tarefa(nome, descricao, status, data, categoria, prioridade);
        this.tarefas.add(tarefa);
        ordenarPrioridade();
        salvarArquivo();
    }

    public void removerTarefa() {
        Scanner pergunta = new Scanner(System.in);
        System.out.print("Digite o número da tarefa que deseja remover: ");
        int index = pergunta.nextInt() - 1;
        this.tarefas.remove(index);
    }

    public void listarTarefas() {
        if (this.tarefas.size() == 0) {
            System.out.println("Lista vazia!");
        } else {
            System.out.println("*****************************");
            System.out.println("Como deseja listar as tarefas?");
            System.out.println("1. Por categoria");
            System.out.println("2. Por prioridade");
            System.out.println("3. Por status");
            System.out.println("*****************************");
            System.out.print("Digite um número para escolher: ");

            int op = new Scanner(System.in).nextInt();

            switch (op) {
                case 1:
                    System.out.print("Digite uma categoria: ");
                    String escolhaCategoria = new Scanner(System.in).nextLine();

                    for (Tarefa tarefa : this.tarefas) {
                        if (tarefa.getCategoria().equalsIgnoreCase(escolhaCategoria)) {
                            System.out.println(tarefa);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < this.tarefas.size(); i++) {
                        System.out.println((i + 1) + ". " + this.tarefas.get(i).toString());
                    }
                    break;
                case 3:
                    System.out.print("Digite o status (TODO, DOING, DONE): ");
                    String escolhaStatus = new Scanner(System.in).nextLine();
                    for (Tarefa tarefa : this.tarefas) {
                        if (tarefa.getStatus().equalsIgnoreCase(escolhaStatus)) {
                            System.out.println(tarefa);
                        }
                    }
                    break;
                default:
                    System.out.println("Escolha inválida!");
            }


        }
    }

    public void salvarArquivo() {
        try {
            PrintWriter escrever = new PrintWriter(new File(DB));
            for (Tarefa tarefa : this.tarefas) {
                escrever.println(tarefa.getNome() + ";" + tarefa.getDescricao() + ";" + tarefa.getDataVencimento() + ";" + tarefa.getStatus() + ";" + tarefa.getCategoria() + ";" + tarefa.getPrioridade());
            }
            escrever.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void carregarArquivo() {
        try {
            Scanner scan = new Scanner(new File(DB));
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();
                String[] dadosTarefa = linha.split(";");
                String nome = dadosTarefa[0];
                String descricao = dadosTarefa[1];
                DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate data = LocalDate.parse(dadosTarefa[2], formatoData);
                String status = dadosTarefa[3];
                String categoria = dadosTarefa[4];
                int prioridade = Integer.parseInt(dadosTarefa[5]);

                Tarefa tarefa = new Tarefa(nome, descricao, status, data, categoria, prioridade);
                this.tarefas.add(tarefa);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void ordenarPrioridade() {
        for (int i = 1; i < this.tarefas.size(); i++) {
            Tarefa tarefaAtual = this.tarefas.get(i);
            int j = i - 1;
            while (j >= 0 && this.tarefas.get(j).getPrioridade() < tarefaAtual.getPrioridade()) {
                this.tarefas.set(j + 1, this.tarefas.get(j));
                j--;
            }
            this.tarefas.set(j + 1, tarefaAtual);
        }
    }
}