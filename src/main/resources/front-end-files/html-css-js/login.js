const url = "http://localhost:8080"

document.getElementById('loginBtn').onclick = login;

async function login() {
    
    let username = document.getElementById('userLoginInput').value
    let password = document.getElementById('pwLoginInput').value

    let loginDTO = {
        username:username,
        password:password
    }

    console.log(loginDTO);


    await fetch(`${url}/auth/login`, {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(loginDTO)
    })
    .then((response) => response.json())
    .then((data) => {

        console.log(data);
        console.log(parseJwt(data.accessToken));
        document.cookie = data.accessToken;

        if(parseJwt(data.accessToken).Role === "Finance Manager"){
            //go to Manager page
            window.location.href = "reimbursements.html"
        } else {
            //go to Employee page
            window.location.href = "reimbursements.html"
        }
        
    })
    .catch((error) => {
    document.getElementById("subHeader").innerHTML = "Login Failed!"
    console.log(error);
})




}

function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
  
    return JSON.parse(jsonPayload);
  }






