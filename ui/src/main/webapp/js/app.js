function toggleRow(value){
    var subElem = document.getElementById(value);
    subElem.style.display = subElem.style.display === 'none' ? 'block' : 'none';
}

document.getElementById('OpenCloseBtn').addEventListener('click', toggleRow);

function addLine() {
    var lineTable = document.getElementById("lineTable");

    /*var rowCount = lineTable.rows.length;
    var lastRow = lineTable.rows[rowCount - 1]; // last row
    var lastRowValue = lastRow.cells[0].children[0].value;*/

    var lineTableRow = lineTable.insertRow(lineTable.rows.length-1);

    var col1html = "<form:input path=\"orderDetails[${counter.index}].itemNumber\" />";
    var col2html = "<form:input path=\"orderDetails[${counter.index}].serialNumber\" />";
    var col3html = "<form:input path=\"orderDetails[${counter.index}].productName\" />";
    var col4html = "<form:input path=\"orderDetails[${counter.index}].qty\" />";
    var col5html = "<button onclick=\"removeLine(this)\">Удалить</button>";

    var col1 = lineTableRow.insertCell(0); col1.innerHTML=col1html;
    var col2 = lineTableRow.insertCell(1); col2.innerHTML=col2html;
    var col3 = lineTableRow.insertCell(2); col3.innerHTML=col3html;
    var col4 = lineTableRow.insertCell(3); col4.innerHTML=col4html;
    var col5 = lineTableRow.insertCell(4); col5.innerHTML=col5html;

    /*lineTableRow.cells[0].children[0].value = lastRowValue + 1; *///add the sequent number of new row as itemNumber
}

function removeLine(lineItem) {
    var row = lineItem.parentNode.parentNode;
    row.parentNode.removeChild(row);
}
