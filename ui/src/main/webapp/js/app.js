alert( "JS enabled" );

/*const buttonElem = document.getElementById('OpenCloseBtn');*/

function ShowMess(){
    alert( "Button!" );
}

function OpenClose(id){
    var obj = "";
    if (document.getElementById) obj = document.getElementById("ntlr").style;
    /*else if(document.all) obj = document.all[id];
    else if(document.layers) obj = document.layers[id];*/

    if (obj.display == "")
        obj.display = "none";
    else
        obj.display = "";
}

document.getElementById('OpenCloseBtn').addEventListener('click', ShowMess);

/*
buttonElem.addEventListener('click', OpenClose);*/
