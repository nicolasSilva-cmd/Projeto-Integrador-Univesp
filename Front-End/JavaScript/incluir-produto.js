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

async function sendDataToAPI(url, data, method = 'POST') {
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error('Erro ao enviar dados para a API');
        }

        // Verifica se a resposta tem conteúdo e tenta parsear como JSON
        const responseText = await response.text();
        return responseText ? JSON.parse(responseText) : {}; // Retorna objeto vazio se resposta for vazia

    } catch (error) {
        console.error('Erro ao enviar dados para a API:', error);
        throw error;
    }
}

document.addEventListener('DOMContentLoaded', async () => {
    document.getElementById('product-form').addEventListener('submit', async (event) => {
        event.preventDefault();
        const productMarketId = document.getElementById('product-market-id').value;
        const productName = document.getElementById('product-name').value;
        const productImage = document.getElementById('product-image').value;
        const productPrice = document.getElementById('product-price').value;
        const productDiscount = document.getElementById('product-discount').checked;

        const newProduct = {
            id_mercado: parseInt(productMarketId),
            nome: productName,
            imagem: productImage,
            preco: parseFloat(productPrice),
            desconto: productDiscount
        };

        const apiUrl = 'http://localhost:8082/produtos';
        try {
            await sendDataToAPI(apiUrl, newProduct);
            alert('Produto incluído com sucesso!');
            location.reload(); // Atualiza a página
        } catch (error) {
            console.error('Erro ao enviar os dados do produto:', error);
            alert('Erro ao incluir o produto. Por favor, tente novamente.');
        }
    });
});
