window.onload = () => {
    const dataCardProduto = document.querySelectorAll(".sel")
    var searchInput = document.querySelector("[data-search]")
    
    let produtos = []
 
    for (var i=0; i<dataCardProduto.length; i++) {
        const card = dataCardProduto[i]
        var texto = dataCardProduto[i].innerText;
        var data = {nome: texto.substring(0, texto.indexOf('\n')), element: card};
        produtos.push(data)
    }

    console.log(produtos)

    searchInput.addEventListener("input", (e) => {
        const value = e.target.value.toLowerCase()
        produtos.forEach(produto => {
            const isVisible = produto.nome.toLowerCase().includes(value)
            produto.element.classList.toggle("esconder", !isVisible)
        })
        console.log(value)
    }) 

}





    
