/* AULA DE 15/03/25
alert("Olá, JavaScript");
console.log("Olá, JavaScript teste!");

let vetor = [];                //declara vetor
vetor[0] = "Lygia";
vetor[1] = "Nadinael";
console.log(vetor[1]);         //exibe o valor do vetor no console navegador
vetor.push("Isadora");         //adiciona mais um valor a monte e/ou vetor
console.log("\n"+ vetor[2]);*/

/*Regras do minigame
1- a pirâmide terá 3 camadas
2- as cartas devem ser embaralhadas
3- eu pontuo quando a carta virada no topo do baralhoSubst somada a alguma base(camada) atual da piramide1 for 13.
*/


class Stack{ //Imiga uma monte de cartas: 'push' coloca cartas no topo/'pop' tira o que está no topo/'top' vê o que está no topo 

    constructor(){
        this.items=[];
    }

    push(item){
        this.items.push(item); //adicionando item a monte
    }
    top(){ 
       return this.items[this.items.length-1]; // acessa o vetor item e retorna o tamanho do vetor -1 (último item da monte)
    }
    pop(){
        this.items.pop();
    }
}
// fim da classe stack

//criando classe para cartas
class Card{
 
        constructor(codigo){
            this.cod = codigo;
            this.naipe = "NULO";
            this.valor = 0;
            this.imagem = "back_01";
        }
        getCod(){
            return this.cod;
        }
        setValor(v){
            this.valor = v;
        }
        getValor(){
            return this.valor;
        }
        setImagem(img){
            this.imagem = img;
        }
        getImagem(){
            return this.imagem;
        }

}


//início automático

/*let monte = new Stack();
monte.push("Júpiter");
monte.push("Plutão");
monte.pop();
console.log(monte.top());*/
nomeUsuario();
let baralhoSubst = new Stack();
let cartasjogadas1 = new Stack();
let cartas = geradorDeCartas(); //cria as 52 cartas
let mesa = [];
let mesa2 = []; 
let cartaAtual;
let cartaPontuada = 0;
let soma2=0;
//console.log(cartas);
cartas = embaralhar(cartas); //embaralha as cartas
emmonter(cartas); //coloca as cartas na monte
distribuir(); //pega duas cartas para mesa
desenharMesa(); //exibe essas duar cartas na mesa
resultado();

//temporaria(cartas);



//Funções fica na parte inferior do JS (para organização)
function nomeUsuario(){
    let usuario = prompt("Digite o seu nome: ");
    document.getElementById("usuario").innerHTML = "<b>Usuário: </>"+usuario;
}

 //para as cartas receberem valor de forma automática:
function geradorDeCartas(){ //g + (valor) + (naipe: c, d, h, s)
    let vetor = [];
    for(let i=1; i<=13; i++){

        for(let j=0; j<4; j++){
            let codigo = "g"+i;
            let nomeImagem = "";
                if(i==1){
                    nomeImagem = "ace";
                }
                else if(i==11){
                    nomeImagem = "jack";
                }
                else if(i==12){
                    nomeImagem = "queen";
                }
                else if(i==13){
                    nomeImagem = "king"
                }
                else{
                    nomeImagem = i;
                }          
            
                if(j==0){
                    // 0 = C -> clubs -> Paus
                    codigo = codigo + "C";
                    nomeImagem = nomeImagem+"_of_clubs";
                }
                else if(j==1){
                    // 1 = D -> Diamonds -> Ouros
                    codigo = codigo + "D";
                    nomeImagem = nomeImagem+"_of_diamonds";
                }
                else if(j==2){
                    // 2 = H -> Hearts -> Copas
                    codigo = codigo + "H";
                    nomeImagem = nomeImagem+"_of_hearts";
                }
                else{
                    // 3 = S -> Spades -> Espada
                    codigo = codigo + "S";
                    nomeImagem = nomeImagem+"_of_spades";
                }

            let carta = new Card(codigo);

            let valor;
            if(i>10){
                valor = 10;
            }
            else{
                valor = i;
            }

           
            carta.setValor(valor);
            carta.setImagem(nomeImagem);
            vetor.push(carta);

        }
    }
    return vetor;
}

