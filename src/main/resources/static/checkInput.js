const input_nom = document.getElementById('nom');
const input_prix = document.getElementById('prix');
const input_stock = document.getElementById('stock');

const lblErrorNom = document.getElementById('lblErrorNom');
const lblErrorPrix = document.getElementById('lblErrorPrix');
const lblErrorStock = document.getElementById('lblErrorStock');

function isEmpty(){
    let isOk = false;

    if(input_nom.value == ""){ 
        input_nom.classList.add('input-error');
        lblErrorNom.classList.remove('errorLabel')
    }else{
        isOk = true;
        lblErrorNom.classList.add('errorLabel')
    }

    if(input_prix.value == ""){
        isOk = false;
        lblErrorPrix.classList.remove('errorLabel')
    }else{
        isOk = true;
        lblErrorPrix.classList.add('errorLabel')
    }

    if(input_stock.value == ""){
        isOk = false;
        lblErrorStock.classList.remove('errorLabel')
    }else{
        isOk = true;
        lblErrorStock.classList.add('errorLabel')
    }
    
    if(isOk){
        confirmFenetre();
    }
}