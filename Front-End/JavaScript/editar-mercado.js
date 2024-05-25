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

        alert('Mercado atualizado com sucesso!');
        window.location.href = "interface-mercado.html"; // Redireciona para a listagem de mercados após a edição

    } catch (error) {
        console.error('Erro ao atualizar dados na API:', error);
        alert('Erro ao atualizar o mercado. Por favor, tente novamente.');
    }
}

document.addEventListener('DOMContentLoaded', async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const marketId = urlParams.get('id'); // Obtém o ID do mercado da URL

    if (!marketId) {
        console.error('ID do mercado não encontrado na URL');
        alert('ID do mercado não encontrado na URL.');
        return;
    }

    const apiUrl = `http://localhost:8082/marketplace/${marketId}`;
    const market = await fetchDataFromAPI(apiUrl);

    document.getElementById('market-id').value = market.id.toString();
    document.getElementById('market-name').value = market.nome;
    document.getElementById('market-address').value = market.endereco;

    document.getElementById('market-form').addEventListener('submit', async (event) => {
        event.preventDefault();
        
        const updatedMarket = {
            id: market.id,
            nome: document.getElementById('market-name').value,
            endereco: document.getElementById('market-address').value
        };

        await updateDataToAPI(apiUrl, updatedMarket);
    });
});
