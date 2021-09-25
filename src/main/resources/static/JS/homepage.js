var user_data = {
    "id": "102",
    "email": "afas",
    "enable": "true",
    "fname": "qwea",
    "lname": "cxvx",
    "pass": "opug",
    "ph": "652314",
    "username": "sfgssgvs",
    "pid": "1121000002",
    "lid": 0
}

var data1 = {
    "account_number": "11210000002",
    "account_balance": "1250.50"
}
var user = document.getElementById('user_data');
user.innerHTML = "Name : " + user_data.id + "<br>Email : " + user_data.email +
    "<br>Phone : " + user_data.ph + "<br>Username : " + user_data.username + "<br>Account Number : " + data1.account_number + "<br>Balance : " + data1.account_balance

var loginRequest = new XMLHttpRequest();
var loginRequest1 = new XMLHttpRequest();

const param = new URLSearchParams(window.location.search);

loginRequest1.open('GET', 'http://localhost:8080/api/v1/user/' + param.get('id'));

loginRequest1.send();

loginRequest1.onload = function () {
    user_data = JSON.parse(loginRequest1.responseText)
    loginRequest.open('GET', 'http://localhost:8080/api/v1/user/PAccount/' + user_data.pid);
    loginRequest.send();
}

loginRequest.onload = function () {

    data1 = JSON.parse(loginRequest.responseText);

    display();
}

function display() {
    user.innerHTML = "Name : " + user_data.id + "<br>Email : " + user_data.email +
        "<br>Phone : " + user_data.ph + "<br>Username : " + user_data.username + "<br>Account Number : " + data1.account_number + "<br>Balance : " + data1.account_balance

}

function transact() {
    window.location.replace("ITransactions.html?id=" + user_data.id);
}