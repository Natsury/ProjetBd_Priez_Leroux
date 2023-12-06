const divPopUp_Confirm = document.getElementById("popUp");
function confirmFenetre(){
    if(divPopUp_Confirm.classList.contains("popUp-open")){
        divPopUp_Confirm.classList.remove("popUp-open");
    } else{
        divPopUp_Confirm.classList.add("popUp-open");
    }
}