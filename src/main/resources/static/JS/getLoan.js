var tran = {
    "loan_total" : 0.0,
    "years" : 0,
    "account_number" : 0
};

var loginRequest = new XMLHttpRequest();
var loginRequest1 = new XMLHttpRequest();

const param = new URLSearchParams(window.location.search);

loginRequest1.open('GET', 'http://localhost:8080/api/v1/loan/create/' + param.get('id'));

loginRequest1.send();

var myvar;

loginRequest1.onload = function () {
    myvar = JSON.parse(loginRequest1.responseText)
    loginRequest.open('POST', 'http://localhost:8080/api/v1/loan/get');
    loginRequest.setRequestHeader("Content-Type", "application/json");
    tran.account_number = myvar.account_number;
}

function next() {
    var amount = document.getElementById("inputEmail").value;
    var years = document.getElementById("inputPassword").value;
    var check = document.getElementById("chk").checked;
    if (!check) {
        alert("Check the box");
        return;
    }
    if (amount === "") {
        alert("Enter amount");
        return;
    }
    if (years === "") {
        alert("Enter years");
        return;
    }
    tran.loan_total = amount;
    tran.years = years;
    console.log(tran);

    loginRequest.send(JSON.stringify(tran));

}

loginRequest.onload = function () {
    console.log(loginRequest.responseText);
    var response = JSON.parse(loginRequest.responseText);
    alert("\nFor : " + response.years + "\nAt : " + response.rate  + "%" + "\nOK!!\nCheck History For More Details");
    window.location.replace("user_homepage.html?id=" + param.get('id'));
}

function cancel() {
    window.location.replace("user_homepage.html?id=" + param.get('id'));
}
