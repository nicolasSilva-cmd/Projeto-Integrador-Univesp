async function fetchDataFromAPI(url) {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error('Erro ao buscar dados da API');
    }
    return await response.json();
}

function populateProductTable(products) {
    const tableBody = document.getElementById('produto-table').querySelector('tbody');

    // Limpa as linhas existentes dentro do corpo da tabela
    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }

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

        const actionCell = document.createElement('td');
        actionCell.style.textAlign = 'center';
        const editButton = document.createElement('img');
        editButton.src = '../imgs/pencil.svg';
        editButton.style.cursor = 'pointer';
        editButton.title = 'Editar';
        editButton.addEventListener('click', () => editProduct(product.id_produto));
        actionCell.appendChild(editButton);

        const deleteButton = document.createElement('img');
        deleteButton.src = '../imgs/x-lg.svg';
        deleteButton.style.cursor = 'pointer';
        deleteButton.title = 'Excluir';
        deleteButton.addEventListener('click', () => deleteProduct(product.id_produto));
        actionCell.appendChild(deleteButton);

        row.appendChild(actionCell);

        tableBody.appendChild(row);
    });
}

async function getAllProducts() {
    try {
        const apiUrl = 'http://localhost:8082/produtos';
        const products = await fetchDataFromAPI(apiUrl);
        populateProductTable(products);
    } catch (error) {
        console.error('Erro ao buscar produtos:', error);
        alert('Erro ao buscar produtos. Por favor, tente novamente.');
    }
}

async function populateProductIdSelect() {
    try {
        const apiUrl = 'http://localhost:8082/produtos';
        const products = await fetchDataFromAPI(apiUrl);

        const selectElement = document.getElementById('product-id');
        products.forEach(product => {
            const option = document.createElement('option');
            option.value = product.id_produto;
            option.textContent = product.id_produto;
            selectElement.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao buscar IDs de produtos:', error);
    }
}

async function searchProductByName() {
    try {
        const productName = document.getElementById('product-name').value;
        const apiUrl = `http://localhost:8082/produtos/nome?name=${encodeURIComponent(productName)}`;
        const products = await fetchDataFromAPI(apiUrl);
        populateProductTable(products);
    } catch (error) {
        console.error('Erro ao buscar produtos por nome:', error);
        alert('Erro ao buscar produtos por nome. Por favor, tente novamente.');
    }
}

async function searchProductById() {
    try {
        const selectedId = document.getElementById('product-id').value;
        const apiUrl = `http://localhost:8082/produtos/${selectedId}`;
        const product = await fetchDataFromAPI(apiUrl);
        populateProductTable([product]);
    } catch (error) {
        getAllProducts(); // Retorna a lista completa de produtos se o ID for inválido
    }
}

async function getAllProductsDiscount() {
    try {
        const apiUrl = 'http://localhost:8082/produtos/desconto';
        const products = await fetchDataFromAPI(apiUrl);
        populateProductTable(products);
    } catch (error) {
        console.error('Erro ao buscar produtos com desconto:', error);
        alert('Erro ao buscar produtos com desconto. Por favor, tente novamente.');
    }
}

document.addEventListener('DOMContentLoaded', async () => {
    try {
        await getAllProducts();
        await populateProductIdSelect();
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao carregar dados dos produtos. Por favor, atualize a página.');
    }

    document.getElementById('search-product-button').addEventListener('click', searchProductByName);
    document.getElementById('search-product-id-button').addEventListener('click', searchProductById);
    document.getElementById('discount-only-checkbox').addEventListener('change', () => {
        if (document.getElementById('discount-only-checkbox').checked) {
            getAllProductsDiscount();
        } else {
            getAllProducts();
        }
    });
});

async function deleteProduct(productId) {
    const apiUrl = `http://localhost:8082/produtos/${productId}`;

    try {
        const response = await fetch(apiUrl, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Erro ao excluir produto');
        }

        alert('Produto excluído com sucesso!');
        getAllProducts(); // Atualiza a tabela após excluir o produto
    } catch (error) {
        console.error('Erro ao excluir produto:', error);
        alert('Erro ao excluir produto. Por favor, tente novamente.');
    }
}

function editProduct(productId) {
    window.location.href = `../Html/editar-produto.html?id=${productId}`;
}
