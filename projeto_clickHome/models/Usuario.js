class Usuario{
    constructor(
        cpf,
        nome,
        sexo,
        email,
        senha,
        senhaConfirm
    ){
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        this.senhaConfirm = senhaConfirm;
    }
}

module.exports = Usuario;