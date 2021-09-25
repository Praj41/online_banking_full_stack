var myArray;
var loginRequest = new XMLHttpRequest();
var loginRequest1 = new XMLHttpRequest();

const param = new URLSearchParams(window.location.search);


loginRequest1.open('GET', 'http://localhost:8080/api/v1/user/' + param.get('id'));


loginRequest1.send();
var myvar;

loginRequest1.onload = function () {
    myvar = JSON.parse(loginRequest1.responseText)
    loginRequest.open('GET', 'http://localhost:8080/api/v1/transaction/' + myvar.pid);
    loginRequest.send();
}


loginRequest.onload = function () {

    myArray = JSON.parse(loginRequest.responseText);

    buildTable(myArray);
}

function buildTable(data) {
    var table = document.getElementById('myTable')

    for (var i = 0; i < data.length; i++) {
        var row = `<tr>
							<td>${data[i].id}</td>
							<td>${data[i].amount}</td>
              <td>${data[i].available_balance}</td>
							<td>${data[i].date}</td>
              <td>${data[i].description}</td>
              <td>${data[i].status}</td>
              <td>${data[i].type}</td>
              <td>${data[i].primary_account_id}</td>
              <td>${data[i].loan_account_id}</td>
					  </tr>`
        table.innerHTML += row


    }
}

function signin() {
    window.location.replace("user_homepage.html?id=" + param.get('id'));
}