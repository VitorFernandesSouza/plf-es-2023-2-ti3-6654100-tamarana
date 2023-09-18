function receberDados(id, nome, descricao, preco, quantidade) {
    console.log("fui chamado");
    var modelEditar = new bootstrap.Modal(document.getElementById('editarproduto'))
    document.getElementById("modelId").value = id;
    document.getElementById("modelNome").value = nome;
    document.getElementById("modelDescricao").value = descricao;
    document.getElementById("modelPreco").value = preco;
    document.getElementById("modelQuantidade").value = quantidade;
    
    modelEditar.show(modelEditar);
}
