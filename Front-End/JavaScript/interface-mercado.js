async function fetchDataFromAPI(url) {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error('Erro ao buscar dados da API');
    }
    return await response.json();
}

function createProductTable(products) {
    const table = document.createElement('table');
    table.className = 'table-container'; // Adiciona a classe "table-container"
    table.style.margin = 'auto'; // Centraliza a tabela


    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');

    const idHeader = document.createElement('th');
    idHeader.textContent = 'ID';
    headerRow.appendChild(idHeader);

    const nameHeader = document.createElement('th');
    nameHeader.textContent = 'Nome do Produto';
    headerRow.appendChild(nameHeader);

    const imageHeader = document.createElement('th');
    imageHeader.textContent = 'Imagem';
    headerRow.appendChild(imageHeader);

    const priceHeader = document.createElement('th');
    priceHeader.textContent = 'Preço';
    headerRow.appendChild(priceHeader);

    const discountHeader = document.createElement('th');
    discountHeader.textContent = 'Desconto';
    headerRow.appendChild(discountHeader);

    thead.appendChild(headerRow);
    table.appendChild(thead);

    const tbody = document.createElement('tbody');

    products.forEach(product => {
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = product.id_produto !== undefined ? product.id_produto.toString() : '';
        row.appendChild(idCell);
        
        const nameCell = document.createElement('td');
        nameCell.textContent = product.nome !== undefined ? product.nome.toString() : '';
        row.appendChild(nameCell);

        const imageCell = document.createElement('td');
        const img = document.createElement('img');
        img.src = product.imagem;
        img.alt = product.nome;
        img.style.width = '50px';
        img.style.height = '50px';
        imageCell.appendChild(img);
        row.appendChild(imageCell);

        const priceCell = document.createElement('td');
        priceCell.textContent = product.preco !== undefined ? product.preco.toString() : '';
        row.appendChild(priceCell);

        const discountCell = document.createElement('td');
        discountCell.textContent = product.desconto ? 'Sim' : 'Não';
        row.appendChild(discountCell);

        tbody.appendChild(row);
    });

    table.appendChild(tbody);
    return table;
}

function populateTable(markets) {
    const tableBody = document.getElementById('mercado-table').querySelector('tbody');

    // Limpa as linhas existentes dentro do corpo da tabela
    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }

    markets.forEach(market => {
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = market.id.toString();
        row.appendChild(idCell);

        const nameCell = document.createElement('td');
        nameCell.textContent = market.nome;
        row.appendChild(nameCell);

        const addressCell = document.createElement('td');
        addressCell.textContent = market.endereco;
        row.appendChild(addressCell);

        const productsCell = document.createElement('td');
        if (market.produtos && market.produtos.length > 0) {
            productsCell.colSpan = 1;
            const productContainer = document.createElement('div');
            productContainer.className = 'table-container';
            productContainer.appendChild(createProductTable(market.produtos));
            productsCell.appendChild(productContainer);
        } else {
            productsCell.colSpan = 1;
            productsCell.textContent = 'Nenhum produto listado';
        }
        row.appendChild(productsCell);

        const actionCell = document.createElement('td');
        const editButton = document.createElement('img');
        editButton.src = '../imgs/pencil.svg';
        editButton.style.cursor = 'pointer';
        editButton.addEventListener('click', () => editMarket(market.id));
        actionCell.appendChild(editButton);

        const deleteButton = document.createElement('img');
        deleteButton.src = '../imgs/x-lg.svg';
        deleteButton.style.cursor = 'pointer';
        deleteButton.addEventListener('click', () => deleteMarket(market.id));
        actionCell.appendChild(deleteButton);

        row.appendChild(actionCell);

        tableBody.appendChild(row);
    });
}

async function searchMarketById(marketId) {
    try {
        const apiUrl = `http://localhost:8082/marketplace/${marketId}`;
        const response = await fetch(apiUrl);
        
        if (!response.ok) {
            const markets = await fetchDataFromAPI('http://localhost:8082/marketplace');
            populateTable(markets);
            return;
        }
        
        const market = await response.json();
        populateTable([market]);
    } catch (error) {
        console.error('Erro ao buscar mercado por ID:', error);
        alert('Erro ao buscar mercado por ID. Por favor, tente novamente.');
    }
}

async function searchMarketByAddress(endereco) {
    try {
        const apiUrl = `http://localhost:8082/marketplace/endereco?endereco=${encodeURIComponent(endereco)}`;
        const markets = await fetchDataFromAPI(apiUrl);
        populateTable(markets);
    } catch (error) {
        console.error('Erro ao buscar mercados por endereço:', error);
        alert('Erro ao buscar mercados por endereço. Por favor, tente novamente.');
    }
}

async function searchMarketByName(name) {
    try {
        const apiUrl = `http://localhost:8082/marketplace/nome?name=${encodeURIComponent(name)}`;
        const markets = await fetchDataFromAPI(apiUrl);
        populateTable(markets);
    } catch (error) {
        console.error('Erro ao buscar mercados por nome:', error);
        alert('Erro ao buscar mercados por nome. Por favor, tente novamente.');
    }
}

document.addEventListener('DOMContentLoaded', async () => {
    try {
        const apiUrl = 'http://localhost:8082/marketplace';
        const markets = await fetchDataFromAPI(apiUrl);

        const selectElement = document.getElementById('market-details');
        markets.forEach(market => {
            const option = document.createElement('option');
            option.value = market.id;
            option.textContent = market.id;
            selectElement.appendChild(option);
        });

        document.getElementById('search-button').addEventListener('click', () => {
            const selectedId = selectElement.value;
            searchMarketById(selectedId);
        });

        document.getElementById('search-market-button').addEventListener('click', () => {
            const endereco = document.getElementById('market-address').value;
            searchMarketByAddress(endereco);
        });

        document.getElementById('search-name-button').addEventListener('click', () => {
            const nome = document.getElementById('market-name').value;
            searchMarketByName(nome);
        });

        populateTable(markets);
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao carregar dados dos mercados. Por favor, atualize a página.');
    }
});

async function deleteMarket(marketId) {
    const apiUrl = `http://localhost:8082/marketplace/${marketId}`;

    try {
        const response = await fetch(apiUrl, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Erro ao excluir mercado');
        }

        alert('Mercado excluído com sucesso!');
        window.location.href = '../Html/interface-mercado.html';
    } catch (error) {
        console.error('Erro ao excluir mercado:', error);
        alert('Erro ao excluir mercado. Por favor, tente novamente.');
    }
}

function editMarket(marketId) {
    window.location.href = `../Html/editar-mercado.html?id=${marketId}`;
}
