document.addEventListener("DOMContentLoaded", ()=>{

});

const apiURL= "http://localhost:8080/usuario";
const formUsuario = document.getElementById("formUsario");
const IdInput = document.getElementById("Id");
const nomeInput = document.getElementById("nome");
const emailInput = document.getElementById("email");
const senhaInput = document.getElementById("senha");
const submitBtn = document.getElementById("submitBtn")

formUsuario.addEventListener("submit", async (e) =>{
	e.preventDefault();
	const usuario ={
		nome: nomeInput.value,
		email: emailInput.value,
		senha: senhaInput.value,
	};
	
	const isEditing = IdInput.value !=="";
	const method = isEditing ? "PUT":"POST";
	const url = isEditing ? `${apiURL}/${IdInput.value}`: apiURL;
	
	try{
		await fetch(url, {
			method: method
			headers:{"content-Type":application/json},
			body: JSON.stringif(usuario)
		});
	}
});