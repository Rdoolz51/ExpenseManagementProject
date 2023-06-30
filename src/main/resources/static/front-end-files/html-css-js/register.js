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

    if(registerDTO.firstName && registerDTO.lastName && registerDTO.username && registerDTO.password) {
        
        await fetch(`${url}/auth/register`, {
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify(registerDTO)
        })
        .then((response ) => response.status)
        .then((data) => {
        console.log(data);

        if(data === 201){

            document.getElementById('subHeader').style.color = "green"
            document.getElementById('subHeader').innerHTML = "Account Created! (Redirecting now...)"
            setTimeout(() => {window.location.href="login.html"}, 2500)
        }
        else
        document.getElementById('subHeader').innerHTML = "USERNAME ALREADY TAKEN"
    })
    .catch((err) =>{
        console.log(err);
    }) 
} else {
    document.getElementById('subHeader').innerHTML = "Please Make Sure All Entries Are Valid"
}
}