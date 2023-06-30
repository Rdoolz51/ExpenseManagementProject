let dashbtn = document.getElementById('dashbtn')
let logoutbtn = document.getElementById('logoutbtn')
let tBody = document.getElementById('rTableBody')

const url = "http://localhost:8080"


dashbtn.addEventListener("click", () => {
    alert("Off to Dashboard!");
});

logoutbtn.addEventListener("click", () => {
   window.location.href = "login.html"
});

// document.getElementById('reqReimburse').onclick = requestReimbursement;

window.onload = async function(){

    await fetch(`${url}/reimbursements`)
    .then((response) => response.json())
    .then((data) => {
        console.log(data);

        for(let reimbursement of data){
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
            cell4.innerText = reimbursement.person.firstName + " " +  reimbursement.person.lastName;
            row.appendChild(cell4)

            let cell5 = document.createElement('td')
            cell5.innerText = reimbursement.status.name;
            row.appendChild(cell5)

            let cell6 = document.createElement('td')
            var approveButton = document.createElement('button')
            approveButton.innerText = "Approve"
            approveButton.setAttribute("id", "approvebtn")

            var denyButton = document.createElement('button')
            denyButton.innerText = "Decline"
            denyButton.setAttribute("id", "denybtn")

            cell6.appendChild(approveButton)
            cell6.appendChild(denyButton)
            row.appendChild(cell6)

            tBody.appendChild(row)
            

            approveButton.onclick = approveDenyFunction(1);
            denyButton.onclick = approveDenyFunction(2)

        }
    })
}
async function approveDenyFunction(newStatus) {

    if(newStatus === 1) {
        let status = {
            "id": 2,
            "name": "Approved"
        }
        await fetch(url + '/reimbursements/{id}', {
            method: "PUT",
            body: JSON.stringify(status),
            headers: {
                "Authorization": "Bearer " + document.cookie,
                "Content-Type":"application/json"
            }
                                    
        })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
        })
}

}
