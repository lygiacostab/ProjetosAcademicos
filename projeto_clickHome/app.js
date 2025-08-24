const express = require('express');
const app = express();
const path = require('path');
const Usuario = require('./models/Usuario');

//Configurações:
app.set('view engine', 'ejs');
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.urlencoded({ extended: true}));

//Banco de dados temporário
const usuarios = [];

//Rotas
app.get('/', (req,res) => {
    res.render('index', { title: 'Home - CLICK&HOME', usuario: null});
});
app.get('/login', (req, res) => {
    res.render('login', { title: 'Login', usuario: null});
});

app.get('/cadastroUser', (req,res) => {
    res.render('cadastroUser', { title: 'Cadastro Usuário', usuario: null});
});
app.post('/cadastroUser', (req, res) =>{
    const{cpf, nome, sexo, email, senha, senhaConfirm} = req.body;

//criando novo objeto para Usuário:
const novoUsuario = new Usuario(
    cpf,
    nome,
    sexo,
    email,
    senha,
    senhaConfirm
);
usuarios.push(novoUsuario);

res.render('identifUsuario', {
    title: 'Bem-vindo(a)!',
    usuario: novoUsuario,
    usuarios
});
});

app.get('/identifUsuario', (req,res) => {
    res.render('identifUsuario', { title: 'Usuarios Cadastrados', usuarios,
        usuario: null
    });
    
    });

app.get('/capturaImagem', (req,res) => {
    res.render('capturaImagem', { title: 'Captura Imagem', usuario: null});
});
app.get('/pagConstrucao', (req,res) => {
    res.render('pagConstrucao', {title: 'Página em Construção', usuario: null});
});

//Servidor
const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Servidor rodando em http://localhost:${PORT}`);
});
