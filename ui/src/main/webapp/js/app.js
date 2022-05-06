function toggleRow(value){
    var subElem = document.getElementById(value);
    subElem.style.display = subElem.style.display === 'none' ? 'block' : 'none';
}

document.getElementById('OpenCloseBtn').addEventListener('click', toggleRow);
