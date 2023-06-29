const url = "http://localhost:8080"

document.getElementById('registerBtn').onclick = register;

async function register() {

    let fname = document.getElementById('fnRegInput').value.trim()
    let lname = document.getElementById('lnRegInput').value.trim()
    let username = document.getElementById('userRegInput').value.trim()
    let password = document.getElementById('pwRegInput').value.trim()

    let registerDTO = {
        firstName:fname,
        lastName:lname,
        username:username,
        password:password
    }

    await fetch(`${url}/auth/register`, {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(registerDTO)
    })
    .then((response) => response.json())
    .then((data) => {
        console.log(data);
        if(data.response) {
            alert("Registration Successful!")
        } else {
            alert("Email already taken!")
        }
    })
    .catch((err) =>{
        console.log(err);
    })
}