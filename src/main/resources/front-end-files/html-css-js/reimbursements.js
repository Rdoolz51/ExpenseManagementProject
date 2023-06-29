let dashbtn = document.getElementById('dashbtn')
let logoutbtn = document.getElementById('logoutbtn')


dashbtn.addEventListener("click", () => {
    alert("Off to Dashboard!");
})

logoutbtn.addEventListener("click", () => {
   window.location.href = "login.html"
})