const url = "http://localhost:8080"
let dashbtn = document.getElementById('dashbtn')
let logoutbtn = document.getElementById('logoutbtn')
let tBody = document.getElementById('rTableBody')
var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];
let filterSelect = document.getElementById('filterSelect');
btn.onclick = function () {
  modal.style.display = "block";
}
span.onclick = function () {
  modal.style.display = "none";
  document.getElementById('reimburseAmount').value = 0
  document.getElementById('reimburseDesc').value = ""
  document.getElementById('modalSub').innerHTML = ""
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}



logoutbtn.addEventListener("click", () => {
  document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });
  window.location.href = "login.html"
});
function loadData() {
 desiredStatusName = document.getElementById('filterSelect').value;
 location.reload();
}

document.getElementById('sbmRequest').onclick = requestReimbursement;
window.onload = async function () {
  mngDashboard()
  filterSelect.addEventListener('change', loadData);
  desiredStatusName = document.getElementById('filterSelect').value;
  await fetch(`${url}/reimbursements`)
  .then((response) => response.json())
  .then((data) => {
      dashbtn.innerHTML = `Hello, ${(parseJwt(document.cookie).sub)}!`
      for (let reimbursement of data) {if (parseJwt(document.cookie).Id === reimbursement.person.id && reimbursement.status.name === desiredStatusName || parseJwt(document.cookie).Id === reimbursement.person.id && desiredStatusName === "Filter") {
        
        
          let row = document.createElement('tr')
          let cell = document.createElement('td')

          cell.innerText = reimbursement.id;
          row.appendChild(cell)

          let cell2 = document.createElement('td')
          cell2.innerText = `$${reimbursement.amount}`;
          row.appendChild(cell2)

          let cell3 = document.createElement('td')
          cell3.innerText = reimbursement.description;
          row.appendChild(cell3)

          let cell4 = document.createElement('td')
          cell4.innerText = reimbursement.person.firstName + " " + reimbursement.person.lastName;
          row.appendChild(cell4)

          let cell5 = document.createElement('td')
          cell5.innerText = reimbursement.status.name;
          row.appendChild(cell5)

          tBody.appendChild(row)
        }
      }
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


async function requestReimbursement() {
  let newReimbursement = {
    amount: document.getElementById('reimburseAmount').value,
    description: document.getElementById('reimburseDesc').value
  }


  if(newReimbursement.amount && newReimbursement.description) {

    await fetch(`${url}/reimbursements`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + document.cookie
      },
      body: JSON.stringify(newReimbursement)
    })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      location.reload();
    })
    
  } else {
    document.getElementById('modalSub').innerHTML = "Please Make Sure The Amount And Description Are Both Valid"
  }
}
  

function mngDashboard() {
  if(parseJwt(document.cookie).Role === "Finance Manager") {
    document.getElementById('mngPageSwap').removeAttribute("hidden")
  } 
}