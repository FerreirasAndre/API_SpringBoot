const apiURL = "http://localhost:8080/usuario";
const formUsuario = document.getElementById("formUsuario");
const listaUsuarios = document.getElementById("listaUsuarios"); 
const IdInput = document.getElementById("Id");
const nomeInput = document.getElementById("nome");
const emailInput = document.getElementById("email");
const senhaInput = document.getElementById("senha");
const cancelBtn = document.getElementById("cancelBtn"); 
const listarBtn = document.getElementById("listarBtn");

async function carregarUsuarios() {
    try {
        const resposta = await fetch(apiURL);
        if (!resposta.ok) throw new Error("Erro ao buscar usuários");
        
        const usuarios = await resposta.json();
        
        listaUsuarios.innerHTML = ""; 
        
        usuarios.forEach(u => {
            const li = document.createElement("li");
            li.innerHTML = `
                <span>${u.id} - ${u.nome} - ${u.email}</span>
                <button onclick="editarUsuario(${u.id}, '${u.nome}', '${u.email}')">Editar</button>
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

async function editarUsuario(id) {
    try {
        const resposta = await fetch(`${apiURL}/${id}`);
        
        // Se o servidor der erro 500, para aqui e avisa
        if (!resposta.ok) {
            alert("Erro no servidor ao buscar usuário!");
            return;
        }

        const usuario = await resposta.json();
        
        if (usuario) {
            IdInput.value = usuario.id || "";
            nomeInput.value = usuario.nome || "";
            emailInput.value = usuario.email || "";
            senhaInput.value = usuario.senha || "";
            
            submitBtn.textContent = "Atualizar";
            cancelBtn.style.display = "inline-block";
        }
    } catch (erro) {
        console.error("Erro ao buscar usuário para edição: ", erro);
    }
}