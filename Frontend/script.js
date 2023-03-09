//Funções de teste
//Função para filtrar lista de tarefas por status
function filtrarTarefasPorStatus(status) {
    var linhasTabela = document.querySelectorAll("tbody tr");
    for (var i = 0; i < linhasTabela.length; i++) {
        var tarefa = linhasTabela[i];
        var tarefaStatus = tarefa.querySelector("td:nth-child(4)").textContent;
        if (status === "" || tarefaStatus === status) {
            tarefa.style.display = "";
        } else {
            tarefa.style.display = "none";
        }
    }
}

//Função para adicionar tarefa
document.getElementById("adicionarTarefa").addEventListener("click", function(event){
    event.preventDefault();
    var nomeTarefa = document.getElementById("nomeTarefa").value;
    var dataTarefa = document.getElementById("dataTarefa").value;
    var descricaoTarefa = document.getElementById("descricaoTarefa").value;
    var statusTarefa = document.getElementById("statusTarefa").value;

    //Adicionar nova linha na tabela
    var novaLinha = document.createElement("tr");
    novaLinha.innerHTML = "<td>" + nomeTarefa + "</td>" +
                          "<td>" + dataTarefa + "</td>" +
                          "<td>" + descricaoTarefa + "</td>" +
                          "<td>" + statusTarefa + "</td>" +
                          "<td><button type='button' class='editarTarefa'>Editar</button></td>" +
                          "<td><button type='button' class='excluirTarefa'>Excluir</button></td>";
    document.querySelector("tbody").appendChild(novaLinha);

    //Limpar campos do formulário
    document.getElementById("nomeTarefa").value = "";
    document.getElementById("dataTarefa").value = "";
    document.getElementById("descricaoTarefa").value = "";
    document.getElementById("statusTarefa").value = "";

    //Filtrar lista de tarefas por status
    filtrarTarefasPorStatus(document.getElementById("filtroStatus").value);
});

//Função para editar tarefa
var editarTarefa = document.getElementsByClassName("editarTarefa");
for (var i = 0; i < editarTarefa.length; i++) {
    editarTarefa[i].addEventListener("click", function(){
        var tarefa = this.parentNode.parentNode;
        var nomeTarefa = tarefa.getElementsByTagName("td")[0];
        var dataTarefa = tarefa.getElementsByTagName("td")[1];
        var descricaoTarefa = tarefa.getElementsByTagName("td")[2];
        var statusTarefa = tarefa.getElementsByTagName("td")[3];

        //Preencher campos do formulário com valores atuais da tarefa
        document.getElementById("nomeTarefa").value = nomeTarefa.textContent;
        document.getElementById("dataTarefa").value = dataTarefa.textContent;
        document.getElementById("descricaoTarefa").value = descricaoTarefa.textContent;
        document.getElementById("statusTarefa").value = statusTarefa.textContent;

        //Remover linha da tabela
        tarefa.remove();
    });
}

//Função para excluir tarefa
var excluirTarefa = document.getElementsByClassName("excluirTarefa");
for (var i = 0; i < excluirTarefa.length; i++) {
    excluirTarefa[i].addEventListener("click", function(){
        var tarefa = this.parentNode.parentNode;
        tarefa.remove();

        //Filtrar lista de tarefas por status
        filtrarTarefasPorStatus(document.getElementById("filtroStatus").value);
    });
}

//Função para filtrar lista de tarefas por status
function filtrarTarefasPorStatus(status) {
    var linhasTabela = document.querySelectorAll("tbody tr");
    for (var i = 0; i < linhasTabela.length; i++) {
        var tarefa = linhasTabela[i];
        var tarefaStatus = tarefa.querySelector("td:nth-child(4)").textContent;
        if (status === "" || tarefaStatus === status) {
            tarefa.style.display = "";
        } else {
            tarefa.style.display = "none";
        }
    }
}
