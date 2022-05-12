function toggleRow(value){
    var subElem = document.getElementById(value);
    subElem.style.display = subElem.style.display === 'none' ? 'block' : 'none';
}

document.getElementById('OpenCloseBtn').addEventListener('click', toggleRow);

function addLine() {
    var lineTable = document.getElementById("lineTable");

    var rowCount = lineTable.rows.length - 1;
    var rowIndex = rowCount - 1;

    var lineTableRow = lineTable.insertRow(lineTable.rows.length-1);

    var col1html = `<input name='orderDetails[${rowIndex}].itemNumber' pattern='^[ 0-9]+$' />`;
    var col2html = `<input name='orderDetails[${rowIndex}].serialNumber' pattern='^[^\\s]*$' />`;
    var col3html = `<input name='orderDetails[${rowIndex}].productName' />`;
    var col4html = `<input name='orderDetails[${rowIndex}].qty' pattern='^[ 0-9]+$' />`;
    var col5html = "<button type='button' onclick='removeLine(this)'>Удалить</button>";

    var col1 = lineTableRow.insertCell(0); col1.innerHTML=col1html;
    var col2 = lineTableRow.insertCell(1); col2.innerHTML=col2html;
    var col3 = lineTableRow.insertCell(2); col3.innerHTML=col3html;
    var col4 = lineTableRow.insertCell(3); col4.innerHTML=col4html;
    var col5 = lineTableRow.insertCell(4); col5.innerHTML=col5html;

    var lastRow = lineTable.rows[rowIndex];
    var lastRowValue = lastRow.cells[0].children[0].value;
    lineTableRow.cells[0].children[0].value = Number(lastRowValue) + 1; //add the sequent number of new row as itemNumber
}

function removeLine(lineItem) {
    var row = lineItem.parentNode.parentNode;
    row.parentNode.removeChild(row);
}

function submitForm(){
    //form.submit();
    confirm('Сохранить изменения?');
    document.getElementById("orderForm").submit();
}
