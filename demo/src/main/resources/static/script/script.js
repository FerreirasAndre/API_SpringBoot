const apiURL = "http://localhost:8080/usuario";
const formUsuario = document.getElementById("formUsuario");
const listaUsuarios = document.getElementById("listaUsuarios"); // Variável com U maiúsculo
const IdInput = document.getElementById("Id");
const nomeInput = document.getElementById("nome");
const emailInput = document.getElementById("email");
const senhaInput = document.getElementById("senha");

// IDs corrigidos para bater com o HTML
const cancelBtn = document.getElementById("cancelBtn"); 
const listarBtn = document.getElementById("listarBtn");

async function carregarUsuarios() {
    try {
        const resposta = await fetch(apiURL);
        if (!resposta.ok) throw new Error("Erro ao buscar usuários");
        
        const usuarios = await resposta.json();
        
        // Corrigido para bater com a variável declarada no topo (listaUsuarios)
        listaUsuarios.innerHTML = ""; 
        
        usuarios.forEach(u => {
            const li = document.createElement("li");
            // Corrigido a sintaxe do onclick: o parêntese deve estar dentro das aspas
            li.innerHTML = `
                <span>${u.id} - ${u.nome} - ${u.email}</span>
                <button onclick="prepararEdicao(${u.id}, '${u.nome}', '${u.email}')">Editar</button>
                <button onclick="excluirUsuario(${u.id})">Excluir</button>
            `;
            listaUsuarios.appendChild(li);
        });
    } catch (erro) {
        console.log("Erro: ", erro);
    }
}

// Evento para o botão Listar
listarBtn.addEventListener("click", carregarUsuarios);

formUsuario.addEventListener("submit", async (e) => {
    e.preventDefault();
    const usuario = {
        nome: nomeInput.value,
        email: emailInput.value,
        senha: senhaInput.value,
    };
    
    const isEditing = IdInput.value !== "";
    const method = isEditing ? "PUT" : "POST";
    const url = isEditing ? `${apiURL}/${IdInput.value}` : apiURL;
    
    try {
        await fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(usuario)
        });
        carregarUsuarios();
        formUsuario.reset();
        IdInput.value = "";
    } catch (erro) {
        console.error("Erro ao salvar usuário", erro);
    }
});

async function excluirUsuario(id) {
    if (confirm("Tem certeza que deseja excluir este usuário?")) {
        try {
            await fetch(`${apiURL}/${id}`, {
			method: "DELETE"
		});
            carregarUsuarios(); 
        } catch (erro) {
            console.error("Erro ao excluir usuário: ", erro);
        }
    }
}

assync function editarUsuario(id){
	try {
		const resposta =await fetch(`${apiURL}/${id}`);
		const usuario = await resposta.json();
		userIdInput.value = usuario.id;
		nomeInput.value =  usuario.nome;
		emailInput.value = usuario.email;
		senha.Input.value = usuario.senha;
		submit.Btn.textContent = "Atualizar";
		cancel.Btn.style.display="inner-block";
	} catch (erro) {
		console.error("Erro ao buscar usuário para edição: ", erro);
	}
}