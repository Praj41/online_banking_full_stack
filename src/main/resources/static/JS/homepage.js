var user_data = {
    "id": "",
    "email": "",
    "enable": "",
    "fname": "",
    "lname": "",
    "pass": "",
    "ph": "",
    "username": "",
    "pid": "",
    "lid": 0
}

var data1 = {
    "account_number": "",
    "account_balance": ""
}
var user = document.getElementById('user_data');
function filldata() {
    user.innerHTML = "Name : " + user_data.id + "<br>Email : " + user_data.email +
        "<br>Phone : " + user_data.ph + "<br>Username : " + user_data.username + "<br>Account Number : " + data1.account_number + "<br>Balance : " + data1.account_balance;
}

filldata();

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
    user.innerHTML = "Name : " + user_data.fname + " " + user_data.lname + "<br>Email : " + user_data.email +
        "<br>Phone : " + user_data.ph + "<br>Username : " + user_data.username + "<br>Account Number : " + data1.account_number + "<br>Balance : " + data1.account_balance

}

function transact() {
    window.location.replace("ITransactions.html?id=" + user_data.id);
}
function deposit() {
    window.location.replace("deposit.html?id=" + user_data.id + "&action=Pdeposit");
}
function withdraw() {
    window.location.replace("withdraw.html?id=" + user_data.id + "&action=PtoOut");
}
function utrans() {
    window.location.replace("UTransactions.html?id=" + user_data.id);
}
function logout() {
    window.location.replace("signin.html");
}
function utu() {
    window.location.replace("UTU.html?id=" + user_data.id);
}
function getloan() {
    window.location.replace("getLoan.html?id=" + user_data.id);
}

function payloan() {


    if (user_data.lid === undefined || user_data.lid === 0) {
        alert("Get a Loan First");
        return;
    }

    var loginRequest2 = new XMLHttpRequest();
    loginRequest2.open('POST', 'http://localhost:8080/api/v1/loan/emi');
    loginRequest2.setRequestHeader("Content-Type", "application/json");
    loginRequest2.send(JSON.stringify(user_data));


    loginRequest2.onload = function () {
        var tmp = JSON.parse(loginRequest2.responseText);
        alert(tmp.status + "!!\nFor More details check History");
        data1.account_balance = tmp.available_balance;
        filldata();
    }

}

function view() {
    if (user_data.lid === undefined || user_data.lid === 0) {
        alert("No Loan exist");
        return;
    }

    var loan = {
        "loan_total" : 0,
        "years" : 0,
        "account_number" : "0"
    }

    loan.account_number = user_data.lid;

    var loginRequest3 = new XMLHttpRequest();
    loginRequest3.open('POST', 'http://localhost:8080/api/v1/loan/get');
    loginRequest3.setRequestHeader("Content-Type", "application/json");
    loginRequest3.send(JSON.stringify(loan));

    loginRequest3.onload = function () {
        loan = JSON.parse(loginRequest3.responseText);
        alert("Loan Remaining : " + loan.loan_balance +
              "\nLoan Total : " + loan.loan_total +
              "\nRate : " + loan.rate +
              "\nFor Years : " + loan.years);
    }

}