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
    document.getElementById('market-form').addEventListener('submit', async (event) => {
        event.preventDefault();
        const marketName = document.getElementById('market-name').value;
        const marketAddress = document.getElementById('market-address').value;

        const newMarket = {
            nome: marketName,
            endereco: marketAddress
        };

        const apiUrl = 'http://localhost:8082/marketplace';
        try {
            await sendDataToAPI(apiUrl, newMarket);
            alert('Mercado incluído com sucesso!');
            location.reload(); // Atualiza a página
        } catch (error) {
            console.error('Erro ao enviar os dados do mercado:', error);
            alert('Erro ao incluir o mercado. Por favor, tente novamente.');
        }
    });
});