/*function temporaria(lista){
    let elemento = document.getElementById("pontuadas1"); //acessando o ID
    //let conteudo = "";
    //for(let i=0; i<lista.length; i++){
      //  conteudo = conteudo + "<img src='cards/cards/"+ lista[i].getImagem() +".png' /> ";
    //}
    console.log(conteudo);
    //elemento.innerHTML += conteudo;
}*/

function emmonter(vetor){//5.coloca todas as cartas da lista embaralhada na monte 'baralhoSubst'
    for(let i=0; i<vetor.length; i++){
        baralhoSubst.push(vetor[i]);
    }
}

function virarCarta(){ //6.quando usuário clica no botão 'virar nova carta' esta função é executada
    let elemento = document.getElementById("jogadas1"); //exibe a carta virada
    cartaAtual = baralhoSubst.top();
    elemento.innerHTML = "<img src='cards/cards/"+ cartaAtual.getImagem()+".png' />"
    baralhoSubst.pop(); //remove a carta da monte
    
    setTimeout(function (){ //aguarda um tempo para executar a funçao desenharMesa2, e depois limpa a variável elemento

        mesa2.push(cartaAtual);
        desenharMesa2();

        elemento.innerHTML = "";
    }, 2000);

    cartaPontuada = cartaPontuada + cartaAtual.getValor();
    resultado();
    
    
}

function sortearNumero(){
    let min = 0;
    let max = 51;
    let numero = Math.floor(Math.random() * (max - min + 1)) + min;
    return numero;
}

function embaralhar(lista){//4.pega as cartas e cria uma nova lista embaralhada (sem repetir)
    let vetor = [];
    while(vetor.length<lista.length){
        let iSort = sortearNumero();
        let valido = true;
        
        for(let j=0; j<vetor.length; j++){
            if(lista[iSort].cod == vetor[j].cod){
                valido = false;
            }
        }
        if(valido){
            vetor.push(lista[iSort]);
        }
    }
    
    return vetor;
}

function distribuir(){ //7.Pega as duas primeiras cartas do baralhoSubst e coloca na "mesa" (vetor mesa)
    mesa.push(baralhoSubst.top());
    baralhoSubst.pop();
    mesa.push(baralhoSubst.top());
    baralhoSubst.pop();

}

function desenharMesa(){//8. Exibe as cartas que estão na mesa, dentro do elemento com id=pontuadas1
    let elemento = document.getElementById("pontuadas1");
    let pagina = "";

    for(let i=0; i<mesa.length; i++){
        pagina = pagina + "<div> <img src='cards/cards/"+mesa[i].getImagem()+ ".png' /> </div>";
       
    }
    
    elemento.innerHTML = pagina;
}

function desenharMesa2(){
    let elemento = document.getElementById("pontuadas2");
    let pagina = "";

    for(let i=0; i<mesa2.length; i++){
        pagina = pagina + "<div> <img src='cards/cards/"+mesa2[i].getImagem()+ ".png' /> </div>";
    }

    elemento.innerHTML = pagina;
}

function resultado(){
    let soma1 = 0;
    for(let i=0; i<mesa.length; i++){
        soma1 = soma1 + mesa[i].getValor();
        
    }
    soma2 = soma1 + cartaPontuada;
        
    document.getElementById("pontuacaoAtual").innerHTML = "<b>Pontuação atual: </>"+soma2;
    
    if(soma2>21){
        setTimeout(() =>{
        alert(`GAME OVER!\nSua pontuação atual é ${soma2}, maior que a pontuação máxima: 21`);
        }, 150);
    } 
}
function resultadoFinal(){
    if(soma2<=21){
        document.getElementById("resultadoFinal").innerHTML = "<b>Resultado final: </>"+soma2;
    }
}
function resetJogo(){
    cartaPontuada = 0;
    soma2 = 0;
    soma1 = 0;
    mesa = [];
    mesa2 = []; 

    document.getElementById("usuario").innerHTML = " ";
    document.getElementById("pontuadas1").innerHTML = "";
    document.getElementById("jogadas1").innerHTML = "";
    document.getElementById("pontuacaoAtual").innerHTML = "<b>Pontuação atual: </>"+soma2;
    document.getElementById("resultadoFinal").innerHTML = "";
    

    nomeUsuario();
    distribuir();
    desenharMesa();
    desenharMesa2();
    resultado();
}