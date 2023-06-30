let dashbtn = document.getElementById('dashbtn')
let logoutbtn = document.getElementById('logoutbtn')
let tBody = document.getElementById('rTableBody')
let filterSelect = document.getElementById('filterSelect');

const url = "http://localhost:8080"



logoutbtn.addEventListener("click", () => {
    window.location.href = "login.html"
});
function loadData() {
    desiredStatusName = document.getElementById('filterSelect').value;
    location.reload();
}

// window.onloadstart = validateRole;
document.getElementById('sbmRequest').onclick = requestReimbursement;
function validateRole() {
    if (parseJwt(document.cookie).Role === "Employee") {
        window.location.href = "employee.html"
        location.reload();
    }
}
window.onload = async function () {
    filterSelect.addEventListener('change', loadData);
    desiredStatusName = document.getElementById('filterSelect').value;
    await fetch(`${url}/reimbursements`)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            dashbtn.innerHTML = `Hello, ${(parseJwt(document.cookie).sub)}!`
            for (let reimbursement of data) {
                if (reimbursement.status.name === desiredStatusName || desiredStatusName === "Filter") {

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

                    let cell6 = document.createElement('td')

                    var approveButton = document.createElement('button')
                    approveButton.innerText = "Approve"
                    approveButton.setAttribute("id", `${reimbursement.status.name}`)

                    var denyButton = document.createElement('button')
                    denyButton.innerText = "Decline"
                    denyButton.setAttribute("id", `${reimbursement.status.name}`)

                    cell6.appendChild(approveButton)
                    cell6.appendChild(denyButton)
                    row.appendChild(cell6)

                    tBody.appendChild(row)

                    approveButton.setAttribute("onclick", `approveDenyFunction(${reimbursement.id}, 1)`)
                    denyButton.setAttribute("onclick", `approveDenyFunction(${reimbursement.id}, 2)`)


                    if (approveButton.getAttribute("id") == "Approved" || denyButton.getAttribute("id") == "Declined") {
                        cell6.style.display = "none"
                    }
                }
            }
        })
}
async function approveDenyFunction(id, newStatus) {

    if (newStatus === 1) {
        let status = {
            id: "2",
            name: "Approved"
        }
        await fetch(`${url}/reimbursements/${id}/aod`, {
            method: "PUT",
            body: JSON.stringify(status),
            headers: {
                "Authorization": "Bearer " + document.cookie,
                "Content-Type": "application/json"
            }

        })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                location.reload();
            })
    } else if (newStatus === 2) {
        let status = {
            id: "3",
            name: "Declined"
        }
        await fetch(`${url}/reimbursements/${id}/aod`, {
            method: "PUT",
            body: JSON.stringify(status),
            headers: {
                "Authorization": "Bearer " + document.cookie,
                "Content-Type": "application/json"
            }

        })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                location.reload();
            })
    }

}

var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];
btn.onclick = function () {
    modal.style.display = "block";
}
span.onclick = function () {
    modal.style.display = "none";
    document.getElementById('reimburseAmount').value = 0
    document.getElementById('reimburseDesc').value = ""
    document.getElementById('modalSub').innerHTML = ""
}
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

async function requestReimbursement() {
    let newReimbursement = {
        amount: document.getElementById('reimburseAmount').value,
        description: document.getElementById('reimburseDesc').value
    }


    if (newReimbursement.amount && newReimbursement.description) {
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



function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}