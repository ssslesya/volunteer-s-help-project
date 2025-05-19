function createOrder0() {
    sessionStorage.setItem("tarif", document.getElementById("tarif").value);
    if((document.getElementById("addressX").value!==null)&&(document.getElementById("addressY").value!==null)){
        sessionStorage.setItem("adstartx", document.getElementById("addressX").value.toString());
        sessionStorage.setItem("adstarty", document.getElementById("addressY").value.toString());
        window.location.href='client1.html';
    }
}
function createOrder(){
    let addressfinalx = document.getElementById("addressX").value;
    let addressfinaly = document.getElementById("addressY").value;
    let addressstartx = sessionStorage.getItem("adstartx");
    let addressstarty = sessionStorage.getItem("adstarty");
    let tarif = sessionStorage.getItem("tarif");
    let client = sessionStorage.getItem("id");
    let order = {
        addressstartx: addressstartx,
        addressstarty: addressstarty,
        addressfinalx: addressfinalx,
        addressfinaly: addressfinaly,
        client: client,
        tarif: tarif
    }
    fetch('http://localhost:8080/taxi/new-order',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(order)
    })
        .then(response => {
             return response.json();
        })
        .then(order => {
            sessionStorage.setItem("orderId", order.id);
            console.log(sessionStorage.getItem("orderId"));
            window.location.href='client2.html';
        })
}

function exit(){
    sessionStorage.clear();
    window.location.href='index.html';
}