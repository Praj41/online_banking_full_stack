
var loginRequest = new XMLHttpRequest();

var cred = {
    "email" : "",
    "fname" : "",
    "lname" :  "",
    "pass" : "",
    "ph" :  "",
    "username" : ""
}

loginRequest.open('POST', 'http://localhost:8080/api/v1/user/signup');
loginRequest.setRequestHeader("Content-Type", "application/json");

loginRequest.onload = function () {

    console.log(loginRequest.responseText);

    if (loginRequest.responseText[0] === '0') {
        alert("Signup failed");
    } else {
        alert("Account created");
        window.location.replace("signin.html");
    }
};


function login() {

    cred.email = document.getElementById("inputEmail").value;
    cred.fname = document.getElementById("fname").value;
    cred.lname = document.getElementById("lname").value;
    cred.pass = document.getElementById("inputPassword").value;
    cred.ph = document.getElementById("phone").value;
    cred.username = document.getElementById("uname").value;

    loginRequest.send(JSON.stringify(cred));

}

function signin() {
    window.location.replace("signin.html");
}