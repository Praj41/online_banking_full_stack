
function login() {
    var loginRequest = new XMLHttpRequest();

    loginRequest.onload = function () {

        console.log(loginRequest.responseText);

        if (loginRequest.responseText[0] === '0') {
            alert("login failed");
        } else {
            alert("login success");
            window.location.replace("jsontable.html?id="+loginRequest.responseText);
        }
    };

    var cred = {
        "username" : "",
        "pass" : ""
    }

    cred.username = document.getElementById("inputEmail").value;
    cred.pass = document.getElementById("inputPassword").value;

    loginRequest.open('POST', 'http://localhost:8080/api/v1/user/login');
    loginRequest.setRequestHeader("Content-Type", "application/json");

    loginRequest.send(JSON.stringify(cred));

}

function signup() {
    window.location.replace("signup.html");
}