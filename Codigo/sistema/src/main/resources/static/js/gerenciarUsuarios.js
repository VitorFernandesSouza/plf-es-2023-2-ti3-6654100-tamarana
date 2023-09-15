function receberDados(email, nome, sobrenome, role, id) {
    var modelEditar = new bootstrap.Modal(document.getElementById('editarusuario'))
    document.getElementById("modelEmail").value = email;
    document.getElementById("modelNome").value = nome;
    document.getElementById("modelSobrenome").value = sobrenome;
    document.getElementById("modelRole").value = role;
    document.getElementById("modelId").value = id;

    
  

    modelEditar.show(modelEditar);
}
