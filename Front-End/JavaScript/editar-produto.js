async function fetchDataFromAPI(url) {
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Erro ao buscar dados da API');
        }
        return await response.json();
    } catch (error) {
        console.error('Erro ao buscar dados da API:', error);
        throw error;
    }
}

async function updateDataToAPI(url, data) {
    try {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error('Erro ao atualizar dados na API');
        }

        alert('Produto atualizado com sucesso!');
        window.location.href = '../Html/listagem-produtos.html'; // Redireciona para a listagem de produtos após a edição

    } catch (error) {
        console.error('Erro ao atualizar dados na API:', error);
        alert('Erro ao atualizar o produto. Por favor, tente novamente.');
    }
}

document.addEventListener('DOMContentLoaded', async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');

    // Função para buscar dados do produto pelo ID e preencher o formulário
    async function fetchProductData() {
        const apiUrl = `http://localhost:8082/produtos/${productId}`;
        try {
            const response = await fetch(apiUrl);
            const productData = await response.json();
            document.getElementById('product-id').value = productData.id_produto;
            document.getElementById('product-market-id').value = productData.id_mercado;
            document.getElementById('product-name').value = productData.nome;
            document.getElementById('product-image').value = productData.imagem;
            document.getElementById('product-price').value = productData.preco;
            document.getElementById('product-discount').checked = productData.desconto;
        } catch (error) {
            console.error('Erro ao buscar dados do produto:', error);
        }
    }

    // Chama a função para buscar dados do produto ao carregar a página
    await fetchProductData();

    document.getElementById('product-form').addEventListener('submit', async (event) => {
        event.preventDefault();
        const productMarketId = document.getElementById('product-market-id').value;
        const productName = document.getElementById('product-name').value;
        const productImage = document.getElementById('product-image').value;
        const productPrice = document.getElementById('product-price').value;
        const productDiscount = document.getElementById('product-discount').checked;

        const updatedProduct = {
            id_mercado: parseInt(productMarketId),
            nome: productName,
            imagem: productImage,
            preco: parseFloat(productPrice),
            desconto: productDiscount
        };

        const apiUrl = `http://localhost:8082/produtos/${productId}`;
        try {
            const response = await fetch(apiUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedProduct)
            });

            if (!response.ok) {
                throw new Error('Erro ao atualizar dados do produto');
            }

            alert('Produto atualizado com sucesso!');
            window.location.href = '../Html/interface-produto.html'; // Redireciona para a listagem de produtos
        } catch (error) {
            console.error('Erro ao atualizar os dados do produto:', error);
            alert('Erro ao atualizar o produto. Por favor, tente novamente.');
        }
    });
});