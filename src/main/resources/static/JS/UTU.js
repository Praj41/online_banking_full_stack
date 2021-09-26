var tran = {
    "amount" : 0.00,
    "to_account_id" : 0,
    "from_account_id" : 0
};

var loginRequest = new XMLHttpRequest();
var loginRequest1 = new XMLHttpRequest();

const param = new URLSearchParams(window.location.search);

loginRequest1.open('GET', 'http://localhost:8080/api/v1/user/' + param.get('id'));

loginRequest1.send();

var myvar;

loginRequest1.onload = function () {
    myvar = JSON.parse(loginRequest1.responseText)
    loginRequest.open('POST', 'http://localhost:8080/api/v1/transaction/transfer');
    loginRequest.setRequestHeader("Content-Type", "application/json");
    tran.from_account_id = myvar.pid;
}

function next() {
    var amount = document.getElementById("inputEmail").value;
    var to = document.getElementById("inputPassword").value;
    var check = document.getElementById("chk").checked;
    if (!check) {
        alert("Check the box");
        return;
    }
    if (amount === "") {
        alert("Enter amount");
        return;
    }
    if (to === "") {
        alert("Enter Account number of receiver");
        return;
    }
    tran.amount = amount;
    tran.to_account_id = to;
    console.log(tran);

    loginRequest.send(JSON.stringify(tran));

}

loginRequest.onload = function () {
    console.log(loginRequest.responseText);
    var response = JSON.parse(loginRequest.responseText);
    alert(response.status + "!!\nCheck History For More Details");
    window.location.replace("user_homepage.html?id=" + param.get('id'));
}

function cancel() {
    window.location.replace("user_homepage.html?id=" + param.get('id'));
}
