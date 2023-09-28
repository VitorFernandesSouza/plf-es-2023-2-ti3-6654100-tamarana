function receberDados(id, numero, raca, sexo, peso, dataNasc) {
    console.log("fui chamado");
    var modelEditar = new bootstrap.Modal(document.getElementById('editaranimal'))
    document.getElementById("modelId").value = id;
    document.getElementById("modelNumero").value = numero;
    document.getElementById("modelRaca").value = raca;
    document.getElementById("modelSexo").value = sexo;
    document.getElementById("modelPeso").value = peso;
    document.getElementById("modelDataNasc").value = dataNasc;
    
    modelEditar.show(modelEditar);
}
